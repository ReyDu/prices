# PRICES API

Prices Management Service

A robust Spring Boot application implementing Hexagonal Architecture to manage
and query applicable product prices across different retail brands based on precise temporal
criteria and priority rules.
Architecture Overview

The project strictly separates business logic from infrastructure details following Clean
Architecture principles:

    domain: Pure Java business models (Price, Brand) and custom domain exceptions. Completely decoupled from frameworks or persistence mechanics.

    application: Use cases (GetApplicablePrice) orchestrating business rules and defining outbound port interfaces.

    infrastructure: Technical adapters, including Spring Data JPA persistence, MapStruct mappers, and the REST API layer equipped with global exception handling.

Tech Stack

    Java 21

    Spring Boot (Web, Data JPA)

    H2 Database (In-memory persistence for local execution and testing)

    MapStruct & Lombok for clean object mapping and boilerplate reduction

    JUnit 5 & Mockito for isolated unit tests and integration tests

    JaCoCo for code coverage auditing

    Maven for build management

Getting Started
Prerequisites

    Java 21 JDK installed.

    Maven wrapper included in the repository (mvnw).

Running Tests

Execute the full test suite with:
Bash

    mvn clean test

To generate and inspect the JaCoCo code coverage report:
Bash

    mvn clean test jacoco:report

(The HTML report will be available at target/site/jacoco/index.html)

Running the Application

Start the application locally using Maven:
Bash

    mvn spring-boot:run

The service will be accessible on port 8080.

## Docker Support

Build and run the application container using the provided multi-stage Dockerfile:
Bash

    docker build -t prices-app .
    docker run -p 8080:8080 prices-app

## API Documentation & Monitoring

* **Swagger / OpenAPI**: Interactive API documentation is available at
  `http://localhost:8080/swagger-ui/index.html` once the application is running.
