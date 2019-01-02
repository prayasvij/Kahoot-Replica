package com.bhanuchaddha.gtmg.quiz.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckAnswerRequest {
    private String questionId;
    private int selectedOption;
}
