package com.nadyagrishina.reflect_diary.repository;

import com.nadyagrishina.reflect_diary.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TagRepository extends JpaRepository<Tag, Long> {
    Optional<Tag> findByName(String tagName);
    List<Tag> findAllById(Iterable<Long> ids);
}
