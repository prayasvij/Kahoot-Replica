package com.bhanuchaddha.gtmg.movies.integration;

import com.bhanuchaddha.gtmg.movies.model.DiscoverMovieResponse;
import com.bhanuchaddha.gtmg.movies.model.Movie;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * Created by Bhanu Chaddha on 20-11-2018 07:12 PM.
 */
@Service
public class MovieDBIntegration {

    @Autowired
    private RestTemplate template;

    @Value("${movies.discover.url}")
    private String discoverMovieURL;

    public List<Movie> getMovies(){
        ResponseEntity<DiscoverMovieResponse> response = template.getForEntity(discoverMovieURL
        , DiscoverMovieResponse.class);
        return response.getBody().getResults();
    }
}
