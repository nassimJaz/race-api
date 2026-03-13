-- Dump de la base de données race_db

-- Nettoyage (optionnel)
DROP TABLE IF EXISTS registration CASCADE;
DROP TABLE IF EXISTS race CASCADE;
DROP TABLE IF EXISTS runner CASCADE;

-- Création Runner
CREATE TABLE runner (
     id SERIAL PRIMARY KEY,
     first_name VARCHAR(100) NOT NULL,
     last_name VARCHAR(100) NOT NULL,
     email VARCHAR(255) UNIQUE NOT NULL,
     age INTEGER NOT NULL
);

-- Création Race
CREATE TABLE race (
     id SERIAL PRIMARY KEY,
     name VARCHAR(255) NOT NULL,
     date DATE NOT NULL,
     location VARCHAR(255) NOT NULL,
     max_participants INTEGER NOT NULL
);

-- Création Registration
CREATE TABLE registration (
    id SERIAL PRIMARY KEY,
    runner_id INTEGER NOT NULL,
    race_id INTEGER NOT NULL,
    registration_date DATE NOT NULL,
    CONSTRAINT fk_runner FOREIGN KEY (runner_id) REFERENCES runner(id),
    CONSTRAINT fk_race FOREIGN KEY (race_id) REFERENCES race(id),
    CONSTRAINT unique_registration UNIQUE (runner_id, race_id)
);

-- Données d'exemple
INSERT INTO runner (first_name, last_name, email, age) VALUES 
('Nassim', 'Jazouli', 'nassim.jazouli@epfedu.fr', 21),
('Bob', 'Durand', 'bob.durand@example.com', 45);

INSERT INTO race (name, date, location, max_participants) VALUES 
('Marathon de Paris', '2026-04-05', 'Paris', 50000),
('Semi de Lyon', '2026-03-20', 'Lyon', 15000);

INSERT INTO registration (runner_id, race_id, registration_date) VALUES 
(1, 1, '2026-01-10'),
(2, 2, '2026-01-15');
