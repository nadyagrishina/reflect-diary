package com.nadyagrishina.reflect_diary.service;

import com.nadyagrishina.reflect_diary.model.Tag;

import java.util.List;

public interface TagService {
    List<Tag> findAllTags();
    Tag findTagById(Long id);
    Tag createTag(String tagName);
    List<Tag> findAllById(List<Long> TagIds);
    List<Tag> findAllByEntryId(Long entryId);
}
