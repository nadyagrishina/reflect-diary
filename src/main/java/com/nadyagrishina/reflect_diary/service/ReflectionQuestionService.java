package com.nadyagrishina.reflect_diary.service;

import com.nadyagrishina.reflect_diary.model.ReflectionQuestion;

public interface ReflectionQuestionService {
    ReflectionQuestion getRandomQuestion();
    ReflectionQuestion findById(Long id);
}
