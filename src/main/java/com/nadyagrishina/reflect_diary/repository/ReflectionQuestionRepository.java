package com.nadyagrishina.reflect_diary.repository;

import com.nadyagrishina.reflect_diary.model.ReflectionQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReflectionQuestionRepository extends JpaRepository<ReflectionQuestion, Long> {
    @Query(value = "SELECT * FROM reflection_questions ORDER BY RAND() LIMIT 1", nativeQuery = true)
    ReflectionQuestion findRandom();
}
