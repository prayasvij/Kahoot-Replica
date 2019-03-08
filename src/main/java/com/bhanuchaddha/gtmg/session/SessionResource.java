package com.bhanuchaddha.gtmg.session;

import com.bhanuchaddha.gtmg.session.dto.UserAnswerRequest;
import com.bhanuchaddha.gtmg.session.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/session")
@RequiredArgsConstructor
public class SessionResource {

    private final SessionService sessionService;
    private final UserService userService;
    private final SessionRunner sessionRunner;

    @PostMapping
    public Long createSession() {
        return sessionService.createSession();
    }

    @PutMapping("/{sessionId}")
    public Long joinSession(@PathVariable Long sessionId, @RequestBody String userName) {
        return sessionService.joinSession(sessionId, userName);
    }

    @GetMapping("/{sessionId}/begin")
    public void beginSession(@PathVariable Long sessionId) {
        sessionRunner.beginSession(sessionId);
    }

    @PostMapping("/{sessionId}/answer")
    public void submitAnswer(@PathVariable long sessionId, @RequestBody UserAnswerRequest answerRequest) {
        userService.submitAnswer(answerRequest);
    }

    @GetMapping("/{sessionId}")
    public Session getSession(@PathVariable Long sessionId){
        return sessionService.getSession(sessionId);
    }




}

