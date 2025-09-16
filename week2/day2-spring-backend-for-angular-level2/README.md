
---

# **Document — Backend: Spring Boot + PostgreSQL (CRUD User Management)**

## 1️⃣ Introduction

This backend application demonstrates a **complete CRUD (Create, Read, Update, Delete)** implementation using **Spring Boot**, **JPA/Hibernate**, and **PostgreSQL**.

Learning objectives:

* Understand Spring Boot project architecture
* Map entities with JPA
* Manage persistence with PostgreSQL
* Expose a REST API for consumption by an Angular frontend

---

## 2️⃣ Project Structure

```
src/main/java/com/example/demo/
│
├── DemoApplication.java          # Spring Boot entry point
├── entity/
│   └── User.java                 # JPA entity representing the users table
├── repository/
│   └── UserRepository.java       # DAO interface
├── service/
│   └── UserService.java          # Business logic
└── controller/
    └── UserController.java       # REST API
```

---

## 3️⃣ Code and Explanations

### a) `User` Entity

```java
@Entity
@Table(name = "users") // "users" avoids the reserved keyword "user"
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String email;

    // getters and setters
}
```

**Keywords and explanations:**

* `@Entity`: marks the class as a **JPA persistent entity**
* `@Table(name="users")`: specifies the database table name
* `@Id`: primary key
* `@GeneratedValue(strategy=GenerationType.IDENTITY)`: auto-generates IDs (auto-increment)
* `private Long id`, `private String name`, `private String email`: persistent fields

**Best practices:**

* Avoid PostgreSQL reserved keywords (`user`) for table names
* Use `Long` for ID with `GenerationType.IDENTITY`

---

### b) Repository / DAO

```java
@Repository
public interface UserRepository extends JpaRepository<User, Long> {}
```

**Keywords and explanations:**

* `@Repository`: marks the bean as a DAO and enables automatic exception translation
* `JpaRepository<User, Long>`: Spring Data JPA interface providing **automatic CRUD operations**

**Best practices:**

* Avoid writing basic CRUD queries manually; let `JpaRepository` handle it
* Add custom queries only if necessary

---

### c) Service Layer

```java
@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository repo) {
        this.userRepository = repo;
    }

    public List<User> getAllUsers() { return userRepository.findAll(); }

    public User getUser(Long id) { return userRepository.findById(id).orElse(null); }

    public User createUser(User user) { return userRepository.save(user); }

    public User updateUser(Long id, User user) {
        User existing = userRepository.findById(id).orElseThrow();
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return userRepository.save(existing);
    }

    public void deleteUser(Long id) { userRepository.deleteById(id); }
}
```

**Keywords and explanations:**

* `@Service`: marks the class as **business logic**
* `userRepository.findAll()`: fetch all users
* `userRepository.findById(id)`: fetch a user by ID
* `userRepository.save(entity)`: insert or update a user
* `deleteById(id)`: delete a user

**Best practices:**

* Never put business logic in the Controller
* Always handle null cases or exceptions (`orElseThrow()`)

---

### d) REST Controller

```java
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService service) {
        this.userService = service;
    }

    @GetMapping
    public List<User> getAll() { return userService.getAllUsers(); }

    @GetMapping("/{id}")
    public User getOne(@PathVariable Long id) { return userService.getUser(id); }

    @PostMapping
    public User create(@RequestBody User user) { return userService.createUser(user); }

    @PutMapping("/{id}")
    public User update(@PathVariable Long id, @RequestBody User user) { return userService.updateUser(id, user); }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { userService.deleteUser(id); }
}
```

**Keywords and explanations:**

* `@RestController`: combines `@Controller` + `@ResponseBody` → returns JSON
* `@RequestMapping("/api/users")`: base API path
* `@GetMapping`, `@PostMapping`, `@PutMapping`, `@DeleteMapping`: HTTP methods
* `@PathVariable`: retrieves a variable from the URL
* `@RequestBody`: retrieves JSON body from the client

**Best practices:**

* Clearly separate layers **Controller → Service → Repository**
* Use `ResponseEntity` if you need custom HTTP status codes

---

### e) `application.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/demo
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

**Explanations:**

* `ddl-auto=update` → Hibernate automatically updates the table structure
* `PostgreSQLDialect` → Hibernate generates PostgreSQL-compatible SQL

**Best practices:**

* For production, avoid `update`; use `validate` or migrations

---