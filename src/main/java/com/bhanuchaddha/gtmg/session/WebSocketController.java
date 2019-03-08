package com.bhanuchaddha.gtmg.session;

import com.bhanuchaddha.gtmg.session.dto.UserAnswerRequest;
import com.bhanuchaddha.gtmg.session.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;


@Controller
@RequiredArgsConstructor
public class WebSocketController {


    private final SimpMessagingTemplate template;
    private final UserService userService;

    @MessageMapping("/{sessionId}/answer")
    //@SendTo("/dashboard")
    public void submitAnswer(@DestinationVariable long sessionId, UserAnswerRequest answer) {
        userService.submitAnswer(answer);
        this.template.convertAndSend("/dashboard",answer);

    }
}
