package com.nadyagrishina.reflect_diary.service;

import com.nadyagrishina.reflect_diary.DTO.GoalDTO;

import java.util.List;

public interface GoalService {
    List<GoalDTO> findAllGoals();
    GoalDTO findGoalById(Long id);
    GoalDTO save(GoalDTO dto);
    GoalDTO updateGoal(Long id, GoalDTO dto);
    void deleteGoal(Long id);
    List<GoalDTO> findAllGoalsByUserId(Long userId);
}
