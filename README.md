# Tourist Agency Management System

A full-stack web application developed as an academic project for managing data within a tourist agency. The application provides a web interface for working with tourist agencies, destinations, hotels, and travel arrangements.

The system consists of an Angular frontend, a Spring Boot REST API, and a PostgreSQL relational database.

## Features

- View and manage tourist agencies
- Manage destinations and their information
- Manage hotels associated with destinations
- Manage travel arrangements
- Create, update, and delete records
- Search and filter application data
- Display related data between entities
- Communicate between the Angular frontend and Spring Boot backend through REST APIs
- Store and retrieve application data from a PostgreSQL database

## Technologies

### Frontend

- Angular
- TypeScript
- HTML
- CSS
- Angular Material
- RxJS

### Backend

- Java
- Spring Boot
- Spring Data JPA
- REST API
- Maven

### Database

- PostgreSQL
- pgAdmin

## Application Architecture

The project is divided into two main parts:

```text
tourist-agency-management-system/
├── BackendProject/
│   ├── src/
│   ├── pom.xml
│   └── ...
│
└── FrontendProject/
    ├── src/
    ├── angular.json
    ├── package.json
    └── ...
```

The **frontend** is responsible for the user interface and communicates with the backend through HTTP requests.

The **backend** exposes REST endpoints, contains the application logic, and communicates with the PostgreSQL database using Spring Data JPA.

## Main Entities

The application works with four main entities:

- Tourist Agency
- Destination
- Hotel
- Travel Arrangement

These entities are represented in both the backend and frontend parts of the application and are connected through the application's data model.

## Backend

The Spring Boot backend is organized into layers for:

- Models / entities
- Repositories
- Services
- REST controllers

Spring Data JPA is used for database access and persistence.

The backend also contains initial database data that can be loaded when the application starts.

## Frontend

The Angular frontend contains:

- Components for displaying and managing application data
- Dialog components for adding, editing, and deleting records
- Models representing application entities
- Services responsible for communication with the REST API
- Angular Material components for the user interface

## Running the Application

### Database

Create a PostgreSQL database and configure the database connection in:

```text
BackendProject/src/main/resources/application.properties
```

Make sure PostgreSQL is running before starting the backend.

### Backend

Navigate to:

```text
BackendProject
```

Run the Spring Boot application from your IDE or using Maven:

```bash
./mvnw spring-boot:run
```

On Windows:

```bash
mvnw.cmd spring-boot:run
```

### Frontend

Navigate to:

```text
FrontendProject
```

Install the required dependencies:

```bash
npm install
```

Start the Angular development server:

```bash
ng serve
```

Then open the local Angular application in your browser.

## Academic Context

This project was developed as an individual academic project at the **Faculty of Technical Sciences, University of Novi Sad**.

The project demonstrates the development of a full-stack application using an **Angular frontend, Spring Boot REST backend, and PostgreSQL relational database**.
