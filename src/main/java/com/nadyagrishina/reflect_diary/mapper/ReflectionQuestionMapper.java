package com.nadyagrishina.reflect_diary.mapper;

import com.nadyagrishina.reflect_diary.DTO.ReflectionQuestionDTO;
import com.nadyagrishina.reflect_diary.model.ReflectionQuestion;
import org.springframework.stereotype.Component;

@Component
public class ReflectionQuestionMapper {
    public ReflectionQuestion toEntity(ReflectionQuestionDTO dto) {
        ReflectionQuestion reflectionQuestion = new ReflectionQuestion();
        reflectionQuestion.setText(dto.getText());
        return  reflectionQuestion;
    }

    public ReflectionQuestionDTO toDTO(ReflectionQuestion entity) {
        ReflectionQuestionDTO reflectionQuestionDTO = new ReflectionQuestionDTO();
        reflectionQuestionDTO.setId(entity.getId());
        reflectionQuestionDTO.setText(entity.getText());
        return reflectionQuestionDTO;
    }
}
