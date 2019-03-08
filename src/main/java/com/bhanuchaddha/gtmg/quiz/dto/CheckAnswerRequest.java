package com.bhanuchaddha.gtmg.quiz.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CheckAnswerRequest {
    private String questionId;
    private int selectedOption;
}
