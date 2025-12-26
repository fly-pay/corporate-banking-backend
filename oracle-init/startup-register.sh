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

-- Use XEPDB1 (default PDB) or CORPORATE_BANKING if it exists
BEGIN
  EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE CORPORATE_BANKING OPEN';
EXCEPTION
  WHEN OTHERS THEN
    BEGIN
      EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE XEPDB1 OPEN';
    EXCEPTION
      WHEN OTHERS THEN NULL;
    END;
END;
/

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
      DBMS_OUTPUT.PUT_LINE('Service might already exist (this is OK): ' || SUBSTR(SQLERRM, 1, 100));
  END;
  
  BEGIN
    DBMS_SERVICE.START_SERVICE('CORPORATE_BANKING');
    DBMS_OUTPUT.PUT_LINE('Service CORPORATE_BANKING started');
  EXCEPTION
    WHEN OTHERS THEN
      DBMS_OUTPUT.PUT_LINE('Service might already be started (this is OK): ' || SUBSTR(SQLERRM, 1, 100));
  END;
END;
/

-- Verify service registration
SELECT name, network_name, enabled FROM v\$services WHERE name LIKE '%CORPORATE%' OR name = 'XE' ORDER BY name;

-- Save PDB state so it persists after container restart
BEGIN
  EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE CORPORATE_BANKING SAVE STATE';
  DBMS_OUTPUT.PUT_LINE('PDB CORPORATE_BANKING state saved');
EXCEPTION
  WHEN OTHERS THEN
    DBMS_OUTPUT.PUT_LINE('Note: Could not save PDB state (this is OK if PDB does not exist yet): ' || SUBSTR(SQLERRM, 1, 100));
END;
/

EXIT;
EOF

echo "========================================="
echo "PDB service registration completed"
echo "========================================="

