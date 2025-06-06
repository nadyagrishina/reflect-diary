package com.nadyagrishina.reflect_diary.service;

import com.nadyagrishina.reflect_diary.model.Entry;
import com.nadyagrishina.reflect_diary.repository.EntryRepository;
import com.nadyagrishina.reflect_diary.service.service_impl.EntryServiceImpl;
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

public class EntryServiceImplTest {

    @Mock
    private EntryRepository entryRepository;

    @InjectMocks
    private EntryServiceImpl entryService;

    private Entry entry;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        entry = new Entry();
        entry.setId(1L);
        entry.setMainText("Test entry");
    }

    @Test
    public void testFindByIdExists() {
        when(entryRepository.findById(1L)).thenReturn(Optional.of(entry));
        Entry result = entryService.findById(1L);
        assertEquals("Test entry", result.getMainText());
    }

    @Test
    public void testFindByIdNotExists() {
        when(entryRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> entryService.findById(1L));
    }

    @Test
    public void testSave() {
        when(entryRepository.save(any(Entry.class))).thenAnswer(i -> i.getArgument(0));
        Entry saved = entryService.save(entry);
        assertNotNull(saved.getCreatedAt());
        verify(entryRepository, times(1)).save(entry);
    }

    @Test
    public void testDeleteByIdExists() {
        when(entryRepository.findById(1L)).thenReturn(Optional.of(entry));
        entryService.deleteById(1L);
        verify(entryRepository, times(1)).delete(entry);
    }

    @Test
    public void testDeleteByIdNotExists() {
        when(entryRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> entryService.deleteById(1L));
    }

    @Test
    public void testFindByUserId() {
        when(entryRepository.findByUserId(2L)).thenReturn(List.of(entry));
        List<Entry> result = entryService.findByUserId(2L);
        assertEquals(1, result.size());
        verify(entryRepository, times(1)).findByUserId(2L);
    }
}