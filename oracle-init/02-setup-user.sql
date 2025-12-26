-- Setup database user and permissions for the application
-- This ensures the SYSTEM user has proper access

-- Try CORPORATE_BANKING first, fallback to XEPDB1
BEGIN
  EXECUTE IMMEDIATE 'ALTER SESSION SET CONTAINER=CORPORATE_BANKING';
EXCEPTION
  WHEN OTHERS THEN
    BEGIN
      EXECUTE IMMEDIATE 'ALTER SESSION SET CONTAINER=XEPDB1';
    EXCEPTION
      WHEN OTHERS THEN NULL;
    END;
END;
/

-- Set SYSTEM user password in the PDB
ALTER USER SYSTEM IDENTIFIED BY Oracle123;

-- Grant necessary privileges to SYSTEM user
GRANT CONNECT, RESOURCE, DBA TO SYSTEM;
GRANT UNLIMITED TABLESPACE TO SYSTEM;

-- Create application user for banking application
CREATE USER banking_user IDENTIFIED BY banking_pass;
GRANT CONNECT, RESOURCE TO banking_user;
ALTER USER banking_user QUOTA UNLIMITED ON USERS;

-- Create a dedicated application user (optional, but recommended for production)
-- For now, we'll use SYSTEM as configured in application.properties

COMMIT;

