package com.nadyagrishina.reflect_diary.service.service_impl;

import com.nadyagrishina.reflect_diary.DTO.ReflectionQuestionDTO;
import com.nadyagrishina.reflect_diary.mapper.ReflectionQuestionMapper;
import com.nadyagrishina.reflect_diary.model.ReflectionQuestion;
import com.nadyagrishina.reflect_diary.repository.ReflectionQuestionRepository;
import com.nadyagrishina.reflect_diary.service.ReflectionQuestionService;
import org.springframework.stereotype.Service;

@Service
public class ReflectionQuestionServiceImpl implements ReflectionQuestionService {

    private final ReflectionQuestionRepository reflectionQuestionRepository;
    private final ReflectionQuestionMapper reflectionQuestionMapper;

    public ReflectionQuestionServiceImpl(ReflectionQuestionRepository reflectionQuestionRepository, ReflectionQuestionMapper reflectionQuestionMapper) {
        this.reflectionQuestionRepository = reflectionQuestionRepository;
        this.reflectionQuestionMapper = reflectionQuestionMapper;
    }

    @Override
    public ReflectionQuestionDTO getRandomQuestion() {
        ReflectionQuestion question = reflectionQuestionRepository.findRandom();
        return reflectionQuestionMapper.toDTO(question);
    }
}
