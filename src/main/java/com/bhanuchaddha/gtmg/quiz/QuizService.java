package com.bhanuchaddha.gtmg.quiz;

import com.bhanuchaddha.gtmg.quiz.model.Question;
import com.bhanuchaddha.gtmg.quiz.model.Quiz;
import com.bhanuchaddha.gtmg.quiz.repository.QuestionRepository;
import com.bhanuchaddha.gtmg.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by Bhanu Chaddha on 26-11-2018 12:17 AM.
 *
 * Service to create the quiz.
 */
@Service
public class QuizService {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private QuizRepository quizRepository;

    public long createQuiz(){
        Quiz quiz = new Quiz();
        List<Question> questions = questionRepository.findAll();
        ThreadLocalRandom.current().ints(0, questions.size()-1)
                .distinct()
                .limit(10)
                .forEach(index -> quiz.addQuestions(questions.get(index)));
       return quizRepository.save(quiz).getId();
    }

    public Set<Question> getQuiz(long id){
        return quizRepository.findById(id).map(Quiz::getQuestions)
                .orElseThrow(()->  new RuntimeException("Quiz not found"));
    }
}
