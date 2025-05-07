package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.DTO.ReflectionQuestionDTO;
import com.nadyagrishina.reflect_diary.service.ReflectionQuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/reflect-questions")
public class ReflectionQuestionController {

    private final ReflectionQuestionService reflectionQuestionService;

    public ReflectionQuestionController(ReflectionQuestionService reflectionQuestionService) {
        this.reflectionQuestionService = reflectionQuestionService;
    }

    @GetMapping("/random")
    public ReflectionQuestionDTO getRandomQuestion() {
        return reflectionQuestionService.getRandomQuestion();
    }
}
