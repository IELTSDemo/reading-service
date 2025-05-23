package com.ieltsdemo.service.impl;

import com.ieltsdemo.dto.client.DeleteAllResultDTO;
import com.ieltsdemo.dto.client.DeleteResultDTO;
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
        String transientUser = result.getEmail();
        if (transientUser != null) {
            // Ищем пользователя по email в базе данных
            User existingUser = userRepository.findFirstByEmail(transientUser).orElse(null);
            if (existingUser != null) {
                // Используем существующего пользователя
                result.getEmail();
            } else {
                User user = new User();
                user.setEmail(transientUser);
                // Если пользователь не найден, сохраняем нового
                User newUser = userRepository.save(user);
                result.setEmail(newUser.getEmail());
            }
        }

        // Теперь связанный User персистентен, сохраняем Result
        return resultRepository.save(result);
    }

    @Override
    public void deleteResult(DeleteResultDTO deleteResultDTO) {
        String textId = deleteResultDTO.getTextId();
        String email = deleteResultDTO.getEmail();
        resultRepository.deleteByTextIdAndEmail(textId, email, false);
    }

    @Override
    public void deleteByTestIdAndEmail(DeleteAllResultDTO deleteAllResultDTO) {
        String testId = deleteAllResultDTO.getTestId();
        String email = deleteAllResultDTO.getEmail();
        resultRepository.deleteByTestIdAndEmail(testId, email,false);
    }

}
