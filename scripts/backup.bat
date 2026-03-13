@echo off
set CONTAINER_NAME=race_postgres
set DB_NAME=race_db
set DB_USER=race
set BACKUP_FILE=scripts\backup.sql

echo ⏳ Sauvegarde de la base de données vers %BACKUP_FILE%...

docker exec %CONTAINER_NAME% pg_dump -U %DB_USER% --clean --if-exists %DB_NAME% > %BACKUP_FILE%

if %ERRORLEVEL% equ 0 (
    echo ✅ Sauvegarde terminee avec succes !
) else (
    echo ❌ Une erreur est survenue lors de la sauvegarde.
    exit /b 1
)
