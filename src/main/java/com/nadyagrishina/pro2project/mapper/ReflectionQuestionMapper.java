package com.nadyagrishina.pro2project.mapper;

import com.nadyagrishina.pro2project.DTO.ReflectionQuestionDTO;
import com.nadyagrishina.pro2project.model.ReflectionQuestion;
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
