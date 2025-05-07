package com.nadyagrishina.reflect_diary.service;

import com.nadyagrishina.reflect_diary.DTO.EntryRequestDTO;
import com.nadyagrishina.reflect_diary.DTO.EntryResponseDTO;

import java.util.List;

public interface EntryService {
    List<EntryResponseDTO> findAllEntries();
    EntryResponseDTO findById(Long id);
    EntryResponseDTO save(EntryRequestDTO entryRequestDTO);
    EntryResponseDTO updateEntry(Long id, EntryRequestDTO entryRequestDTO);
    void deleteById(Long id);
    List<EntryResponseDTO> findByUserId(Long userId);
}
