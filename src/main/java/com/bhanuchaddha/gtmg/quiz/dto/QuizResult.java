package com.bhanuchaddha.gtmg.quiz.dto;

import lombok.*;

import java.util.List;

@NoArgsConstructor
@Builder
@Getter
public class QuizResult {
    private long id;
    @Singular
    private List<QuestionResult> questionResults;
    private Double successPercentage;
    private Double percentage;
    private boolean success;

//    TODO: add validations that required values are available
    private Double getPercentage() {
        long successCount = questionResults.stream()
                .filter(questionResult -> questionResult.isCorrect())
                .count();
        percentage = successCount*100d/questionResults.size();
        return percentage;
    }

    public Boolean getSuccess() {
        return percentage.compareTo(successPercentage) >= 0;
    }
}
