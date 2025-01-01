package com.example.question_service.service;

import com.example.question_service.dto.QuestionDTO;
import com.example.question_service.dto.ResponseDTO;
import com.example.question_service.model.DifficultyLevel;
import com.example.question_service.model.Question;
import com.example.question_service.repository.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    private QuestionRepository questionRepository;
    public ResponseEntity<List<Question>> getAllQuestion() {
        try {
            return new ResponseEntity<>(questionRepository.findAll(),HttpStatus.OK);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity<List<Question>>  getAllQuestionByCategory(String category) {

        try {
            return new ResponseEntity<>(questionRepository.findByCategory(category)
                    ,HttpStatus.OK);
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return new ResponseEntity<>(new ArrayList<>() , HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> addQuestion(Question question) throws Exception{
        Question question1= questionRepository.save(question);
        if(question1 != null)
            return ResponseEntity.ok("Question created successfully");
        return new ResponseEntity<>("Invalid Question body",HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<String> updateQuestionById(Integer questionId, Question question) {
        Question question1 = questionRepository.findById(questionId).get();

        if(question1 == null){
            return new ResponseEntity<>("Invalid Question body",HttpStatus.BAD_REQUEST);
        }
        if(question.getQuestion()!=null){
            question1.setQuestion(question.getQuestion());
        }
        if(question.getOption1()!=null){
            question1.setOption1(question.getOption1());
        }
        if(question.getOption2()!=null){
            question1.setOption2(question.getOption2());
        }
        if(question.getOption3()!=null){
            question1.setOption3(question.getOption3());
        }
        if(question.getOption4()!=null){
            question1.setOption4(question.getOption4());
        }
        if(question.getCategory()!=null){
            question1.setCategory(question.getCategory());
        }
        if(question.getCorrectOption()!=null){
            question1.setCorrectOption(question.getCorrectOption());
        }

        question1= questionRepository.save(question1);
        if(question1 != null)
            return ResponseEntity.ok("Question created successfully");
        return new ResponseEntity<>("Invalid Question body",HttpStatus.BAD_REQUEST);

    }

    public Question getQuestionById(Integer questionId){
        return questionRepository.findById(questionId).get();
    }

    public ResponseEntity<String> deleteQuestionById(Integer questionId) {
        Question question = getQuestionById(questionId);
        if(question == null)
            return new ResponseEntity<>("Invalid Question ID",HttpStatus.BAD_REQUEST);
        questionRepository.delete(question);
        return ResponseEntity.ok("Question Deleted successfully");
    }

    public ResponseEntity<List<Integer>> getQuestionForQuiz(String category, Integer numQ) {
        List<Integer> questionIds = questionRepository.findRandomQuestionsByCategory(category,numQ);
        return new ResponseEntity<>(questionIds , HttpStatus.OK);
    }

    public ResponseEntity<List<QuestionDTO>> getQuestionByIds(List<Integer> questionIds) {
        List<QuestionDTO> questionDTOS = new ArrayList<>();

        for (Integer id : questionIds){
            Question question = questionRepository.findById(id).get();
            if(question == null)
                return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
            QuestionDTO questionDTO=QuestionDTO.builder()
                    .id(question.getId())
                    .question(question.getQuestion())
                    .option1(question.getOption1())
                    .option2(question.getOption2())
                    .option3(question.getOption3())
                    .option4(question.getOption4())
                    .build();
            questionDTOS.add(questionDTO);
        }

        return new ResponseEntity<>(questionDTOS  , HttpStatus.OK);
    }

    public ResponseEntity<Integer> getScore(List<ResponseDTO> responseDTOs) {
        int rightAnswer = 0 ;

        for (ResponseDTO responseDTO:responseDTOs){
            Question question = questionRepository.findById(responseDTO.getId()).get();
            if(question == null)
                return new ResponseEntity<>(0 , HttpStatus.BAD_REQUEST);
            if(responseDTO.getAnswer().equals(question.getCorrectOption()))
                rightAnswer++;
        }

        return new ResponseEntity<>(rightAnswer ,HttpStatus.OK);
    }

    public List<Integer> getQuestionsForQuizDiffculty(String category, String difficultyLevel, int numQuestions) {
        Pageable pageable = PageRequest.of(0, numQuestions); // Limit the number of results
        return questionRepository.findRandomQuestionsByDifficulty(DifficultyLevel.valueOf(difficultyLevel), pageable);
    }
}
