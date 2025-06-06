package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.model.Goal;
import com.nadyagrishina.reflect_diary.model.User;
import com.nadyagrishina.reflect_diary.service.GoalService;
import com.nadyagrishina.reflect_diary.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GoalControllerTest {

    @Mock
    private GoalService goalService;

    @Mock
    private UserService userService;

    @Mock
    private Principal principal;

    @Mock
    private String referer;

    @Mock
    private Model model;

    @InjectMocks
    private GoalController goalController;

    private User user;
    private Goal goal;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        goal = new Goal();
        goal.setId(1L);
        goal.setUser(user);
        goal.setDeadline(LocalDate.now().plusDays(5));
    }

    @Test
    void getAllGoals_shouldReturnGoalsView() {
        when(principal.getName()).thenReturn("testuser");
        when(userService.findByUsername("testuser")).thenReturn(user);
        when(goalService.findAllGoalsByUserId(1L)).thenReturn(List.of(goal));

        String view = goalController.getAllGoals(model, principal);

        assertEquals("goals", view);
        verify(model).addAttribute(eq("goals"), any());
    }

    @Test
    void showCreateForm_shouldReturnForm() {
        String view = goalController.showCreateForm(model);

        assertEquals("create-goal", view);
        verify(model).addAttribute(eq("goal"), any(Goal.class));
    }

    @Test
    void createGoal_shouldSaveGoalAndRedirect() {
        when(principal.getName()).thenReturn("testuser");
        when(userService.findByUsername("testuser")).thenReturn(user);

        String result = goalController.createGoal(goal, principal);

        assertEquals("redirect:/goals", result);
        assertEquals(user, goal.getUser());
        verify(goalService).save(goal);
    }

    @Test
    void deleteGoal_shouldDeleteAndRedirect() {
        String result = goalController.deleteGoal(1L);

        assertEquals("redirect:/goals", result);
        verify(goalService).deleteGoal(1L);
    }

    @Test
    void editGoal_shouldReturnFormWhenAccessGranted() {
        when(goalService.findGoalById(1L)).thenReturn(goal);
        when(principal.getName()).thenReturn("testuser");

        String view = goalController.editGoal(1L, model);

        assertEquals("create-goal", view);
        verify(model).addAttribute("goal", goal);
        verify(model).addAttribute("formAction", "/goals/update");
    }

    @Test
    void editGoal_shouldRedirectWhenAccessDenied() {
        User otherUser = new User();
        otherUser.setUsername("hacker");
        goal.setUser(otherUser);

        when(goalService.findGoalById(1L)).thenReturn(goal);
        when(principal.getName()).thenReturn("testuser");

        String view = goalController.editGoal(1L, model);
        assertEquals("redirect:/goals", view);
    }

    @Test
    void updateGoal_shouldUpdateAndRedirect() {
        when(goalService.findGoalById(1L)).thenReturn(goal);
        when(principal.getName()).thenReturn("testuser");

        Goal updated = new Goal();
        updated.setId(1L);
        updated.setDescription("New Description");
        updated.setDeadline(LocalDate.now().plusDays(7));
        updated.setCompleted(true);

        String result = goalController.updateGoal(updated, principal);

        assertEquals("redirect:/goals", result);
        assertEquals("New Description", goal.getDescription());
        assertTrue(goal.isCompleted());
        verify(goalService).save(goal);
    }

    @Test
    void toggleGoalStatus_shouldToggleAndRedirect_whenAccessGranted() {
        goal.setCompleted(false);

        when(goalService.findGoalById(1L)).thenReturn(goal);
        when(principal.getName()).thenReturn("testuser");

        String result = goalController.toggleGoalStatus(1L, principal, referer);

        assertEquals("redirect:/goals", result);
        assertTrue(goal.isCompleted());
        verify(goalService).save(goal);
    }

    @Test
    void toggleGoalStatus_shouldRedirect_whenAccessDenied() {
        User otherUser = new User();
        otherUser.setUsername("hacker");
        goal.setUser(otherUser);

        when(goalService.findGoalById(1L)).thenReturn(goal);
        when(principal.getName()).thenReturn("testuser");

        String result = goalController.toggleGoalStatus(1L, principal, referer);

        assertEquals("redirect:/access-denied", result);
    }
}
