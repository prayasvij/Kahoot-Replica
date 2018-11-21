package com.bhanuchaddha.gtmg.movies.model;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 * Created by Bhanu Chaddha on 18-11-2018 11:20 PM.
 */
@Data
public class Movie {
    Integer voteCount;
    Integer id;
    Boolean video;
    String title;
    BigDecimal popularity;
    String posterPath;
    String originalLanguage;
    String originalTitle;
    List<Integer> genreIds;
    String backdropPath;
    Boolean adult;
    String overview;
    LocalDate releaseDate;
}
