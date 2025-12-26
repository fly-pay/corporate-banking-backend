-- Register CORPORATE_BANKING PDB service with listener
-- This script runs after the database is initialized
-- Note: The shell script 00-register-service.sh handles this more reliably

-- Ensure PDB is open
ALTER SESSION SET CONTAINER=CDB$ROOT;
ALTER PLUGGABLE DATABASE CORPORATE_BANKING OPEN;

-- Register the service (will be handled by shell script, but this is a backup)
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

-- Verify service registration
SELECT name, network_name, enabled FROM v$services WHERE name LIKE '%CORPORATE%' OR name = 'XE' ORDER BY name;

