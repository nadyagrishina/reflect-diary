package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.model.User;
import com.nadyagrishina.reflect_diary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/create")
    public String createUser(@ModelAttribute @Valid User user) {
        userService.save(user);
        return "redirect:/users";
    }

    @PostMapping("/{userId}/delete")
    public String deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
        return "redirect:/";
    }
}
