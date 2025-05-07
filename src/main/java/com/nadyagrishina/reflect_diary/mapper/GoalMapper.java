package com.nadyagrishina.reflect_diary.mapper;

import com.nadyagrishina.reflect_diary.DTO.GoalDTO;
import com.nadyagrishina.reflect_diary.model.Goal;
import org.springframework.stereotype.Component;

@Component
public class GoalMapper {
    public Goal toEntity(GoalDTO dto) {
        Goal goal = new Goal();
        goal.setDescription(dto.getDescription());
        goal.setCompleted(dto.isCompleted());
        goal.setDeadline(dto.getDeadline());
        return goal;
    }

    public GoalDTO toDTO(Goal goal) {
        GoalDTO dto = new GoalDTO();
        dto.setId(goal.getId());
        dto.setDescription(goal.getDescription());
        dto.setCompleted(goal.isCompleted());
        dto.setDeadline(goal.getDeadline());
        return dto;
    }

    public void updateGoalFromDTO(Goal goal, GoalDTO dto) {
        goal.setDescription(dto.getDescription());
        goal.setCompleted(dto.isCompleted());
        goal.setDeadline(dto.getDeadline());
    }
}
