package com.nadyagrishina.reflect_diary.service;

import com.nadyagrishina.reflect_diary.model.Goal;
import com.nadyagrishina.reflect_diary.repository.GoalRepository;
import com.nadyagrishina.reflect_diary.service.service_impl.GoalServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class GoalServiceImplTest {

    @Mock
    private GoalRepository goalRepository;

    @InjectMocks
    private GoalServiceImpl goalService;

    private Goal goal;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        goal = new Goal();
        goal.setId(1L);
        goal.setDescription("Test goal");
        goal.setDeadline(LocalDate.now());
        goal.setCompleted(false);
    }

    @Test
    public void testFindGoalById() {
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        Goal found = goalService.findGoalById(1L);
        assertEquals("Test goal", found.getDescription());
    }

    @Test
    public void testFindGoalByIdNotFound() {
        when(goalRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> goalService.findGoalById(1L));
    }

    @Test
    public void testSave() {
        when(goalRepository.save(goal)).thenReturn(goal);
        Goal saved = goalService.save(goal);
        assertEquals(goal, saved);
    }

    @Test
    public void testUpdateGoal() {
        Goal updated = new Goal();
        updated.setDescription("Updated");
        updated.setDeadline(LocalDate.now().plusDays(1));
        updated.setCompleted(true);

        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        when(goalRepository.save(any(Goal.class))).thenReturn(goal);

        Goal result = goalService.updateGoal(1L, updated);
        assertEquals("Updated", result.getDescription());
        assertTrue(result.isCompleted());
    }

    @Test
    public void testDeleteGoal() {
        when(goalRepository.findById(1L)).thenReturn(Optional.of(goal));
        goalService.deleteGoal(1L);
        verify(goalRepository, times(1)).delete(goal);
    }

    @Test
    public void testDeleteGoalNotFound() {
        when(goalRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> goalService.deleteGoal(1L));
    }

    @Test
    public void testFindAllGoalsByUserId() {
        when(goalRepository.findByUserId(2L)).thenReturn(List.of(goal));
        List<Goal> goals = goalService.findAllGoalsByUserId(2L);
        assertEquals(1, goals.size());
    }
}
