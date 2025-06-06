package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.model.User;
import com.nadyagrishina.reflect_diary.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private User user;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");
        user.setPassword("password");
    }

    @Test
    public void testCreateUser() {
        userController.createUser(user);
        verify(userService, times(1)).save(user);
    }

    @Test
    public void testDeleteUser() {
        userController.deleteUser(1L);
        verify(userService, times(1)).delete(1L);
    }
}
