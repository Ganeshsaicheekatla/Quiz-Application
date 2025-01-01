package com.example.quizservice.feign;

import com.example.quizservice.dto.QuestionDTO;
import com.example.quizservice.dto.ResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {
    @GetMapping("/question/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz
            (@RequestParam String category,@RequestParam Integer numQ);

    @PostMapping("/question/getQuestions")
    public ResponseEntity<List<QuestionDTO>> getQuestionByIds(@RequestBody List<Integer> questionIds);

    @PostMapping("/question/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<ResponseDTO> responses);

    @GetMapping("/question/generateByDifficulty")
    public ResponseEntity<List<Integer>> getQuestionsForDiffculty(
            @RequestParam String category,
            @RequestParam String difficultyLevel,
            @RequestParam Integer numQ);

}
