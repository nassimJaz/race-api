@echo off
set CONTAINER_NAME=race_postgres
set DB_NAME=race_db
set DB_USER=race
set BACKUP_FILE=scripts\backup.sql

echo ⏳ Restauration de la base de données depuis %BACKUP_FILE%...

if not exist %BACKUP_FILE% (
    echo ❌ Erreur : Le fichier %BACKUP_FILE% n'existe pas.
    exit /b 1
)

type %BACKUP_FILE% | docker exec -i %CONTAINER_NAME% psql -U %DB_USER% -d %DB_NAME%

if %ERRORLEVEL% equ 0 (
    echo ✅ Restauration terminee avec succes !
) else (
    echo ❌ Une erreur est survenue lors de la restauration.
    exit /b 1
)
