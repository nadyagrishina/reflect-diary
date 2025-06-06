package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.model.Role;
import com.nadyagrishina.reflect_diary.model.User;
import com.nadyagrishina.reflect_diary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String createEntry(@ModelAttribute @Valid User user) {
        if (userService.existsByUsername(user.getUsername())) {
            return "redirect:/auth/register?error=true";
        }
        user.setRole(Role.USER);
        user.setRegistrationDate(LocalDateTime.now());
        userService.save(user);
        return "redirect:/auth/login";
    }
}
