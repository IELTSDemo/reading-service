package com.ieltsdemo.dto;

import com.ieltsdemo.util.SectionType;


public class SectionDTO {
    private String id;
    private SectionType name; // Название стейджа, например, "Section 1"

    public SectionDTO(String id, SectionType name) {
        this.id = id;
        this.name = name;
    }
}
