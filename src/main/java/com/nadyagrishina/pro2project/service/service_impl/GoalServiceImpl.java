package com.nadyagrishina.pro2project.service.service_impl;

import com.nadyagrishina.pro2project.DTO.GoalDTO;
import com.nadyagrishina.pro2project.mapper.GoalMapper;
import com.nadyagrishina.pro2project.model.Goal;
import com.nadyagrishina.pro2project.repository.GoalRepository;
import com.nadyagrishina.pro2project.service.GoalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoalServiceImpl implements GoalService {

    private final GoalRepository goalRepository;
    private final GoalMapper goalMapper;

    public GoalServiceImpl(GoalRepository goalRepository, GoalMapper goalMapper) {
        this.goalRepository = goalRepository;
        this.goalMapper = goalMapper;
    }

    @Override
    public List<GoalDTO> findAllGoals() {
        List<Goal> goals = goalRepository.findAll();
        return goals.stream().map(goalMapper::toDTO).collect(Collectors.toList());
    }

    @Override
    public GoalDTO findGoalById(Long id) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + id));
        return goalMapper.toDTO(goal);
    }

    @Override
    @Transactional
    public GoalDTO save(GoalDTO dto) {
        Goal goal = goalMapper.toEntity(dto);
        goalRepository.save(goal);
        return goalMapper.toDTO(goal);
    }

    @Override
    @Transactional
    public GoalDTO updateGoal(Long id, GoalDTO dto) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + id));
        goalMapper.updateGoalFromDTO(goal, dto);
        return goalMapper.toDTO(goal);
    }

    @Override
    @Transactional
    public void deleteGoal(Long id) {
        Goal goal = goalRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Goal not found with id: " + id));
        goalRepository.delete(goal);
    }

    @Override
    public List<GoalDTO> findAllGoalsByUserId(Long userId) {
        List<Goal> goals = goalRepository.findByUserId(userId);
        return goals.stream().map(goalMapper::toDTO).collect(Collectors.toList());
    }
}
