
---

# API Documentation — Level 2: Hello API (Spring Boot, JDK 21)

## 1) Introduction

This example demonstrates a **minimal yet professional REST API** built with **Spring Boot** and **Java 21**.
It illustrates:

* Exposing HTTP endpoints (`GET` and `POST`)
* Handling JSON requests and responses
* Input validation
* Separation of **Controller → Service → DTO**
* Global error handling with `@RestControllerAdvice`

---

## 2) Implementation Overview

### 2.1 Architecture

```
src/main/java/com/example/demo/
├─ DemoApplication.java
├─ controller/HelloController.java
├─ service/HelloService.java
├─ dto/UserRequest.java
├─ dto/GreetingResponse.java
└─ exception/
   ├─ GlobalExceptionHandler.java
   └─ ApiError.java
```

* **Controller**: Handles HTTP requests and calls the service layer
* **Service**: Contains business logic
* **DTOs**: Immutable `record`s (`UserRequest`, `GreetingResponse`)
* **Exception Handling**: `GlobalExceptionHandler` returns structured JSON errors

---

### 2.2 Key Concepts

| Concept                        | Purpose                                        |
| ------------------------------ | ---------------------------------------------- |
| `@SpringBootApplication`       | Main entry point, enables auto-configuration   |
| `@RestController`              | REST controller returning JSON responses       |
| `@RequestMapping("/api")`      | Prefix for all controller endpoints            |
| `@GetMapping` / `@PostMapping` | Maps HTTP methods to controller methods        |
| `@PathVariable`                | Extracts path parameters from URL              |
| `@RequestParam`                | Extracts query parameters                      |
| `@RequestBody` + `@Valid`      | Binds JSON body to DTO and triggers validation |
| `record`                       | Immutable DTOs (Java 21 feature)               |
| `Service Layer`                | Separates business logic from controller       |
| `@RestControllerAdvice`        | Handles exceptions globally                    |
| `ResponseEntity`               | Custom HTTP status codes and headers           |

---

## 3) Endpoints & Example Requests

| HTTP Method | Endpoint            | Description                             | Example `curl`                                                                                                      |
| ----------- | ------------------- | --------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| GET         | `/api/hello`        | Simple greeting message                 | `curl http://localhost:8080/api/hello`                                                                              |
| GET         | `/api/hello/{name}` | Personalized greeting                   | `curl http://localhost:8080/api/hello/Ismail`                                                                        |
| GET         | `/api/greet`        | Greeting with query param `name`        | `curl "http://localhost:8080/api/greet?name=Bob"`                                                                   |
| POST        | `/api/hello`        | Create greeting from JSON `UserRequest` | `curl -X POST -H "Content-Type: application/json" -d '{"name":"Charlie","age":30}' http://localhost:8080/api/hello` |
| GET         | `/api/status`       | Service status with custom header       | `curl -i http://localhost:8080/api/status`                                                                          |

---

### 3.1 Sample Responses

**GET /api/hello**

```json
{
  "message": "Hello, World! Welcome to REST APIs!"
}
```

**GET /api/hello/Ismail**

```json
{
  "message": "Hello, Ismail! Nice to meet you!"
}
```

**POST /api/hello**

```json
{
  "message": "Created greeting for: Charlie (Age: 30)"
}
```

**GET /api/status**

```json
{
  "message": "Service is running smoothly!"
}
```

**Validation Error Example (POST /api/hello)**

Request body:

```json
{
  "name": "",
  "age": -5
}
```

Response (HTTP 400):

```json
{
  "timestamp": "2025-09-12T12:34:56.789",
  "status": 400,
  "error": "Validation Error",
  "message": "must not be blank; must be greater than or equal to 0",
  "path": "/api/hello"
}
```

---

## 4) Notes

* API validation is handled by **Bean Validation** (`@NotBlank`, `@Min`).
* Error responses are standardized with **`ApiError` record**.
* JSON output is formatted for readability (`spring.jackson.serialization.indent-output=true`).

---

## 5) Conclusion

This Level 2 API shows a **professional and maintainable REST API structure**:

* Controller / Service / DTO separation
* Input validation and global error handling
* Use of modern Java 21 features (`record`)

It can be used as a **starting point for larger, production-grade APIs**.

---