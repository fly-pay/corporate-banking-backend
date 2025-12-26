#!/bin/bash
# This script runs on every container startup to ensure PDB service is registered
# It's called after the database is up and running

set -e

echo "Waiting for Oracle database to be ready..."
sleep 10

# Wait for Oracle to be fully up
until sqlplus -S sys/Oracle123@localhost/XE as sysdba <<< "SELECT 1 FROM DUAL;" > /dev/null 2>&1; do
  echo "Waiting for Oracle..."
  sleep 5
done

echo "Oracle is ready. Registering PDB service..."

# Register the service
sqlplus -S sys/Oracle123@localhost/XE as sysdba <<EOF
ALTER SESSION SET CONTAINER=CDB\$ROOT;
ALTER PLUGGABLE DATABASE CORPORATE_BANKING OPEN;

BEGIN
  BEGIN
    DBMS_SERVICE.CREATE_SERVICE(
      service_name => 'CORPORATE_BANKING',
      network_name => 'CORPORATE_BANKING',
      aq_ha_notifications => TRUE
    );
  EXCEPTION
    WHEN OTHERS THEN
      NULL; -- Service might already exist
  END;
  
  BEGIN
    DBMS_SERVICE.START_SERVICE('CORPORATE_BANKING');
  EXCEPTION
    WHEN OTHERS THEN
      NULL; -- Service might already be started
  END;
END;
/
EXIT;
EOF

echo "PDB service registration completed."

