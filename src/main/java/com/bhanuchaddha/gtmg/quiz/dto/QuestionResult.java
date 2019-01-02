package com.bhanuchaddha.gtmg.quiz.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
public class QuestionResult {
    private String questionId;
    private boolean correct ;
    private int correctOption;
    private int selectedOption;
}
