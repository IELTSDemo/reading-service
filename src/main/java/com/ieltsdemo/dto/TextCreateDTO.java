package com.ieltsdemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class TextCreateDTO {
    private String title;
    private String content;
    private List<ImageDTO> images;
}
