package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.model.User;
import com.nadyagrishina.reflect_diary.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

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

    @PostMapping("/toggle-darkmode")
    @ResponseBody
    public ResponseEntity<?> toggleDarkMode(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User not logged in");
        }

        User user = userService.findByUsername(principal.getName());
        if (user == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

        boolean currentSetting = user.isDarkModeEnabled();
        user.setDarkModeEnabled(!currentSetting);
        userService.updateUserPreferences(user);

        return ResponseEntity.ok("Dark mode toggled");
    }

}
