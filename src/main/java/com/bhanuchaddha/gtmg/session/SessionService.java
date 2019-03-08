package com.bhanuchaddha.gtmg.session;

import com.bhanuchaddha.gtmg.quiz.QuizService;
import com.bhanuchaddha.gtmg.session.repository.SessionRepository;
import com.bhanuchaddha.gtmg.session.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class SessionService {

    private final QuizService quizService;
    private final SessionRepository sessionRepository;
    private final UserRepository userRepository ;
    private final SimpMessagingTemplate template;


    public Long createSession(){
        long quizId = quizService.createQuiz();
        Session session =Session.builder().quizId(quizId).build();
        return sessionRepository.save(session).getId();
    }

    public Long joinSession (Long sessionId, String userName ) {
        User u = sessionRepository.findById(sessionId)
                .map( s->
                    userRepository.save(User.builder()
                    .name(userName)
                    .session(s)
                    .build())
                )
                .orElseThrow(() -> new RuntimeException("Session not found"));
        return  u.getId();
    }


    public Session getSession(long sessionId){
        return sessionRepository.findById(sessionId)
                .orElseThrow(() -> new RuntimeException("Session not found"));
    }


}
