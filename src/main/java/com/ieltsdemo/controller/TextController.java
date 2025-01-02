package com.ieltsdemo.controller;

import com.ieltsdemo.model.Text;
import com.ieltsdemo.service.TextService;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/texts")
public class TextController {
    private final TextService textService;

    public TextController(TextService textService) {
        this.textService = textService;
    }

    @GetMapping
    public List<Text> getAllTexts() {
        return textService.findAll();
    }

    @PostMapping
    public Text createText(@RequestBody Text text) {
        return textService.saveText(text);
    }

    @GetMapping("/{id}")
    public Text getTextById(@PathVariable String id) {
        return textService.findById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteText(@PathVariable String id) {
        textService.deleteById(id);
    }
}

