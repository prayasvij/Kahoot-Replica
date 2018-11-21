package com.bhanuchaddha.gtmg.quiz.repository;

import com.bhanuchaddha.gtmg.quiz.model.Question;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by Bhanu Chaddha on 20-11-2018 08:36 PM.
 */
public interface QuestionRepository extends CrudRepository<Question, String> {

    @Override
    List<Question> findAll();
}
