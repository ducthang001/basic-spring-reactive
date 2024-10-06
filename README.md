# Getting Started

### Reference Documentation

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/3.3.4/maven-plugin)
* [Create an OCI image](https://docs.spring.io/spring-boot/3.3.4/maven-plugin/build-image.html)
* [Spring Reactive Web](https://docs.spring.io/spring-boot/docs/3.3.4/reference/htmlsingle/index.html#web.reactive)

Here’s a detailed README tutorial for a Spring Reactive project, formatted for a GitHub repository. It includes instructions for setting up, running, and understanding the key components of the project:

---

# Spring Reactive Tutorial

Welcome to the **Spring Reactive** tutorial! This project is designed to introduce you to reactive programming using **Spring WebFlux**.

## Table of Contents

- [Overview](#overview)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Getting Started](#getting-started)
- [Running the Project](#running-the-project)
- [Endpoints](#endpoints)
- [Contributing](#contributing)
- [License](#license)

## Overview

This project demonstrates how to build a non-blocking, reactive web service using **Spring WebFlux**. We will be using:

- **Reactive Streams** to handle asynchronous data
- **Spring Data Reactive Repositories** to interact with a non-blocking database
- **WebFlux** for reactive endpoints

The key components include:

- Reactive controllers
- Mono and Flux for data streams
- Reactive database interactions (MongoDB, for this tutorial)

## Prerequisites

Before you begin, make sure you have the following installed on your machine:

- [Java 17+](https://www.oracle.com/java/technologies/javase-jdk17-downloads.html)
- [Maven 3.6+](https://maven.apache.org/install.html)
- [MongoDB](https://www.mongodb.com/try/download/community) (or any reactive-supporting database)

## Project Structure

Here’s a basic overview of the project structure:

```
spring-reactive-tutorial
│
├── src
│   ├── main
│   │   ├── java
│   │   │   └── com.example.reactive
│   │   │       ├── controller
│   │   │       │   └── ReactiveController.java
│   │   │       ├── model
│   │   │       │   └── Item.java
│   │   │       ├── repository
│   │   │       │   └── ItemRepository.java
│   │   │       └── service
│   │   │           └── ItemService.java
│   │   └── resources
│   │       └── application.yml
│   └── test
│       └── java
│           └── com.example.reactive
├── pom.xml
└── README.md
```

- **ReactiveController.java**: Handles the incoming HTTP requests reactively.
- **Item.java**: Model class representing the data structure.
- **ItemRepository.java**: Interface for reactive CRUD operations.
- **ItemService.java**: Business logic for item-related actions.
- **application.yml**: Configuration file for database and server settings.

## Getting Started


### 1. Set Up MongoDB

Make sure MongoDB is running on your machine. You can modify the connection string in the `application.yml` file if needed:

```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/reactive_db
```

### 2. Build the Project

```bash
mvn clean install
```

### 3. Run the Application

```bash
mvn spring-boot:run
```

By default, the application will start on `http://localhost:8080`.

## Endpoints

Here are the main REST endpoints provided by the service:

| Method | Endpoint          | Description            |
|--------|-------------------|------------------------|
| GET    | `/items`           | Get all items          |
| GET    | `/items/{id}`      | Get an item by ID      |
| POST   | `/items`           | Create a new item      |
| PUT    | `/items/{id}`      | Update an existing item|
| DELETE | `/items/{id}`      | Delete an item         |

### Example Request: Fetch All Items

```bash
curl -X GET http://localhost:8080/items
```

### Example Response:

```json
[
    {
        "id": "1",
        "name": "Reactive Book",
        "price": 25.99
    },
    {
        "id": "2",
        "name": "Spring WebFlux Guide",
        "price": 39.99
    }
]
```

## Running Tests

To run tests:

```bash
mvn test
```

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request.


---
