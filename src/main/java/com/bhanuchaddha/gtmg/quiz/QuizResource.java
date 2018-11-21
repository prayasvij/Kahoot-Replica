package com.bhanuchaddha.gtmg.quiz;

import com.bhanuchaddha.gtmg.quiz.model.Question;
import com.bhanuchaddha.gtmg.quiz.repository.QuestionRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bhanu Chaddha on 20-11-2018 08:37 PM.
 */
@RestController
@AllArgsConstructor
public class QuizResource {

    private QuestionRepository questionRepository;

    @GetMapping("/quiz")
    public List<Question> getQuiz(){
        return questionRepository.findAll();
    }

}
