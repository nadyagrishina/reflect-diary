package com.nadyagrishina.reflect_diary.service.service_impl;

import com.nadyagrishina.reflect_diary.model.ReflectionQuestion;
import com.nadyagrishina.reflect_diary.repository.ReflectionQuestionRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ReflectionQuestionServiceImplTest {

    @Mock
    private ReflectionQuestionRepository reflectionQuestionRepository;

    @InjectMocks
    private ReflectionQuestionServiceImpl reflectionQuestionService;

    private ReflectionQuestion question;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        question = new ReflectionQuestion();
        question.setId(1L);
        question.setText("How do you feel today?");
    }

    @Test
    public void testGetRandomQuestion() {
        when(reflectionQuestionRepository.findRandom()).thenReturn(question);

        ReflectionQuestion result = reflectionQuestionService.getRandomQuestion();

        assertNotNull(result);
        assertEquals(question.getId(), result.getId());
        assertEquals(question.getText(), result.getText());
    }

    @Test
    public void testFindByIdSuccess() {
        when(reflectionQuestionRepository.findById(1L)).thenReturn(Optional.of(question));

        ReflectionQuestion result = reflectionQuestionService.findById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getId());
    }

    @Test
    public void testFindByIdNotFound() {
        when(reflectionQuestionRepository.findById(1L)).thenReturn(Optional.empty());

        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class, () -> {
            reflectionQuestionService.findById(1L);
        });

        assertTrue(exception.getMessage().contains("ReflectionQuestion with id: 1"));
    }
}
