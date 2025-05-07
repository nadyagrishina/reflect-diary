package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.DTO.GoalDTO;
import com.nadyagrishina.reflect_diary.service.GoalService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/goals")
public class GoalController {

    private final GoalService goalService;

    public GoalController(GoalService goalService) {
        this.goalService = goalService;
    }

    @GetMapping
    public List<GoalDTO> getAllGoals() {
        return goalService.findAllGoals();
    }

    @GetMapping("{goalId}")
    public GoalDTO getGoalById(@PathVariable Long goalId) {
        return goalService.findGoalById(goalId);
    }

    @PostMapping
    public GoalDTO createGoal(@RequestBody @Valid GoalDTO goalDTO) {
        return goalService.save(goalDTO);
    }

    @PutMapping("{goalId}")
    public GoalDTO updateGoal(@PathVariable Long goalId, @RequestBody @Valid GoalDTO goalDTO) {
        return goalService.updateGoal(goalId, goalDTO);
    }

    @DeleteMapping("{goalId}")
    public void deleteGoal(@PathVariable Long goalId) {
        goalService.deleteGoal(goalId);
    }

    @GetMapping("/by-user/{userId}")
    public List<GoalDTO> getGoalsByUserId(@PathVariable Long userId) {
        return goalService.findAllGoalsByUserId(userId);
    }
}
