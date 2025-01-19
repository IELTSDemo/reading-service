package com.ieltsdemo.service.impl;

import com.ieltsdemo.model.User;
import com.ieltsdemo.repository.UserRepository;
import com.ieltsdemo.service.UserService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
        return userRepository.findFirstByEmail(email).map(user -> {
            // Инкрементируем счетчик входов и обновляем дату последнего входа
            user.incrementLoginCounter();
            user.setLastLoginDate(LocalDateTime.now());
            return userRepository.save(user);
        }).orElseGet(() -> {
            // Создаём нового пользователя
            User newUser = new User();
            newUser.setEmail(email);
            newUser.setLoginCounter(1); // Первый вход
            newUser.setLastLoginDate(LocalDateTime.now()); // Устанавливаем текущую дату
            return userRepository.save(newUser);
        });
    }


}
