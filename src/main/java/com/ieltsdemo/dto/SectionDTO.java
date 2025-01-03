package com.ieltsdemo.dto;

import com.ieltsdemo.util.SectionType;


public class SectionDTO {
    private String id;
    private SectionType name; // Название стейджа, например, "Section 1"

    public SectionDTO(String id, SectionType name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SectionType getName() {
        return name;
    }

    public void setName(SectionType name) {
        this.name = name;
    }
}
