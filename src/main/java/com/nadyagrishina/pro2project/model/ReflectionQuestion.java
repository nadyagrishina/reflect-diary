package com.nadyagrishina.pro2project.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "reflection_questions")
public class ReflectionQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @OneToMany(mappedBy = "reflectionQuestion")
    private List<Entry> entries;

    public ReflectionQuestion() {
    }

    public ReflectionQuestion(String text, List<Entry> entries) {
        this.text = text;
        this.entries = entries;
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

    public List<Entry> getEntries() {
        return entries;
    }

    public void setEntries(List<Entry> entries) {
        this.entries = entries;
    }
}
