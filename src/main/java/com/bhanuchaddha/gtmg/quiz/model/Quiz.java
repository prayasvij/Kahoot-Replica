package com.bhanuchaddha.gtmg.quiz.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Bhanu Chaddha on 26-11-2018 12:19 AM.
 */
@Entity
@Data
@NoArgsConstructor
public class Quiz {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToMany(
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    @OrderBy
    private Set<Question> questions = new HashSet<>(0);

    public void addQuestions(Question question){
        questions.add(question);
    }


}
