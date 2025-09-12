package com.example.demo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class HelloController {

    /**
     * GET /api/hello
     * Basic GET endpoint that returns a simple greeting
     *
     * Example: curl http://localhost:8080/api/hello
     */
    @GetMapping("/hello")
    public String sayHello() {
        return "Hello, World! Welcome to REST APIs!";
    }

    /**
     * GET /api/hello/{name}
     * Path variable example - personalized greeting
     *
     * Example: curl http://localhost:8080/api/hello/Alice
     */
    @GetMapping("/hello/{name}")
    public String sayHelloTo(@PathVariable String name) {
        return "Hello, " + name + "! Nice to meet you!";
    }

    /**
     * GET /api/greet
     * Request parameter example - greeting with query parameter
     *
     * Example: curl http://localhost:8080/api/greet?name=Bob
     */
    @GetMapping("/greet")
    public String greetWithParam(@RequestParam String name) {
        return "Greetings, " + name + "! How are you today?";
    }

    /**
     * POST /api/hello
     * Request body example - receiving JSON data
     *
     * Example:
     * curl -X POST http://localhost:8080/api/hello \
     *   -H "Content-Type: application/json" \
     *   -d '{"name": "Charlie", "age": 30}'
     */
    @PostMapping("/hello")
    public String createGreeting(@RequestBody User user) {
        return "Created greeting for: " + user.getName() + " (Age: " + user.getAge() + ")";
    }

    /**
     * GET /api/status
     * Demonstrating HTTP status codes and headers
     */
    @GetMapping("/status")
    public ResponseEntity<String> checkStatus() {
        return ResponseEntity.ok()
                .header("Custom-Header", "my-value")
                .body("Service is running smoothly!");
    }

    // Simple inner class for request body example
    public static class User {
        private String name;
        private int age;

        // Getters and setters required for JSON binding
        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
    }
}