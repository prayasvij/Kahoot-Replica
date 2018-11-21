package com.bhanuchaddha.gtmg.movies.model;

import lombok.Data;

import java.util.List;

/**
 * Created by Bhanu Chaddha on 18-11-2018 11:40 PM.
 */
@Data
public class DiscoverMovieResponse {
    private Integer page;
    private Integer totalResults;
    private Integer totalPages;
    private List<Movie> results;


    public List<Movie> getResults() {
        return results;
    }
}
