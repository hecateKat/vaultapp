<h1 align="center"> Vault Application </h1>

## Table of Contents

- [Introduction](#introduction)
- [Technology](#technology)
- [UML diagram](#uml-diagram)
- [Docker](#docker)
- [Endpoints](#endpoints)

## Introduction
This is a simple recruitment application built using Spring Boot, Spring Security, and MySQL. It provides basic lofic for managing users, roles, and authentication.
Project used as a reference for recruitment process.
Web infrastructure is built using Spring MVC, and the application is secured with Spring Security.
Objects are persisted in a MySQL database, and the application is packaged as a Docker image for easy deployment.
Database schema management is handled by Liquibase.

## Technology

- **Java**: Programming language used for the application.
- **Spring Boot**: Framework for building the application.
- **Spring Security**: Provides security features for the application.
- **MySQL**: Database used for storing application data.
- **Docker**: Containerization platform used to package the application.
- **Liquibase**: Database schema change management tool used for managing database changes.
- **Maven**: Build tool used for managing dependencies and building the application.
- **JUnit**: Testing framework used for writing unit tests.
- **Mockito**: Mocking framework used for writing unit tests.
- **Swagger**: API documentation tool used for documenting the RESTful APIs of the application.
- **Postman**: Tool used for testing the RESTful APIs of the application.
- **MapStruct**: Object mapping framework used for mapping between different object models.
- **Lombok**: Library used to reduce boilerplate code in Java classes.
- **Spring Boot Test**: Provides testing support for Spring Boot applications.
- **Spring Data JPA**: Provides support for working with databases using JPA (Java Persistence API).

## UML diagram
![img.png](img.png)

## Docker

To run the application in a Docker container, please execute the command `docker compose up` in the root directory of the project.
Prerequisites: Java version, Maven, Docker Compose.
The application will be available at `http://localhost:8080` and the Swagger UI at `http://localhost:8080/swagger-ui/index.html`.

Local run (without Docker):
```bash
mvn clean package
java -jar target/bookstore-app.jar
```

Running tests:
```bash
mvn test
```

## Endpoints

![img_1.png](img_1.png)