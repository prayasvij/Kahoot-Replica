package com.bhanuchaddha.gtmg.movies;

import com.bhanuchaddha.gtmg.movies.integration.MovieDBIntegration;
import com.bhanuchaddha.gtmg.movies.model.Movie;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Bhanu Chaddha on 20-11-2018 07:28 PM.
 */
@RestController
@AllArgsConstructor
public class MovieResource {

    private MovieDBIntegration movieDBIntegration;


    @GetMapping("/movies")
    public List<Movie> getMovies(){
        return movieDBIntegration.getMovies();
    }
}
