package com.nadyagrishina.reflect_diary.service.service_impl;

import com.nadyagrishina.reflect_diary.model.Goal;
import com.nadyagrishina.reflect_diary.repository.GoalRepository;
import com.nadyagrishina.reflect_diary.service.GoalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;

    public GoalServiceImpl(GoalRepository goalRepository) {
        this.goalRepository = goalRepository;
    }

    @Override
    public Goal findGoalById(Long id) {
        return goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + id));
    }

    @Override
    @Transactional
    public Goal save(Goal goal) {
        return goalRepository.save(goal);
    }

    @Override
    @Transactional
    public Goal updateGoal(Long id, Goal goal) {
        Goal excistingGoal = goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + id));
        excistingGoal.setCompleted(goal.isCompleted());
        excistingGoal.setDescription(goal.getDescription());
        excistingGoal.setDeadline(goal.getDeadline());
        return goalRepository.save(excistingGoal);
    }

    @Override
    @Transactional
    public void deleteGoal(Long id) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + id));
        goalRepository.delete(goal);
    }

    @Override
    public List<Goal> findAllGoalsByUserId(Long userId) {
        return goalRepository.findByUserId(userId);
    }
}
