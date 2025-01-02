package com.ieltsdemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class TextUpdateDTO {
    private String id;
    private String title;
    private String content;
    private List<ImageDTO> images;
}
