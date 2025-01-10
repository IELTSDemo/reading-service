package com.ieltsdemo.service.impl;

import com.ieltsdemo.model.Result;
import com.ieltsdemo.model.User;
import com.ieltsdemo.repository.ResultRepository;
import com.ieltsdemo.repository.UserRepository;
import com.ieltsdemo.service.ResultService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResultServiceImpl implements ResultService {
    private final ResultRepository resultRepository;
    private final UserRepository userRepository;

    public ResultServiceImpl(ResultRepository resultRepository, UserRepository userRepository) {
        this.resultRepository = resultRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Result> getAllResults() {
        return resultRepository.findAll();
    }

    @Override
    public Result createResult(Result result) {
        // Предполагается, что в объекте result.user уже установлен с email
        User transientUser = result.getUser();
        if (transientUser != null) {
            // Ищем пользователя по email в базе данных
            User existingUser = userRepository.findUserByEmail(transientUser.getEmail()).orElse(null);
            if (existingUser != null) {
                // Используем существующего пользователя
                result.setUser(existingUser);
            } else {
                // Если пользователь не найден, сохраняем нового
                User newUser = userRepository.save(transientUser);
                result.setUser(newUser);
            }
        }

        // Теперь связанный User персистентен, сохраняем Result
        return resultRepository.save(result);
    }
}
