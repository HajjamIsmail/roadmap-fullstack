package com.example.demo.dao;

import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserDao {
    List<User> findAll();
    Optional<User> findById(Long id);
    User save(User user);
    void deleteById(Long id);
    List<User> findByNameContains(String name);
}
