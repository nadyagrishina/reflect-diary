package com.nadyagrishina.reflect_diary.mapper;

import com.nadyagrishina.reflect_diary.DTO.EntryRequestDTO;
import com.nadyagrishina.reflect_diary.DTO.EntryResponseDTO;
import com.nadyagrishina.reflect_diary.model.Entry;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EntryMapper {
    public Entry toEntity(EntryRequestDTO dto){
        Entry entry = new Entry();
        entry.setDate(dto.getDate());
        entry.setMainText(dto.getMainText());
        entry.setHighlights(dto.getHighlights());
        entry.setDifficulties(dto.getDifficulties());
        entry.setTomorrowPlan(dto.getTommorowPlan());
        entry.setScore(dto.getScore());
        entry.setMood(dto.getMood());
        entry.setTags(null);
        return entry;
    }

    public EntryResponseDTO toDTO(Entry entry){
        EntryResponseDTO dto = new EntryResponseDTO();
        dto.setId(entry.getId());
        dto.setDate(entry.getDate());
        dto.setMainText(entry.getMainText());
        dto.setHighlights(entry.getHighlights());
        dto.setDifficulties(entry.getDifficulties());
        dto.setTomorrowPlan(entry.getTomorrowPlan());
        dto.setScore(entry.getScore());
        dto.setMood(entry.getMood());
        dto.setCreatedAt(entry.getCreatedAt());
        if (entry.getReflectionQuestion() != null) {
            dto.setReflectionQuestion(entry.getReflectionQuestion().getText());
        }
        dto.setTags(
                entry.getTags() != null
                        ? entry.getTags().stream().map(tag -> tag.getName()).toList()
                        : List.of()
        );

        return dto;
    }

    public void updateEntryFromDto(EntryRequestDTO entryRequestDTO, Entry entry){
        entry.setDate(entryRequestDTO.getDate());
        entry.setMainText(entryRequestDTO.getMainText());
        entry.setHighlights(entryRequestDTO.getHighlights());
        entry.setDifficulties(entryRequestDTO.getDifficulties());
        entry.setTomorrowPlan(entryRequestDTO.getTommorowPlan());
        entry.setScore(entryRequestDTO.getScore());
        entry.setMood(entryRequestDTO.getMood());
    }
}
