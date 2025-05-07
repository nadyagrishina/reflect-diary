package com.nadyagrishina.pro2project.service;

import com.nadyagrishina.pro2project.DTO.GoalDTO;

import java.util.List;

public interface GoalService {
    List<GoalDTO> findAllGoals();
    GoalDTO findGoalById(Long id);
    GoalDTO save(GoalDTO dto);
    GoalDTO updateGoal(Long id, GoalDTO dto);
    void deleteGoal(Long id);
    List<GoalDTO> findAllGoalsByUserId(Long userId);
}
