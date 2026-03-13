# API de Gestion de Courses Sportives

Cette API REST permet de gérer des coureurs, des courses et les inscriptions.

## 🚀 Lancement rapide (Docker)

Le projet est entièrement dockerisé pour faciliter son lancement.

### 1. Démarrer l'application
Lancez l'API et la base de données avec une seule commande :

```bash
docker compose up -d
```

L'API sera accessible sur [http://localhost:8080](http://localhost:8080).

### 2. Initialiser la base de données
Pour injecter les données d'exemple (Nassim, Bob, Marathon de Paris...), utilisez les scripts fournis :

- **Linux / macOS** :
  ```bash
  ./scripts/restore.sh
  ```
- **Windows** :
  ```cmd
  scripts\restore.bat
  ```

---

## 🛠 Documentation de l'API

### 🏃‍♂️ Coureurs (`/runners`)
- `GET /runners` : Lister tous les coureurs.
- `GET /runners/{id}` : Récupérer un coureur par son ID.
- `POST /runners` : Créer un nouveau coureur.
- `PUT /runners/{id}` : Modifier un coureur existant.
- `DELETE /runners/{id}` : Supprimer un coureur.
- `GET /runners/{id}/races` : Lister les courses auxquelles un coureur est inscrit.

### 🏁 Courses (`/races`)
- `GET /races` : Lister toutes les courses.
- `GET /races/{id}` : Récupérer une course par son ID.
- `POST /races` : Créer une nouvelle course.
- `GET /races/{id}/registrations` : Lister les coureurs inscrits à une course.
- `GET /races/{id}/participants/count` : Obtenir le nombre de participants inscrits à une course.

### 📝 Inscriptions
- `POST /races/{raceId}/registrations` : Inscrire un coureur à une course.
  - Body JSON : `{"runnerId": 1}`
  - Réponses : `201 Created`, `404 Not Found`, `409 Conflict` (si déjà inscrit ou course complète).

---

## 🔍 Outils de gestion
- **Adminer** (Interface DB) : [http://localhost:8081](http://localhost:8081)
  - Serveur : `race_postgres` | User/Pass/DB : `race`
- **Sauvegarde** : Lancez `./scripts/backup.sh` pour sauvegarder l'état actuel de la DB dans `scripts/backup.sql`.
