
---

# Documentation — Level 1: **Hello** API (, )

> **Goal:** Clearly document the minimal implementation of a "Hello" REST API so you understand the key points, how to run it, and how to solve common issues.

---

## 1) Overview

This Level 1 step aims to build a minimal HTTP API using Spring Boot:

* a small `@RestController` exposing `GET` and `POST` endpoints
* running with Spring Boot (via Maven or an IDE)
* accepting and returning JSON / plain text

You are using **JDK 21** — good: we’ll later use modern features (like `record`), but for this level the implementation stays simple.

---

## 2) Prerequisites

* JDK 21 installed (check with `java -version`).
* &#x20;(or `./mvnw` wrapper). Check with `mvn -v`.
* An IDE ( /  / ) is recommended but optional.
* Spring Boot (web starter) declared in `pom.xml`.

> Tip: if you see an error like `release version 21 not supported`, it often means Maven is using an older JDK or the `maven-compiler-plugin` is not configured for Java 21.

---

## 3) Recommended Project Structure (example)

```
src/
└─ main/
   └─ java/
      └─ com/example/demo/
         ├─ DemoApplication.java
         └─ controller/
            └─ HelloController.java

src/
└─ main/
   └─ resources/
      └─ application.properties
```

---

## 4) Key Code (examples)

### `HelloController.java`

```java
package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World! Welcome to REST APIs!";
    }

    @GetMapping("/hello/{name}")
    public String sayHelloTo(@PathVariable String name) {
        return "Hello, " + name + "! Nice to meet you!";
    }

    @GetMapping("/greet")
    public String greetWithParam(@RequestParam String name) {
        return "Greetings, " + name + "! How are you today?";
    }

    @PostMapping("/hello")
    public String createGreeting(@RequestBody User user) {
        return "Created greeting for: " + user.getName() + " (Age: " + user.getAge() + ")";
    }

    @GetMapping("/status")
    public ResponseEntity<String> checkStatus() {
        return ResponseEntity.ok()
                .header("Custom-Header", "my-value")
                .body("Service is running smoothly!");
    }

    public static class User {
        private String name;
        private int age;

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }
}
```

### `DemoApplication.java`

```java
package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DemoApplication {
    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }
}
```

### `application.properties` (example)

```properties
spring.application.name=demo
server.port=8080
# Warning: if you keep this, your controller must NOT have
# the /api prefix in @RequestMapping (otherwise you’ll get /api/api/...)
# server.servlet.context-path=/api
logging.level.com.example.demo=DEBUG
spring.jackson.serialization.indent-output=true
```

---

## 5) Key Concepts in the Code (theory)

| Element                       | Role               | Explanation                                                                          |
| ----------------------------- | ------------------ | ------------------------------------------------------------------------------------ |
| `@SpringBootApplication`      | Entry point        | Marks the main class; enables auto-configuration and component scanning.             |
| `SpringApplication.run(...)`  | Startup            | Boots the application and initializes the Spring context.                            |
| `@RestController`             | REST Controller    | Declares that this class handles HTTP requests and returns response bodies directly. |
| `@RequestMapping("/api")`     | Route prefix       | Adds a common `/api` prefix to all routes in this controller.                        |
| `@GetMapping`, `@PostMapping` | Endpoints          | Map methods to routes and HTTP methods (`GET`, `POST`…).                             |
| `@PathVariable`               | URL parameter      | Injects a part of the URL path into a method argument.                               |
| `@RequestParam`               | Query parameter    | Injects a key/value from the query string into a method argument.                    |
| `@RequestBody`                | JSON body          | Automatically binds the JSON body of the request to a Java object.                   |
| `ResponseEntity`              | Full HTTP response | Allows customizing the HTTP status code and headers.                                 |

---

## 6) Exposed Endpoints (table)

