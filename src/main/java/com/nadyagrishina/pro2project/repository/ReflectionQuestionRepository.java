package com.nadyagrishina.pro2project.repository;

import com.nadyagrishina.pro2project.model.ReflectionQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ReflectionQuestionRepository extends JpaRepository<ReflectionQuestion, Long> {
    @Query("SELECT q FROM ReflectionQuestion q ORDER BY function('RAND') LIMIT 1")
    ReflectionQuestion findRandom();
}
