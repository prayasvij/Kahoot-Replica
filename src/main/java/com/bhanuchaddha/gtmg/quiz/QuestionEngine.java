package com.bhanuchaddha.gtmg.quiz;

import com.bhanuchaddha.gtmg.movies.integration.MovieDBIntegration;
import com.bhanuchaddha.gtmg.movies.model.Movie;
import com.bhanuchaddha.gtmg.quiz.model.Question;
import com.bhanuchaddha.gtmg.quiz.model.QuestionType;
import com.bhanuchaddha.gtmg.quiz.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Bhanu Chaddha on 21-11-2018 10:51 PM.
 */
@Slf4j
@Component
public class QuestionEngine {

    @Autowired
    private MovieDBIntegration movieDBIntegration;
    @Autowired
    private QuestionRepository questionRepository;

    @Async
    public void run(){

        // Skip question creation if questions already exist
        if(questionRepository.count() >0) return;

        List<Movie> allMovies = new ArrayList<>();
        for (int i = 1; i <= 10 ; i++) {
            allMovies.addAll(movieDBIntegration.getPopularMovies(i));
            log.info("Total movies recived {}", allMovies.size());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        int count = allMovies.size()-1;
        Random random = new Random();
        List<Question> questions = new ArrayList<>();
        for (int i = 0; i < 100 ; i++) {
            int m1= random.nextInt(count);
            int m2= random.nextInt(count);
            int m3= random.nextInt(count);
            int m4= random.nextInt(count);

            Question question = new Question();
            question.setDescription("Find the Name of the movie from the given description.\n "+allMovies.get(m1).getOverview());
            question.addOption(allMovies.get(m1).getTitle(),true);
            question.addOption(allMovies.get(m2).getTitle(),false);
            question.addOption(allMovies.get(m3).getTitle(),false);
            question.addOption(allMovies.get(m4).getTitle(),false);
            question.setType(QuestionType.MOVIE_NAME_FROM_DESCRIPTION);
            questions.add(question);
        }
        questionRepository.saveAll(questions);
        log.info(questions.size()+"  questions have been created");
    }
}
