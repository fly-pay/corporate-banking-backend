#!/bin/bash
# Script to remove Oracle volume and rebuild everything from scratch
# Usage: ./reset-database.sh

set -e

echo "========================================="
echo "Resetting Corporate Banking Database"
echo "========================================="
echo ""

echo "Step 1: Stopping all containers..."
docker compose down

echo ""
echo "Step 2: Removing Oracle data volume..."
docker volume rm corporate-banking-backend_oracle-data 2>/dev/null || echo "Volume doesn't exist or already removed"

echo ""
echo "Step 3: Rebuilding and starting all services..."
docker compose up --build -d

echo ""
echo "Step 4: Waiting for Oracle to be ready..."
echo "This may take 2-3 minutes for Oracle to fully initialize..."
sleep 60

echo ""
echo "Step 5: Registering CORPORATE_BANKING service and setting up users..."
docker exec oracle-db bash -c "
  sqlplus -S / as sysdba <<'SQL'
  ALTER SESSION SET CONTAINER=CDB\$ROOT;
  
  -- Open the PDB
  BEGIN
    EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE CORPORATE_BANKING OPEN';
  EXCEPTION
    WHEN OTHERS THEN
      BEGIN
        EXECUTE IMMEDIATE 'ALTER PLUGGABLE DATABASE XEPDB1 OPEN';
      EXCEPTION WHEN OTHERS THEN NULL;
      END;
  END;
  /
  
  -- Register the service
  BEGIN
    BEGIN
      DBMS_SERVICE.CREATE_SERVICE('CORPORATE_BANKING', 'CORPORATE_BANKING', aq_ha_notifications => TRUE);
    EXCEPTION WHEN OTHERS THEN NULL;
    END;
    BEGIN
      DBMS_SERVICE.START_SERVICE('CORPORATE_BANKING');
    EXCEPTION WHEN OTHERS THEN NULL;
    END;
  END;
  /
  
  -- Set SYSTEM password in PDB (try CORPORATE_BANKING first, then XEPDB1)
  BEGIN
    EXECUTE IMMEDIATE 'ALTER SESSION SET CONTAINER=CORPORATE_BANKING';
    EXECUTE IMMEDIATE 'ALTER USER SYSTEM IDENTIFIED BY Oracle123';
    EXECUTE IMMEDIATE 'GRANT CONNECT, RESOURCE, DBA TO SYSTEM';
    EXECUTE IMMEDIATE 'GRANT UNLIMITED TABLESPACE TO SYSTEM';
    EXECUTE IMMEDIATE 'CREATE USER banking_user IDENTIFIED BY banking_pass';
    EXECUTE IMMEDIATE 'GRANT CONNECT, RESOURCE TO banking_user';
    EXECUTE IMMEDIATE 'ALTER USER banking_user QUOTA UNLIMITED ON USERS';
  EXCEPTION
    WHEN OTHERS THEN
      BEGIN
        EXECUTE IMMEDIATE 'ALTER SESSION SET CONTAINER=XEPDB1';
        EXECUTE IMMEDIATE 'ALTER USER SYSTEM IDENTIFIED BY Oracle123';
        EXECUTE IMMEDIATE 'GRANT CONNECT, RESOURCE, DBA TO SYSTEM';
        EXECUTE IMMEDIATE 'GRANT UNLIMITED TABLESPACE TO SYSTEM';
      EXCEPTION WHEN OTHERS THEN NULL;
      END;
  END;
  /
  
  EXIT;
SQL
" 2>&1 | grep -v "^$" || echo "Service registration and user setup attempted"

echo ""
echo "Checking service status..."
docker compose ps

echo ""
echo "========================================="
echo "Reset complete!"
echo "========================================="
echo ""
echo "Services should be starting up. Check logs with:"
echo "  docker compose logs -f oracle-db"
echo "  docker compose logs -f authorization-service"
echo "  docker compose logs -f user-service"
echo ""
echo "Once Oracle is ready, the CORPORATE_BANKING service will be registered automatically."

