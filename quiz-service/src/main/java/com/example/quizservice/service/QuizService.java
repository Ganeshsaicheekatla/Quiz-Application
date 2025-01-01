package com.example.quizservice.service;

import com.example.quizservice.dto.QuestionDTO;
import com.example.quizservice.dto.ResponseDTO;
import com.example.quizservice.model.Quiz;
import com.example.quizservice.repository.QuizDao;
import com.example.quizservice.feign.QuizInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuizService {

    @Autowired
    QuizDao quizDao;

    @Autowired
    QuizInterface quizInterface;

    public ResponseEntity<String> createQuiz(String category, int numQ, String title) {

        List<Integer> questions = quizInterface.getQuestionForQuiz(category, numQ).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);

    }

    public ResponseEntity<List<QuestionDTO>> getQuizQuestions(Integer id) {
          Quiz quiz = quizDao.findById(id).get();
          List<Integer> questionIds = quiz.getQuestionIds();
          ResponseEntity<List<QuestionDTO>> questions = quizInterface.getQuestionByIds(questionIds);
          return questions;

    }

    public ResponseEntity<Integer> calculateResult(Integer id, List<ResponseDTO> responses) {
        ResponseEntity<Integer> score = quizInterface.getScore(responses);
        return score;
    }

    public ResponseEntity<String> createQuizByDifficulty(String categoryName, Integer numQuestions, String difficultyLevel, String title) {
        List<Integer> questions = quizInterface.getQuestionsForDiffculty(categoryName,difficultyLevel, numQuestions).getBody();
        Quiz quiz = new Quiz();
        quiz.setTitle(title);
        quiz.setQuestionIds(questions);
        quizDao.save(quiz);

        return new ResponseEntity<>("Success", HttpStatus.CREATED);
    }
}
