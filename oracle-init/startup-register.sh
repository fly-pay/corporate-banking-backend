#!/bin/bash
# This script runs on every container startup to ensure PDB service is registered
# It's called by the custom entrypoint

set -e

echo "========================================="
echo "Registering CORPORATE_BANKING PDB service"
echo "========================================="

# Wait for Oracle to be ready
max_attempts=60
attempt=0

while [ $attempt -lt $max_attempts ]; do
  if sqlplus -S / as sysdba <<< "SELECT 1 FROM DUAL;" > /dev/null 2>&1; then
    break
  fi
  attempt=$((attempt + 1))
  if [ $((attempt % 10)) -eq 0 ]; then
    echo "Waiting for Oracle to be ready... ($attempt/$max_attempts)"
  fi
  sleep 2
done

if [ $attempt -eq $max_attempts ]; then
  echo "ERROR: Oracle database is not ready after $max_attempts attempts"
  exit 1
fi

echo "Oracle is ready. Registering PDB service..."

# Register the service using SQL*Plus (using OS authentication)
sqlplus -S / as sysdba <<EOF
SET SERVEROUTPUT ON;
ALTER SESSION SET CONTAINER=CDB\$ROOT;

-- Open PDBs (Fixed to include ORCLPDB1)
BEGIN
  -- 1. Try CORPORATE_BANKING
  BEGIN
    EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE CORPORATE_BANKING OPEN';
    DBMS_OUTPUT.PUT_LINE('Opened CORPORATE_BANKING');
  EXCEPTION WHEN OTHERS THEN NULL; END;

  -- 2. Try ORCLPDB1 (Added for your specific case)
  BEGIN
    EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE ORCLPDB1 OPEN';
    DBMS_OUTPUT.PUT_LINE('Opened ORCLPDB1');
  EXCEPTION WHEN OTHERS THEN NULL; END;

  -- 3. Try XEPDB1 (Default fallback)
  BEGIN
    EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE XEPDB1 OPEN';
    DBMS_OUTPUT.PUT_LINE('Opened XEPDB1');
  EXCEPTION WHEN OTHERS THEN NULL; END;
END;
/

-- Register the service
BEGIN
  BEGIN
    DBMS_SERVICE.CREATE_SERVICE(
      service_name => 'CORPORATE_BANKING',
      network_name => 'CORPORATE_BANKING',
      aq_ha_notifications => TRUE
    );
    DBMS_OUTPUT.PUT_LINE('Service CORPORATE_BANKING created');
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Service might already exist: ' || SUBSTR(SQLERRM, 1, 100));
  END;
  
  BEGIN
    DBMS_SERVICE.START_SERVICE('CORPORATE_BANKING');
    DBMS_OUTPUT.PUT_LINE('Service CORPORATE_BANKING started');
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Service might already be started: ' || SUBSTR(SQLERRM, 1, 100));
  END;
END;
/

-- Verify service registration
SELECT name, network_name, enabled FROM v\$services WHERE name LIKE '%CORPORATE%' OR name = 'XE' ORDER BY name;

-- Save PDB state so it persists (Fixed to include ORCLPDB1)
BEGIN
  -- Save CORPORATE_BANKING state
  BEGIN
    EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE CORPORATE_BANKING SAVE STATE';
  EXCEPTION WHEN OTHERS THEN NULL; END;

  -- Save ORCLPDB1 state
  BEGIN
    EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE ORCLPDB1 SAVE STATE';
    DBMS_OUTPUT.PUT_LINE('PDB ORCLPDB1 state saved');
  EXCEPTION WHEN OTHERS THEN NULL; END;
END;
/

EXIT;
EOF

echo "========================================="
echo "PDB service registration completed"
echo "========================================="
