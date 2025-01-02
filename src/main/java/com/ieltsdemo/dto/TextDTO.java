package com.ieltsdemo.dto;

import lombok.Data;

import java.util.List;

@Data
public class TextDTO {
    private String id;
    private String title;
    private String content;
    private List<ImageDTO> images;
}
