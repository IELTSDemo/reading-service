package com.ieltsdemo.service;

import com.ieltsdemo.model.Text;
import com.ieltsdemo.repository.TextRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TextService {
    private final TextRepository textRepository;

    public TextService(TextRepository textRepository) {
        this.textRepository = textRepository;
    }

    public List<Text> findAll() {
        return textRepository.findAll();
    }

    public Text saveText(Text text) {
        return textRepository.save(text);
    }
}

