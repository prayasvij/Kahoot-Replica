package com.bhanuchaddha.gtmg.session.service;

import com.bhanuchaddha.gtmg.quiz.repository.QuestionRepository;
import com.bhanuchaddha.gtmg.session.UserAnswer;
import com.bhanuchaddha.gtmg.session.dto.UserAnswerRequest;
import com.bhanuchaddha.gtmg.session.repository.UserAnswerRepository;
import com.bhanuchaddha.gtmg.session.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {

    private final QuestionRepository questionRepository;
    private final UserAnswerRepository userAnswerRepository;
    private final UserRepository userRepository;
    private final SimpMessagingTemplate template;


    //TODO : same answer can be submitted twice
    public void submitAnswer(UserAnswerRequest answerRequest) {
        int correctAnswer = questionRepository.findById(answerRequest.getQuestionId())
                .map(q->q.getAnswer())
                .orElseThrow(() -> new RuntimeException("Question not found"));
        userRepository.findById(answerRequest.getUserId())
                .map( u-> userAnswerRepository.save(
                        UserAnswer.builder()
                                .questionId(answerRequest.getQuestionId())
                                .selectedOption(answerRequest.getSelectedOption())
                                .correctOption(correctAnswer)
                                .user(u)
                                .build()
                ))
                .map(userAnswer-> {
                    this.template.convertAndSend("/userRanking", userAnswer.getUser().getSession().getUserRanking()) ;
                    return userAnswer ;
                })
                .filter(UserAnswer::isCorrect)
                .map(ua->userRepository.save(ua.getUser().increaseScore()))
        ;

    }
}
