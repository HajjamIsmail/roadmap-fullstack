package com.example.demo.controller;

import com.example.demo.dto.GreetingResponse;
import com.example.demo.dto.UserRequest;
import com.example.demo.service.HelloService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PostMapping;

@RestController
@RequestMapping("/api")
public class HelloController {

    private final HelloService helloService;

    public HelloController(HelloService helloService) {
        this.helloService = helloService;
    }

    @GetMapping("/hello")
    public GreetingResponse sayHello() {
        return helloService.sayHello();
    }

    @GetMapping("/hello/{name}")
    public GreetingResponse sayHelloTo(@PathVariable String name) {
        return helloService.sayHelloTo(name);
    }

    @GetMapping("/greet")
    public GreetingResponse greetWithParam(@RequestParam String name) {
        return helloService.greetWithParam(name);
    }

    @PostMapping("/hello")
    public GreetingResponse createGreeting(@Valid @RequestBody UserRequest user) {
        return helloService.createGreeting(user);
    }

    @GetMapping("/status")
    public ResponseEntity<GreetingResponse> checkStatus() {
        return ResponseEntity.ok()
                .header("Custom-Header", "my-value")
                .body(helloService.checkStatus());
    }
}
