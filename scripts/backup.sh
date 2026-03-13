#!/bin/bash

# Configuration
CONTAINER_NAME="race_postgres"
DB_NAME="race_db"
DB_USER="race"
BACKUP_FILE="scripts/backup.sql"

echo "⏳ Sauvegarde de la base de données vers $BACKUP_FILE..."

# Exécution du dump via docker exec
docker exec $CONTAINER_NAME pg_dump -U $DB_USER --clean --if-exists $DB_NAME > "$BACKUP_FILE"

if [ $? -eq 0 ]; then
    echo "✅ Sauvegarde terminée avec succès !"
else
    echo "❌ Une erreur est survenue lors de la sauvegarde."
    exit 1
fi
