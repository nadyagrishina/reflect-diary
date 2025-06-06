package com.nadyagrishina.reflect_diary.service;

import com.nadyagrishina.reflect_diary.model.Goal;

import java.util.List;

public interface GoalService {
    Goal findGoalById(Long id);
    Goal save(Goal dto);
    Goal updateGoal(Long id, Goal dto);
    void deleteGoal(Long id);
    List<Goal> findAllGoalsByUserId(Long userId);
}
