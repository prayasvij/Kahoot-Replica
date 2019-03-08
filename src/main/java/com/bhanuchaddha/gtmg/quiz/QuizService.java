package com.bhanuchaddha.gtmg.quiz;

import com.bhanuchaddha.gtmg.quiz.dto.CheckAnswerRequest;
import com.bhanuchaddha.gtmg.quiz.dto.CheckQuizRequest;
import com.bhanuchaddha.gtmg.quiz.dto.QuestionResult;
import com.bhanuchaddha.gtmg.quiz.dto.QuizResult;
import com.bhanuchaddha.gtmg.quiz.model.Question;
import com.bhanuchaddha.gtmg.quiz.model.Quiz;
import com.bhanuchaddha.gtmg.quiz.repository.QuestionRepository;
import com.bhanuchaddha.gtmg.quiz.repository.QuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

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

    public QuestionResult checkAnswer(CheckAnswerRequest answer) {

        Optional<Integer> ans = questionRepository.findById(answer.getQuestionId())
                                .map(q->q.getAnswer());
       return QuestionResult.builder()
               .questionId(answer.getQuestionId())
               .selectedOption(answer.getSelectedOption())
               .correctOption(ans.orElse(-1))
               .correct(ans.map(a->a==answer.getSelectedOption()).orElse(false))
               .build();
    }

    public QuizResult checkQuiz(CheckQuizRequest quiz) {
        List<QuestionResult> questionResults = quizRepository.findById(quiz.getQuizId())
                .map(q-> q.getQuestions()).orElse(Collections.emptySet())
                .stream()
                .map(question -> checkQuestion(question, quiz))
                .collect(Collectors.toList());
        return QuizResult.builder()
                .id(quiz.getQuizId())
                .questionResults(questionResults)
                .expectedSuccessPercentage(80d)
                .build();
    }

    private QuestionResult checkQuestion(Question question, CheckQuizRequest quiz) {
        // to check if the question has been answered
        Optional<CheckAnswerRequest> ans = quiz.getAnswer()
                .stream()
                .filter(a->a.getQuestionId().equals(question.getId()))
                .findFirst();

        return QuestionResult.builder()
                .questionId(question.getId())
                .selectedOption(ans.map(a-> a.getSelectedOption()).orElse(-1))
                .correctOption(question.getAnswer())
                .correct(ans.map(a-> a.getSelectedOption()==question.getAnswer()).orElse(false))
                .build();
    }
}
