package com.bhanuchaddha.gtmg.quiz.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by Bhanu Chaddha on 20-11-2018 08:07 PM.
 */
@Entity
@Data
@NoArgsConstructor
public class Option {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private int number;
    private String value;

    public Option(int number, String value) {
        this.number = number;
        this.value = value;
    }
}
