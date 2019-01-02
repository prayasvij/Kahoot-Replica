package com.bhanuchaddha.gtmg.quiz.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CheckQuizRequest {
    private long quizId;
    private List<CheckAnswerRequest> answer;
}
