package com.nadyagrishina.reflect_diary.controller;

import com.nadyagrishina.reflect_diary.model.Entry;
import com.nadyagrishina.reflect_diary.model.ReflectionQuestion;
import com.nadyagrishina.reflect_diary.model.Tag;
import com.nadyagrishina.reflect_diary.model.User;
import com.nadyagrishina.reflect_diary.service.EntryService;
import com.nadyagrishina.reflect_diary.service.ReflectionQuestionService;
import com.nadyagrishina.reflect_diary.service.TagService;
import com.nadyagrishina.reflect_diary.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;

import java.security.Principal;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class EntryControllerTest {

    @Mock
    private EntryService entryService;

    @Mock
    private ReflectionQuestionService reflectionQuestionService;

    @Mock
    private TagService tagService;

    @Mock
    private UserService userService;

    @Mock
    private Principal principal;

    @Mock
    private Model model;

    @InjectMocks
    private EntryController entryController;

    private Entry entry;
    private User user;
    private ReflectionQuestion question;
    private Tag tag;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        entry = new Entry();
        entry.setId(1L);
        entry.setDate(LocalDate.now());

        user = new User();
        user.setId(1L);
        user.setUsername("testuser");

        question = new ReflectionQuestion();
        question.setId(10L);
        question.setText("What did you learn today?");

        tag = new Tag();
        tag.setId(5L);
        tag.setName("TestTag");
    }

    @Test
    public void testGetAllEntries() {
        when(principal.getName()).thenReturn("testuser");
        when(userService.findByUsername("testuser")).thenReturn(user);
        when(entryService.findByUserId(1L)).thenReturn(List.of(entry));

        String view = entryController.getAllEntries(model, principal);

        assertEquals("entries", view);
        verify(model).addAttribute(eq("entries"), any());
    }

    @Test
    public void testShowCreateForm_withQuestion() {
        when(reflectionQuestionService.getRandomQuestion()).thenReturn(question);
        when(tagService.findAllTags()).thenReturn(List.of(tag));

        String view = entryController.showCreateForm(model);

        assertEquals("create-entry", view);
        verify(model).addAttribute(eq("entry"), any(Entry.class));
        verify(model).addAttribute("question", question);
        verify(model).addAttribute("allTags", List.of(tag));
    }

    @Test
    public void testCreateEntry_withTags() {
        List<Long> tagIds = List.of(5L);
        when(principal.getName()).thenReturn("testuser");
        when(userService.findByUsername("testuser")).thenReturn(user);
        when(tagService.findAllById(tagIds)).thenReturn(List.of(tag));

        entry.setTags(null);
        String result = entryController.createEntry(entry, principal, tagIds);

        assertEquals("redirect:/entries", result);
        assertEquals(user, entry.getUser());
        assertEquals(List.of(tag), entry.getTags());
        verify(entryService).save(entry);
    }

    @Test
    public void testDeleteEntry() {
        String result = entryController.deleteEntry(1L);

        assertEquals("redirect:/entries", result);
        verify(entryService).deleteById(1L);
    }
}
