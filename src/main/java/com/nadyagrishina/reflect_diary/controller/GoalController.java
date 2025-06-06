package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.model.Goal;
import com.nadyagrishina.reflect_diary.model.User;
import com.nadyagrishina.reflect_diary.service.GoalService;
import com.nadyagrishina.reflect_diary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/goals")
public class GoalController {

    private final GoalService goalService;
    private final UserService userService;

    public GoalController(GoalService goalService, UserService userService) {
        this.goalService = goalService;
        this.userService = userService;
    }

    @GetMapping
    public String getAllGoals(Model model, Principal principal) {
        Long id = userService.findByUsername(principal.getName()).getId();
        List<Goal> goals = goalService.findAllGoalsByUserId(id);
        model.addAttribute("goals", goals);
        return "goals";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("goal", new Goal());
        return "create-goal";
    }

    @PostMapping("/create")
    public String createGoal(@ModelAttribute Goal goal, Principal principal) {
        User user = userService.findByUsername(principal.getName());
        goal.setUser(user);
        goalService.save(goal);
        return "redirect:/goals";
    }

    @PostMapping("/{id}/toggle")
    public String toggleGoalStatus(@PathVariable Long id,
                                   Principal principal,
                                   @RequestHeader(value = "Referer", required = false) String referer) {
        Goal goal = goalService.findGoalById(id);
        if (!goal.getUser().getUsername().equals(principal.getName())) {
            return "redirect:/access-denied";
        }
        goal.setCompleted(!goal.isCompleted());
        goalService.save(goal);
        return "redirect:" + (referer != null ? referer : "/goals");
    }



    @GetMapping("/{id}/edit")
    public String editGoal(@PathVariable Long id, Model model) {
        Goal goal = goalService.findGoalById(id);



        model.addAttribute("goal", goal);
        model.addAttribute("formAction", "/goals/update");
        return "create-goal";
    }

    @PostMapping("/update")
    public String updateGoal(@ModelAttribute Goal goal, Principal principal) {
        Goal original = goalService.findGoalById(goal.getId());
        if (!original.getUser().getUsername().equals(principal.getName())) {
            return "redirect:/goals";
        }

        original.setDescription(goal.getDescription());
        original.setDeadline(goal.getDeadline());
        original.setCompleted(goal.isCompleted());

        goalService.save(original);
        return "redirect:/goals";
    }

    @PostMapping("/{id}/delete")
    public String deleteGoal(@PathVariable Long id) {
        goalService.deleteGoal(id);
        return "redirect:/goals";
    }
}
