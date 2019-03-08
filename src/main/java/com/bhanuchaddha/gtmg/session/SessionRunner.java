package com.bhanuchaddha.gtmg.session;

import com.bhanuchaddha.gtmg.quiz.QuizService;
import com.bhanuchaddha.gtmg.quiz.model.Question;
import com.bhanuchaddha.gtmg.session.repository.SessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.IntStream;

import static com.bhanuchaddha.gtmg.session.SessionState.*;

@Component
@RequiredArgsConstructor
public class SessionRunner {

    private final SimpMessagingTemplate template;
    private final SessionRepository sessionRepository;
    private final QuizService quizService;

    //@Async
    public void beginSession(long sessionId) {

        Set<Question> questions = sessionRepository.findById(sessionId)
                .map(s-> quizService.getQuiz(s.getQuizId()))
                .orElseThrow(() -> new RuntimeException("Session not found"));

        sendSessionState(INITIAL_COUNTDOWN);
        showCountdown(10);

        questions.stream()
                .forEach(q->
                        {
                            sendSessionState(QUESTION_WAIT_COUNTDOWN);
                            showCountdown(3);

                            sendSessionState(SHOW_QUESTION);
                            this.template.convertAndSend("/question",q);

                            // keep question visible for  10 s
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            sendSessionState(SHOW_ANSWER);
                            this.template.convertAndSend("/answer",q.getAnswer());
                            try {
                                Thread.sleep(1000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }

                        }
                );
    }

    private void showCountdown(int count) {
        IntStream.rangeClosed(1,count)
                .map(i -> count - i)
                .forEach( i-> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    this.template.convertAndSend("/countdown",i);
                        }

                );
    }

    private void sendSessionState(SessionState sessionState) {
        this.template.convertAndSend("/state",sessionState);
    }
}
