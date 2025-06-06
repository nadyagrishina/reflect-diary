package com.nadyagrishina.reflect_diary.service.service_impl;

import com.nadyagrishina.reflect_diary.model.Tag;
import com.nadyagrishina.reflect_diary.repository.EntryRepository;
import com.nadyagrishina.reflect_diary.repository.TagRepository;
import com.nadyagrishina.reflect_diary.service.TagService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {
    TagRepository tagRepository;
    EntryRepository entryRepository;

    public TagServiceImpl(TagRepository tagRepository,  EntryRepository entryRepository) {
        this.tagRepository = tagRepository;
        this.entryRepository = entryRepository;
    }

    @Override
    public List<Tag> findAllTags() {
        return tagRepository.findAll();
    }

    @Override
    public Tag findTagById(Long id) {
        return tagRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Tag not found with id: " + id));
    }

    @Override
    public Tag createTag(String tagName) {
        Optional<Tag> existing = tagRepository.findByName(tagName);
        if (existing.isPresent()) return existing.get();

        Tag tag = new Tag();
        tag.setName(tagName);
        return tagRepository.save(tag);
    }

    @Override
    public List<Tag> findAllById(List<Long> tagIds) {
        return tagRepository.findAllById(tagIds);
    }

}
