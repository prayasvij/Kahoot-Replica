package com.bhanuchaddha.gtmg.quiz.repository;

import com.bhanuchaddha.gtmg.quiz.model.Quiz;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Bhanu Chaddha on 26-11-2018 01:05 AM.
 */
public interface QuizRepository extends CrudRepository<Quiz, Long> {
}
