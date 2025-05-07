package com.nadyagrishina.reflect_diary.repository;

import com.nadyagrishina.reflect_diary.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    List<Tag> findByIdIn(List<Long> tagIds);
}