| Method | Path                  | Description                           | Example curl                                                                                                        |
| ------ | --------------------- | ------------------------------------- | ------------------------------------------------------------------------------------------------------------------- |
| GET    | `/api/hello`          | Simple message                        | `curl http://localhost:8080/api/hello`                                                                              |
| GET    | `/api/hello/{name}`   | Personalized message                  | `curl http://localhost:8080/api/hello/Alice`                                                                        |
| GET    | `/api/greet?name=Bob` | Message using query param             | `curl "http://localhost:8080/api/greet?name=Bob"`                                                                   |
| POST   | `/api/hello`          | Creates/processes JSON `{name, age}`  | `curl -X POST -H "Content-Type: application/json" -d '{"name":"Charlie","age":30}' http://localhost:8080/api/hello` |
| GET    | `/api/status`         | Checks service status + custom header | `curl -i http://localhost:8080/api/status`                                                                          |

### Expected responses (examples)

* `GET /api/hello` → `Hello, World! Welcome to REST APIs!`
* `GET /api/hello/Alice` → `Hello, Alice! Nice to meet you!`
* `POST /api/hello` → `Created greeting for: Charlie (Age: 30)`

---

## 7) How to Run the Application

* From the command line (using Maven wrapper if present):

  * `./mvnw spring-boot:run` (Linux/Mac)
  * `mvnw.cmd spring-boot:run` (Windows)
  * or `mvn spring-boot:run` if Maven is installed globally
* Or build + run as executable jar:

  * `mvn package`
  * `java -jar target/demo-0.0.1-SNAPSHOT.jar`
* Or from the IDE: run `DemoApplication.main()`

---

## 8) Common Issues & Troubleshooting

* **404 on `/api/hello` while the app is running:**

  * Check if you have a **double `/api`** (controller + `server.servlet.context-path=/api`).
  * Check the port (the app might be running on a different port if `server.port` changed).

* **`release version 21 not supported`:**

  * Maven is using a JDK < 21; check `mvn -v`.
  * Configure `java.version` and the `maven-compiler-plugin` with `<release>21</release>`.

* **Port already in use:** error on startup; change `server.port`.

* **POST JSON not binding correctly:**

  * Make sure the request includes `Content-Type: application/json`.
  * Make sure your `User` class has public getters/setters (or use a `record`).

---

## 9) `pom.xml` — useful snippets for Java 21

```xml
<properties>
  <java.version>21</java.version>
  <spring.boot.version>3.2.0</spring.boot.version> <!-- adapt according to your project -->
</properties>

<build>
  <plugins>
    <!-- Spring Boot Plugin -->
    <plugin>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-maven-plugin</artifactId>
    </plugin>
    <!-- Compiler Plugin for Java 21 -->
    <plugin>
        <groupId>org.apache.maven.plugins</groupId>
        <artifactId>maven-compiler-plugin</artifactId>
        <version>3.13.0</version>
        <configuration>
            <release>21</release>
        </configuration>
    </plugin>
  </plugins>
</build>
```

> Note: adjust `spring.boot.version` to match your setup. The key point is to configure the `maven-compiler-plugin` for Java 21 and run Maven with JDK 21.

---

## 10) Best Practices (even at Level 1)

* Separate controller / service as soon as logic grows beyond one line.
* Don’t expose persistent entities directly (use DTOs once you add persistence).
* Use `ResponseEntity<>` when you need control over HTTP status and headers.
* Add basic unit and integration tests for main endpoints.
* Provide at least minimal documentation (README or OpenAPI later on).

---

## 11) Next Steps (what we’ll do in Level 2)

* Extract logic into a `HelloService`.
* Return DTOs (`record`) instead of `String`.
* Add validation (`@Valid`, `@NotBlank`) on parameters and request bodies.
* Centralize error handling with `@RestControllerAdvice`.
* Add ** to generate interactive documentation.

---

## 12) Checklist to Validate Level 1

* [ ] Application starts without errors
* [ ] `GET /api/hello` returns the expected message
* [ ] `GET /api/hello/{name}` works
* [ ] `POST /api/hello` accepts and parses JSON
* [ ] Basic unit tests added
* [ ] `application.properties` has no conflicting `context-path`

---