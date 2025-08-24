
## Fonctionnalités principales

- **Gestion des tâches** : Création, modification, suppression et suivi des tâches
- **Gestion des utilisateurs** : Système d'utilisateurs avec attribution des tâches
- **Statuts et priorités** : Gestion des états (En attente, En cours, Terminée, Annulée) et priorités (Faible, Moyenne, Élevée, Urgente)
- **Interface moderne** : Interface utilisateur responsive avec Bootstrap 5
- **Base de données persistante** : Sauvegarde automatique des données
- **Console H2** : Interface d'administration de la base de données

## Architecture technique

### Backend
- **Framework** : Spring Boot 3.2.0
- **Langage** : Java 17
- **Architecture** : Modèle MVC (Model-View-Controller)
- **Build** : Maven avec wrapper intégré

### Base de données
- **SGBD** : H2 Database (mode fichier persistant)
- **ORM** : Spring Data JPA avec Hibernate
- **Schéma** : Tables `users` et `tasks` avec relation One-to-Many

### Frontend
- **Moteur de templates** : Thymeleaf
- **Framework CSS** : Bootstrap 5
- **Icônes** : Bootstrap Icons
- **Responsive** : Interface adaptée aux différents écrans

### Dépendances principales
- `spring-boot-starter-web` : Framework web Spring
- `spring-boot-starter-thymeleaf` : Moteur de templates
- `spring-boot-starter-data-jpa` : Persistance des données
- `h2database` : Base de données H2
- `spring-boot-devtools` : Outils de développement

## Prérequis système

### Logiciels requis
- **Java** : Version 17 ou supérieure (JDK)
- **Système d'exploitation** : Windows 10/11, macOS, ou Linux
- **Navigateur web** : Chrome, Firefox, Safari, ou Edge (version récente)

### Configuration Java
L'application est configurée pour utiliser Java 17. Assurez-vous que la variable d'environnement `JAVA_HOME` pointe vers votre installation Java 17.

## Installation et démarrage

### Option 1 : Démarrage automatique (Recommandé)
1. Double-cliquez sur le fichier `LANCER.bat`
2. Attendez que le serveur démarre (environ 20 secondes)
3. Le navigateur s'ouvrira automatiquement sur l'application

### Option 2 : Démarrage manuel
1. Ouvrez un terminal dans le répertoire du projet
2. Configurez Java : `set JAVA_HOME=C:\Program Files\Eclipse Adoptium\jdk-17.0.16.8-hotspot`
3. Lancez l'application : `.\mvnw.cmd spring-boot:run`
4. Ouvrez votre navigateur sur `http://localhost:8080`

### Arrêt de l'application
- **Automatique** : Fermez la fenêtre du lanceur
- **Manuel** : Double-cliquez sur `ARRETER.bat` ou utilisez Ctrl+C dans le terminal

## Structure du projet

```
src/
├── main/
│   ├── java/com/example/todoapp/
│   │   ├── controller/          # Contrôleurs web
│   │   ├── model/              # Entités JPA
│   │   ├── repository/         # Interfaces de données
│   │   └── TodoApplication.java # Point d'entrée
│   └── resources/
│       ├── templates/          # Templates Thymeleaf
│       ├── application.properties # Configuration
│       └── data.sql           # Données initiales
├── .mvn/wrapper/              # Wrapper Maven
├── data/                      # Base de données H2
├── LANCER.bat                 # Script de démarrage
├── ARRETER.bat                # Script d'arrêt
└── pom.xml                    # Configuration Maven
```

## Configuration

### Base de données
- **URL** : `jdbc:h2:file:./data/tododb`
- **Utilisateur** : `sa`
- **Mot de passe** : `password`
- **Console H2** : `http://localhost:8080/h2-console`

### Serveur
- **Port** : 8080
- **Contexte** : `/`
- **Mode développement** : Activé avec rechargement automatique

## Utilisation

### Accès à l'application
- **URL principale** : `http://localhost:8080`
- **Page d'accueil** : Vue d'ensemble avec statistiques
- **Gestion des tâches** : `http://localhost:8080/tasks`
- **Gestion des utilisateurs** : `http://localhost:8080/users`

### Fonctionnalités utilisateur
1. **Créer une tâche** : Formulaire avec titre, description, utilisateur assigné, priorité et échéance
2. **Modifier une tâche** : Édition complète des informations
3. **Changer le statut** : Mise à jour rapide du statut (En attente → En cours → Terminée)
4. **Supprimer une tâche** : Suppression avec confirmation
5. **Valider une tâche** : Marquage rapide comme terminée

### Gestion des données
- **Persistance** : Toutes les modifications sont automatiquement sauvegardées
- **Données initiales** : 3 utilisateurs et 3 tâches d'exemple chargées au premier démarrage
- **Relations** : Chaque tâche est associée à un utilisateur


