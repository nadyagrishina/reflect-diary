package com.nadyagrishina.reflect_diary.service.service_impl;

import com.nadyagrishina.reflect_diary.model.Entry;
import com.nadyagrishina.reflect_diary.repository.EntryRepository;
import com.nadyagrishina.reflect_diary.service.EntryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;

    public EntryServiceImpl(EntryRepository entryRepository) {
        this.entryRepository = entryRepository;
    }

    @Override
    public Entry findById(Long id) {
        return entryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entry with id " + id + " not found"));
    }

    @Override
    @Transactional
    public Entry save(Entry entry) {
        entry.setCreatedAt(LocalDateTime.now());
        return entryRepository.save(entry);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entry with id " + id + " not found"));
        entryRepository.delete(entry);
    }

    @Override
    public List<Entry> findByUserId(Long userId) {
        return entryRepository.findByUserId(userId);
    }
}
