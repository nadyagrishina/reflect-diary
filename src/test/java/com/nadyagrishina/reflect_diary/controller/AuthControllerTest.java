package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.model.Role;
import com.nadyagrishina.reflect_diary.model.User;
import com.nadyagrishina.reflect_diary.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class AuthControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private AuthController authController;

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
    public void testCreateEntry_NewUser() {
        when(userService.existsByUsername("testuser")).thenReturn(false);

        authController.createEntry(user);

        verify(userService, times(1)).save(user);
        assert user.getRole() == Role.USER;
        assert user.getRegistrationDate() != null;
    }

    @Test
    public void testCreateEntry_ExistingUser() {
        when(userService.existsByUsername("testuser")).thenReturn(true);

        authController.createEntry(user);

        verify(userService, never()).save(user);
    }
}
