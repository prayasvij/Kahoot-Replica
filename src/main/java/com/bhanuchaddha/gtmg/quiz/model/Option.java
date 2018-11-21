package com.bhanuchaddha.gtmg.quiz.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Bhanu Chaddha on 20-11-2018 08:07 PM.
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Option {
    @Id
    private int id;
    private String value;

}
