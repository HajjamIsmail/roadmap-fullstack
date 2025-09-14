
---

# 📝 Notes API Project Documentation

## 1️⃣ Introduction

This project demonstrates the creation of a **complete REST API for managing notes**.
The main objectives are to:

* Perform Create, Read, Update, and Delete operations (**CRUD**) on notes.
* Use **Spring Boot 3.4** to expose REST endpoints.
* Persist data using **PostgreSQL**.
* Utilize **Java 21 features** such as records, switch expressions, and streams.
* Structure a project following **best practices** in Java and Spring development.

This mini-project provides a clear example of Spring concepts, JPA, and database communication in practice.

---

## 2️⃣ Technologies and Versions Used

| Technology                  | Version         | Role                                                                               |
| --------------------------- | --------------- | ---------------------------------------------------------------------------------- |
| Java (JDK)                  | 21              | Primary language; using new features like records, switch expressions, and streams |
| Spring Boot                 | 3.4.10-SNAPSHOT | Framework to create the REST API                                                   |
| Maven                       | 3.3.9           | Dependency and build management                                                    |
| PostgreSQL                  | 17.x            | Relational database management system                                              |
| HikariCP                    | 5.1.0           | JDBC connection pool                                                               |
| Hibernate / Spring Data JPA | 6.6.x / 3.2.x   | ORM to manage entities and persistence                                             |

---

## 3️⃣ Project Architecture

The project follows **best practices for Spring Boot**:

```
notes-api/
│
├─ src/main/java/com/example/notes/
│   ├─ controller/      # Handles REST endpoints
│   │   └─ NoteController.java
│   ├─ service/         # Business logic
│   │   └─ NoteService.java
│   ├─ repository/      # Database access
│   │   └─ NoteRepository.java
│   ├─ entity/          # JPA entities
│   │   └─ Note.java
│   └─ dto/             # Data Transfer Objects
│       └─ NoteDTO.java
│
├─ src/main/resources/
│   └─ application.properties  # Project and database configuration
│
└─ pom.xml               # Maven dependencies and build configuration
```

### 🔹 Package Roles

1. **controller**:

    * Contains classes exposing REST endpoints.
    * Receives HTTP requests, calls services, and returns JSON responses.

2. **service**:

    * Contains business logic (CRUD, transformations, validations).
    * Calls the repository to interact with the database.

3. **repository**:

    * Interface extending `JpaRepository` to manage entities using Spring Data JPA.
    * No manual implementation needed for basic CRUD operations.

4. **entity**:

    * Defines the data structure persisted in the database.
    * Uses JPA annotations like `@Entity` and `@Id`.

5. **dto**:

    * Contains objects used for transferring data over the API.
    * Decouples the API from internal entities for security and flexibility.

---

## 4️⃣ Keywords and Annotations Used

| Keyword / Annotation                                           | Explanation                                         |
| -------------------------------------------------------------- | --------------------------------------------------- |
| `@RestController`                                              | Marks the class as a REST controller returning JSON |
| `@RequestMapping`                                              | Defines the base URL path for endpoints             |
| `@PostMapping`, `@GetMapping`, `@PutMapping`, `@DeleteMapping` | HTTP methods for CRUD operations                    |
| `@PathVariable`                                                | Maps URL path variable to a method parameter        |
| `@RequestBody`                                                 | Maps JSON request body to a Java object             |
| `@Service`                                                     | Marks a service class for business logic            |
| `@Entity`                                                      | Marks a class as a database entity                  |
| `@Id`, `@GeneratedValue`                                       | Defines the primary key and auto-increment strategy |
| `record` (Java 21)                                             | Immutable DTO structure for concise code            |
| `switch` expression (Java 21)                                  | Compact syntax for conditional logic                |
| `Streams` (Java 21)                                            | Functional style collection manipulation            |

---

## 5️⃣ PostgreSQL Configuration

### 1. Create Database and User

Open `psql` or PgAdmin and execute:

```sql
-- Create the database
CREATE DATABASE notes_db;

-- Create the user
CREATE USER notes_user WITH PASSWORD '1234';

-- Grant all privileges on the database
GRANT ALL PRIVILEGES ON DATABASE notes_db TO notes_user;
```

---

### 2. Configure `application.properties`

```properties
spring.application.name=notes

spring.datasource.url=jdbc:postgresql://localhost:5432/notes_db
spring.datasource.username=notes_user
spring.datasource.password=1234

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

server.port=8080
```

> **Role of `application.properties`**:
>
> * Holds database, server, logging, and Spring configuration.
> * Allows changing parameters without modifying code.

---

## 6️⃣ Conclusion

* This project demonstrates a **fully functional REST CRUD API** using Spring Boot, PostgreSQL, and Java 21.
* Follows **best practices**: separation of controller/service/repository, usage of DTOs, exception handling, readable code.
* Leverages **modern Java 21 features** (records, switch expressions, streams) for cleaner and concise code.
* Can be extended easily to include **pagination, security, unit testing**, or other advanced features.

---
