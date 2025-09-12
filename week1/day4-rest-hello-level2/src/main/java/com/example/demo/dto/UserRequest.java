package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Min;

public record UserRequest(@NotBlank String name, @Min(0) int age) { }
