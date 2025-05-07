package com.nadyagrishina.reflect_diary.DTO;

import com.nadyagrishina.reflect_diary.model.Mood;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class EntryResponseDTO {
    private Long id;
    private LocalDate date;
    private String mainText;
    private String highlights;
    private String difficulties;
    private String tomorrowPlan;
    private int score;
    private Mood mood;
    private String reflectionQuestion;
    private LocalDateTime createdAt;
    private List<String> tags;

    public EntryResponseDTO() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTomorrowPlan() {
        return tomorrowPlan;
    }

    public void setTomorrowPlan(String tomorrowPlan) {
        this.tomorrowPlan = tomorrowPlan;
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

    public String getReflectionQuestion() {
        return reflectionQuestion;
    }

    public void setReflectionQuestion(String reflectionQuestion) {
        this.reflectionQuestion = reflectionQuestion;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }
}
