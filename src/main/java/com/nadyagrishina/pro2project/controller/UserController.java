package com.nadyagrishina.pro2project.controller;

import com.nadyagrishina.pro2project.DTO.UserRequestDTO;
import com.nadyagrishina.pro2project.DTO.UserResponseDTO;
import com.nadyagrishina.pro2project.service.UserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<UserResponseDTO> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{userId}")
    public UserResponseDTO getUserById(@PathVariable Long userId) {
        return userService.findById(userId);
    }

    @PostMapping
    public UserResponseDTO createUser(@RequestBody @Valid UserRequestDTO userRequestDTO) {
        return userService.save(userRequestDTO);
    }

    @PutMapping("/{userId}")
    public UserResponseDTO updateUser(@PathVariable Long userId, @RequestBody @Valid UserRequestDTO userRequestDTO) {
        return userService.updateUser(userId, userRequestDTO);
    }

    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        userService.delete(userId);
    }

    @GetMapping("/by-username")
    public UserResponseDTO getUserByUsername(@RequestParam String username) {
        return userService.findByUsername(username);
    }
}
