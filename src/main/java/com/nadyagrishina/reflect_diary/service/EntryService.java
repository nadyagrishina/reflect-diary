package com.nadyagrishina.reflect_diary.service;

import com.nadyagrishina.reflect_diary.model.Entry;

import java.util.List;

public interface EntryService {
    Entry findById(Long id);
    Entry save(Entry entry);
    void deleteById(Long id);
    List<Entry> findByUserId(Long userId);
}
