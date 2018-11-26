package com.bhanuchaddha.gtmg.quiz;

import com.bhanuchaddha.gtmg.quiz.model.Question;
import com.bhanuchaddha.gtmg.quiz.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

/**
 * Created by Bhanu Chaddha on 20-11-2018 08:37 PM.
 */
@RestController
@AllArgsConstructor
public class QuizResource {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizService quizService;


    @GetMapping("/question")
    public List<Question> getQuiz(){
        return questionRepository.findAll();
    }

    @PostMapping("/quiz")
    public ResponseEntity<Long> createQuiz(){
        return ResponseEntity.ok(quizService.createQuiz());
    }

    @GetMapping("/quiz/{quizId}")
    public Set<Question> getQuiz (@PathVariable("quizId") long quizId ){
            return quizService.getQuiz(quizId);
    }


}
