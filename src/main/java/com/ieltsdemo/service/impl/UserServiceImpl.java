package com.ieltsdemo.service.impl;

import com.ieltsdemo.model.User;
import com.ieltsdemo.repository.UserRepository;
import com.ieltsdemo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User findOrCreateUser(String email) {
        // Проверяем, есть ли пользователь с таким email
        return userRepository.findUserByEmail(email).orElseGet(() -> {
            // Если пользователя нет, создаём нового
            User newUser = new User();
            newUser.setEmail(email);
            return userRepository.save(newUser);
        });
    }
}
