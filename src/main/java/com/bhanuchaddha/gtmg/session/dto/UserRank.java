package com.bhanuchaddha.gtmg.session.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class UserRank {
    private long userId;
    private String userName;
    private int rank;
    private int score;
}
