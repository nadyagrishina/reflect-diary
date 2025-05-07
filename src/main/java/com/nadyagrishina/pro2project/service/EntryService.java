package com.nadyagrishina.pro2project.service;

import com.nadyagrishina.pro2project.DTO.EntryRequestDTO;
import com.nadyagrishina.pro2project.DTO.EntryResponseDTO;

import java.util.List;

public interface EntryService {
    List<EntryResponseDTO> findAllEntries();
    EntryResponseDTO findById(Long id);
    EntryResponseDTO save(EntryRequestDTO entryRequestDTO);
    EntryResponseDTO updateEntry(Long id, EntryRequestDTO entryRequestDTO);
    void deleteById(Long id);
    List<EntryResponseDTO> findByUserId(Long userId);
}
