package com.nadyagrishina.reflect_diary.service.service_impl;

import com.nadyagrishina.reflect_diary.DTO.EntryRequestDTO;
import com.nadyagrishina.reflect_diary.DTO.EntryResponseDTO;
import com.nadyagrishina.reflect_diary.mapper.EntryMapper;
import com.nadyagrishina.reflect_diary.model.Entry;
import com.nadyagrishina.reflect_diary.model.Tag;
import com.nadyagrishina.reflect_diary.repository.EntryRepository;
import com.nadyagrishina.reflect_diary.repository.TagRepository;
import com.nadyagrishina.reflect_diary.service.EntryService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EntryServiceImpl implements EntryService {

    private final EntryRepository entryRepository;
    private final EntryMapper entryMapper;
    private final TagRepository tagRepository;

    public EntryServiceImpl(EntryRepository entryRepository, EntryMapper entryMapper, TagRepository tagRepository) {
        this.entryRepository = entryRepository;
        this.entryMapper = entryMapper;
        this.tagRepository = tagRepository;
    }

    @Override
    public List<EntryResponseDTO> findAllEntries() {
        List<Entry> entries = entryRepository.findAll();
        return entries.stream().map(entryMapper::toDTO).toList();
    }

    @Override
    public EntryResponseDTO findById(Long id) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entry with id " + id + " not found"));
        return entryMapper.toDTO(entry);
    }

    @Override
    @Transactional
    public EntryResponseDTO save(EntryRequestDTO entryRequestDTO) {
        Entry entry = entryMapper.toEntity(entryRequestDTO);

        List<Tag> tags = tagRepository.findByIdIn(entryRequestDTO.getTagIds());
        entry.setTags(tags);

        entryRepository.save(entry);
        return entryMapper.toDTO(entry);
    }

    @Override
    @Transactional
    public EntryResponseDTO updateEntry(Long id, EntryRequestDTO entryRequestDTO) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entry with id " + id + " not found"));
        entryMapper.updateEntryFromDto(entryRequestDTO, entry);

        List<Tag> tags = tagRepository.findByIdIn(entryRequestDTO.getTagIds());
        entry.setTags(tags);

        entryRepository.save(entry);
        return entryMapper.toDTO(entry);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        Entry entry = entryRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Entry with id " + id + " not found"));
        entryRepository.delete(entry);
    }

    @Override
    public List<EntryResponseDTO> findByUserId(Long userId) {
        List<Entry> entries = entryRepository.findByUserId(userId);
        return entries.stream().map(entryMapper::toDTO).toList();
    }
}
