package com.example.demo.service;

import com.example.demo.dto.GreetingResponse;
import com.example.demo.dto.UserRequest;
import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public GreetingResponse sayHello() {
        return new GreetingResponse("Hello, World! Welcome to REST APIs!");
    }

    public GreetingResponse sayHelloTo(String name) {
        return new GreetingResponse("Hello, " + name + "! Nice to meet you!");
    }

    public GreetingResponse greetWithParam(String name) {
        return new GreetingResponse("Greetings, " + name + "! How are you today?");
    }

    public GreetingResponse createGreeting(UserRequest user) {
        return new GreetingResponse("Created greeting for: " + user.name() + " (Age: " + user.age() + ")");
    }

    public GreetingResponse checkStatus() {
        return new GreetingResponse("Service is running smoothly!");
    }
}
