package com.nadyagrishina.pro2project.service.service_impl;

import com.nadyagrishina.pro2project.DTO.ReflectionQuestionDTO;
import com.nadyagrishina.pro2project.mapper.ReflectionQuestionMapper;
import com.nadyagrishina.pro2project.model.ReflectionQuestion;
import com.nadyagrishina.pro2project.repository.ReflectionQuestionRepository;
import com.nadyagrishina.pro2project.service.ReflectionQuestionService;
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
