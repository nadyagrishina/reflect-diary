package com.nadyagrishina.reflect_diary.DTO;

import com.nadyagrishina.reflect_diary.model.Mood;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.List;

public class EntryRequestDTO {
    @NotNull
    @Size(min = 1, max = 100)
    private LocalDate date;
    @NotBlank
    @Size(min = 1, max = 10000)
    private String mainText;
    @Size(min = 1, max = 1000)
    private String highlights;
    @Size(min = 1, max = 1000)
    private String difficulties;
    @Size(min = 1, max = 1000)
    private String tommorowPlan;
    @Min(1)
    @Max(5)
    private int score;
    private Mood mood;
    private List<Long> tagIds;

    public EntryRequestDTO() {
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getMainText() {
        return mainText;
    }

    public void setMainText(String mainText) {
        this.mainText = mainText;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getDifficulties() {
        return difficulties;
    }

    public void setDifficulties(String difficulties) {
        this.difficulties = difficulties;
    }

    public String getTommorowPlan() {
        return tommorowPlan;
    }

    public void setTommorowPlan(String tommorowPlan) {
        this.tommorowPlan = tommorowPlan;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Mood getMood() {
        return mood;
    }

    public void setMood(Mood mood) {
        this.mood = mood;
    }

    public List<Long> getTagIds() {
        return tagIds;
    }

    public void setTagIds(List<Long> tagIds) {
        this.tagIds = tagIds;
    }
}
