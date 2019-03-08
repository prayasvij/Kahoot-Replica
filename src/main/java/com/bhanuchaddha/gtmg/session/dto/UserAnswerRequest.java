package com.bhanuchaddha.gtmg.session.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class UserAnswerRequest {
    private long userId;
    private String questionId;
    private int selectedOption;
}
