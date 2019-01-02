package com.bhanuchaddha.gtmg.quiz.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Bhanu Chaddha on 20-11-2018 07:53 PM.
 */
@Data
@Entity
@NoArgsConstructor
public class Question {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid2")
    private String id;
    @Column(length = 2000)
    private String description;
    private QuestionType type;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "ques_id")
    private List<Option> options = new ArrayList<>();

    @JsonIgnore
    private int answer;

    @JsonIgnore
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
            shuffle();
        }
    }

    private void shuffle() {
        int newCorrectOpt = new Random().nextInt(4)+1;
        Optional<Option> currentCorrect =options.stream()
                .filter( o -> o.getNumber() == answer )
                .findFirst();
        Optional<Option> previousCorrect =options.stream()
                .filter( o -> o.getNumber() == newCorrectOpt )
                .findFirst();
        previousCorrect.ifPresent(o->o.setNumber(currentCorrect.get().getNumber()));
        currentCorrect.ifPresent(o -> o.setNumber(newCorrectOpt));
        this.answer=newCorrectOpt;
    }
}
