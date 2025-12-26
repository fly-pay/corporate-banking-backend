# Oracle Database Initialization Scripts

This directory contains scripts that automatically configure the Oracle database when it's first initialized (when the Docker volume is empty).

## Scripts

1. **00-register-service.sh** - Registers the `CORPORATE_BANKING` PDB service with the Oracle listener. This is the main script that ensures the service is available for connections.

2. **01-register-pdb-service.sql** - SQL script that also registers the service (backup method).

3. **02-setup-user.sql** - Sets up database user permissions.

4. **03-startup-register.sh** - Legacy script (kept for reference).

## How It Works

When you run `docker compose up --build -d` with a fresh volume:

1. The `gvenzl/oracle-xe:21-slim` image creates the database and PDB
2. Scripts in `/container-entrypoint-initdb.d/` are executed automatically
3. The `CORPORATE_BANKING` service is registered with the listener
4. Your applications can connect using: `jdbc:oracle:thin:@oracle-db:1521/corporate_banking`

## Resetting the Database

To completely reset the database and start fresh:

```bash
# Option 1: Use the helper script
./reset-database.sh

# Option 2: Manual steps
docker compose down
docker volume rm corporate-banking-backend_oracle-data
docker compose up --build -d
```

## Notes

- The initialization scripts only run when the database volume is empty (first time setup)
- If you need to re-register the service after the database is already initialized, you can manually run the registration script:
  ```bash
  docker exec oracle-db /container-entrypoint-initdb.d/00-register-service.sh
  ```

