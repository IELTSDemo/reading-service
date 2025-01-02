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

    public Text findById(String id) {
        return textRepository.findById(id).orElse(null);
    }

    public void deleteById(String id) {
        textRepository.deleteById(id);
    }
}

