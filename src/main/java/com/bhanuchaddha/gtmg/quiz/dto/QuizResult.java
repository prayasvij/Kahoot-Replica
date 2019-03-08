package com.bhanuchaddha.gtmg.quiz.dto;

import lombok.*;

import java.util.List;

@Builder
@Getter
public class QuizResult {
    private long id;
    @Singular
    private List<QuestionResult> questionResults;
    private Double expectedSuccessPercentage;
    private Double scorePercentage;
    private boolean success;

//    TODO: add validations that required values are available
    public Double getScorePercentage() {
        long successCount = questionResults.stream()
                .filter(questionResult -> questionResult.isCorrect())
                .count();
        scorePercentage = (double)(successCount * 100)/questionResults.size();
        return scorePercentage;
    }

    public boolean getSuccess() {
         this.success= scorePercentage.compareTo(expectedSuccessPercentage) >= 0;
         return this.success;
    }
}
