package com.example.demo.service;

import com.example.demo.dao.UserDao;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.model.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userDao.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + id));
    }

    @Override
    public User createUser(User user) {
        // logique métier possible (ex : validation supplémentaire)
        return userDao.save(user);
    }

    @Override
    public User updateUser(Long id, User user) {
        User existing = getUserById(id);
        existing.setName(user.getName());
        existing.setEmail(user.getEmail());
        return userDao.save(existing);
    }

    @Override
    public void deleteUser(Long id) {
        // on peut vérifier existence avant suppression
        getUserById(id);
        userDao.deleteById(id);
    }

    @Override
    public List<User> searchByName(String name) {
        return userDao.findByNameContains(name);
    }
}
