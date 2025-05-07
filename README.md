# Gestion de Conférences - Spring Boot

Ce projet est une application web développée avec **Spring Boot** permettant de gérer des conférences, les thématiques associées, les activités proposées, ainsi que l'inscription des participants.

## Fonctionnalités principales

- Création et consultation de conférences
- Inscription d'un participant connecté à une conférence
- Choix des thématiques et activités lors de l'inscription
- Consultation des conférences auxquelles un participant est inscrit
- Interface sécurisée pour les participants

## Stack technique

- Java 17
- Spring Boot 3
- Spring Data JPA (avec Hibernate comme ORM)
- Thymeleaf
- SQLite
- HTML/CSS

## Lancer l'application

Lancer simplement la classe `MainApplication.java` (annotée avec `@SpringBootApplication`) depuis votre IDE.

Accès via : `http://localhost:8080`

## Structure

- `entity/` : Entités JPA (Conference, Participant, Activite, Thematique…)
- `repository/` : Interfaces Spring Data JPA
- `service/` : Couches de service pour la logique métier
- `controller/` : Contrôleurs web
- `templates/` : Vues Thymeleaf
- `static/` : Fichiers CSS et ressources statiques
