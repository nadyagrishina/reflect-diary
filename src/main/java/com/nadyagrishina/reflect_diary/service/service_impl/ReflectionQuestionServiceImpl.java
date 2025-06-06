package com.nadyagrishina.reflect_diary.service.service_impl;

import com.nadyagrishina.reflect_diary.model.ReflectionQuestion;
import com.nadyagrishina.reflect_diary.repository.ReflectionQuestionRepository;
import com.nadyagrishina.reflect_diary.service.ReflectionQuestionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ReflectionQuestionServiceImpl implements ReflectionQuestionService {

    private final ReflectionQuestionRepository reflectionQuestionRepository;

    public ReflectionQuestionServiceImpl(ReflectionQuestionRepository reflectionQuestionRepository) {
        this.reflectionQuestionRepository = reflectionQuestionRepository;
    }

    @Override
    public ReflectionQuestion getRandomQuestion() {
        return reflectionQuestionRepository.findRandom();
    }

    @Override
    public ReflectionQuestion findById(Long id) {
        return reflectionQuestionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(ReflectionQuestion.class.getSimpleName() + " with id: " + id));
    }
}
