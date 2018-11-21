package com.bhanuchaddha.gtmg.quiz.model;

import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Bhanu Chaddha on 20-11-2018 07:53 PM.
 */
@Data
@Entity
public class Question {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    private String description;
    private QuestionType type;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ques_id")
    private List<Option> options = new ArrayList<>();
    private int answer;

    private AtomicInteger optionCount = new AtomicInteger(0);

    public void addOption(String optionContent, boolean correctAnswer){
        if (options.size()==4){
            throw new IllegalArgumentException("Only 4 options can be added");
        }
        options.add(new Option(optionCount.incrementAndGet(), optionContent));

        if(correctAnswer){
            if (answer!=0) {
                throw new IllegalArgumentException("Only 1 option can be correct");
            } else answer = optionCount.get();
        }

        if(optionCount.get()==4){
            Collections.shuffle(options);
        }
    }
}
