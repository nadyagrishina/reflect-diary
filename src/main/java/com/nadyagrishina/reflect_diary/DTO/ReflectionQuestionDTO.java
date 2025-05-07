package com.nadyagrishina.reflect_diary.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReflectionQuestionDTO {

    private Long id;

    @NotBlank
    @Size(min = 1, max = 1000)
    private String text;

    public ReflectionQuestionDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
