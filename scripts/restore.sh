#!/bin/bash

# Configuration
CONTAINER_NAME="race_postgres"
DB_NAME="race_db"
DB_USER="race"
BACKUP_FILE="scripts/backup.sql"

echo "⏳ Restauration de la base de données depuis $BACKUP_FILE..."

# Vérifier si le fichier existe
if [ ! -f "$BACKUP_FILE" ]; then
    echo "❌ Erreur : Le fichier $BACKUP_FILE n'existe pas."
    exit 1
fi

# Exécution de la restauration via docker exec
cat "$BACKUP_FILE" | docker exec -i $CONTAINER_NAME psql -U $DB_USER -d $DB_NAME

if [ $? -eq 0 ]; then
    echo "✅ Restauration terminée avec succès !"
else
    echo "❌ Une erreur est survenue lors de la restauration."
    exit 1
fi
