package com.ieltsdemo.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.awt.*;
import java.util.List;

@Data
@Document(collection = "texts")
public class Text {
    @Id
    private String id;
    private String title;
    private String content;
    private List<Image> images;
}

