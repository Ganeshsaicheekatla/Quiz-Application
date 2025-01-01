package com.example.question_service.controller;

import com.example.question_service.dto.QuestionDTO;
import com.example.question_service.dto.ResponseDTO;
import com.example.question_service.model.DifficultyLevel;
import com.example.question_service.model.Question;
import com.example.question_service.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @GetMapping("/getAllQuestions")
    public ResponseEntity<List<Question>> getAllQuestion(){
        return  questionService.getAllQuestion();
    }

    @GetMapping("/category/{category}")
    public ResponseEntity<List<Question>> getAllQuestionByCategory(@PathVariable String category){
        return questionService.getAllQuestionByCategory(category);

    }

    @PostMapping("/addQuestion")
    public ResponseEntity<String> addQuestion(@RequestBody Question question) throws Exception {
        return  questionService.addQuestion(question);

    }

    @PutMapping("/updateQuestion/questionId/{questionId}")
    public ResponseEntity<String> updateQuestionById(@PathVariable Integer questionId ,@RequestBody Question question )throws Exception{
        return questionService.updateQuestionById(questionId,question);

    }

    @DeleteMapping("/deleteQuestion/questionId/{questionId}")
    public ResponseEntity<String> deleteQuestionById(@PathVariable Integer questionId)throws Exception{
        return questionService.deleteQuestionById(questionId);

    }

    @GetMapping("/generate")
    public ResponseEntity<List<Integer>> getQuestionForQuiz(@RequestParam String category,@RequestParam Integer numQ){
        return questionService.getQuestionForQuiz(category,numQ);
    }

    @GetMapping("/generateByDifficulty")
    public ResponseEntity<List<Integer>> getQuestionsForDiffculty(
            @RequestParam String category,
            @RequestParam String difficultyLevel,
            @RequestParam Integer numQ) {

        List<Integer> questions = questionService.getQuestionsForQuizDiffculty(category, difficultyLevel, numQ);
        return ResponseEntity.ok(questions);
    }



    @PostMapping("/getQuestions")
    public ResponseEntity<List<QuestionDTO>> getQuestionByIds(@RequestBody List<Integer> questionIds){
        return questionService.getQuestionByIds(questionIds);
    }

    @PostMapping("/getScore")
    public ResponseEntity<Integer> getScore(@RequestBody List<ResponseDTO> responseDTOs){
        return questionService.getScore(responseDTOs);
    }


}
