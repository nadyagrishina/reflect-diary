package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.model.Entry;
import com.nadyagrishina.reflect_diary.model.Goal;
import com.nadyagrishina.reflect_diary.model.User;
import com.nadyagrishina.reflect_diary.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.Comparator;
import java.util.List;

@Controller
public class MainController {

    UserService userService;

    public MainController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String index(Model model, Principal principal) {
        String username = principal.getName();
        User user = userService.findByUsername(username);

        List<Goal> goals = user.getGoals();
        List<Entry> entries = user.getEntries();

        List<Goal> goalsPreview = goals.stream()
                .sorted(Comparator.comparing(Goal::getDeadline))
                .limit(3)
                .toList();

        List<Entry> entriesPreview = entries.stream()
                .sorted(Comparator.comparing(Entry::getDate).reversed())
                .limit(3)
                .toList();

        model.addAttribute("user", user);
        model.addAttribute("goals", goalsPreview);
        model.addAttribute("entries", entriesPreview);
        return "index";
    }

}
