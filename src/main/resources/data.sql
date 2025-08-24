-- Insertion des utilisateurs (seulement si ils n'existent pas)
INSERT INTO users (first_name, last_name, email) 
SELECT 'Jean', 'Dupont', 'jean.dupont@email.com'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'jean.dupont@email.com');

INSERT INTO users (first_name, last_name, email) 
SELECT 'Marie', 'Martin', 'marie.martin@email.com'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'marie.martin@email.com');

INSERT INTO users (first_name, last_name, email) 
SELECT 'Pierre', 'Durand', 'pierre.durand@email.com'
WHERE NOT EXISTS (SELECT 1 FROM users WHERE email = 'pierre.durand@email.com');

-- Insertion des tâches (seulement si elles n'existent pas)
INSERT INTO tasks (title, description, status, priority, created_at, due_date, user_id) 
SELECT 'Réunion équipe', 'Préparer la présentation pour la réunion de demain', 'PENDING', 'HIGH', CURRENT_TIMESTAMP, DATEADD('DAY', 1, CURRENT_TIMESTAMP), 
       (SELECT id FROM users WHERE email = 'jean.dupont@email.com' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM tasks WHERE title = 'Réunion équipe');

INSERT INTO tasks (title, description, status, priority, created_at, due_date, user_id) 
SELECT 'Développement API', 'Implémenter les endpoints REST', 'IN_PROGRESS', 'URGENT', CURRENT_TIMESTAMP, DATEADD('DAY', 3, CURRENT_TIMESTAMP),
       (SELECT id FROM users WHERE email = 'marie.martin@email.com' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM tasks WHERE title = 'Développement API');

INSERT INTO tasks (title, description, status, priority, created_at, due_date, user_id) 
SELECT 'Tests unitaires', 'Écrire les tests pour les services', 'PENDING', 'MEDIUM', CURRENT_TIMESTAMP, DATEADD('DAY', 5, CURRENT_TIMESTAMP),
       (SELECT id FROM users WHERE email = 'jean.dupont@email.com' LIMIT 1)
WHERE NOT EXISTS (SELECT 1 FROM tasks WHERE title = 'Tests unitaires');
