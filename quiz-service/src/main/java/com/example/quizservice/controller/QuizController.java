package com.example.quizservice.controller;

import com.example.quizservice.dto.QuestionDTO;
import com.example.quizservice.dto.ResponseDTO;
import com.example.quizservice.model.QuizDto;
import com.example.quizservice.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/quiz")
public class QuizController {

    @Autowired
    QuizService quizService;

    @PostMapping("/create")
    public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizDto){
        return quizService.createQuiz(quizDto.getCategoryName(), quizDto.getNumQuestions(), quizDto.getTitle());
    }
    @PostMapping("/create/difficultyLevel")
    public ResponseEntity<String> createQuizByDifficulty(@RequestBody QuizDto quizDto){
        return quizService.createQuizByDifficulty(quizDto.getCategoryName(),  quizDto.getNumQuestions(), quizDto.getDifficultyLevel(), quizDto.getTitle());
    }

    @PostMapping("/get/{id}")
    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(@PathVariable Integer id){
        return quizService.getQuizQuestions(id);
    }

    @PostMapping("/submit/{id}")
    public ResponseEntity<Integer> submitQuiz(@PathVariable Integer id, @RequestBody List<ResponseDTO> responses){
        return quizService.calculateResult(id, responses);
    }


}
