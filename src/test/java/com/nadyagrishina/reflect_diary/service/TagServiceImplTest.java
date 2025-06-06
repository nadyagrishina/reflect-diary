package com.nadyagrishina.reflect_diary.service;

import com.nadyagrishina.reflect_diary.model.Tag;
import com.nadyagrishina.reflect_diary.repository.EntryRepository;
import com.nadyagrishina.reflect_diary.repository.TagRepository;
import com.nadyagrishina.reflect_diary.service.service_impl.TagServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TagServiceImplTest {

    @Mock
    private TagRepository tagRepository;

    @Mock
    private EntryRepository entryRepository;

    @InjectMocks
    private TagServiceImpl tagService;

    private Tag tag;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        tag = new Tag();
        tag.setId(1L);
        tag.setName("Reflection");
    }

    @Test
    public void testFindAllTags() {
        when(tagRepository.findAll()).thenReturn(List.of(tag));

        List<Tag> result = tagService.findAllTags();

        assertEquals(1, result.size());
        assertEquals("Reflection", result.get(0).getName());
    }

    @Test
    public void testFindTagByIdExists() {
        when(tagRepository.findById(1L)).thenReturn(Optional.of(tag));

        Tag result = tagService.findTagById(1L);

        assertNotNull(result);
        assertEquals("Reflection", result.getName());
    }

    @Test
    public void testFindTagByIdNotFound() {
        when(tagRepository.findById(1L)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> tagService.findTagById(1L));
    }

    @Test
    public void testCreateTagAlreadyExists() {
        when(tagRepository.findByName("Reflection")).thenReturn(Optional.of(tag));

        Tag result = tagService.createTag("Reflection");

        assertEquals("Reflection", result.getName());
        verify(tagRepository, never()).save(any());
    }

    @Test
    public void testCreateTagNew() {
        when(tagRepository.findByName("NewTag")).thenReturn(Optional.empty());
        when(tagRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Tag result = tagService.createTag("NewTag");

        assertEquals("NewTag", result.getName());
        verify(tagRepository, times(1)).save(any());
    }

    @Test
    public void testFindAllById() {
        when(tagRepository.findAllById(List.of(1L))).thenReturn(List.of(tag));

        List<Tag> result = tagService.findAllById(List.of(1L));

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }
}