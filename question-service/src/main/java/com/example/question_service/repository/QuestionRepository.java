package com.example.question_service.repository;

import com.example.question_service.model.DifficultyLevel;
import com.example.question_service.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.util.List;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {


    List<Question> findByCategory(String category);

    @Query(value = "select q.id from question q where q.category = :category Limit :numQ", nativeQuery = true)
    List<Integer> findRandomQuestionsByCategory(@Param("category") String category, @Param("numQ") int numQ);

    @Query(value = "SELECT q.id FROM Question q WHERE q.difficultyLevel = :difficultyLevel ORDER BY FUNCTION('RAND')")
    List<Integer> findRandomQuestionsByDifficulty(@Param("difficultyLevel") DifficultyLevel difficultyLevel, Pageable pageable);

}

