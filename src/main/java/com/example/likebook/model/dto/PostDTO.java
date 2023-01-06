package com.example.likebook.model.dto;

import com.example.likebook.model.enums.MoodEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class PostDTO {

    @NotBlank(message = "You must add content")
    @Size(min = 2, max = 100, message = "Content length must be between 2 and 100 characters!")
    private String content;

    @NotNull(message = "Please select mood")
    private String mood;

    public PostDTO() {
    }

    public String getContent() {
        return content;
    }

    public PostDTO setContent(String content) {
        this.content = content;
        return this;
    }

    public String getMood() {
        return mood;
    }

    public PostDTO setMood(String mood) {
        this.mood = mood;
        return this;
    }
}
