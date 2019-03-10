package com.bhanuchaddha.gtmg.session;

import com.bhanuchaddha.gtmg.session.dto.UserRank;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private long quizId;
    @OneToMany(
            mappedBy = "session",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private Set<User> users;

    @JsonIgnore
    public List<UserRank> getUserRanking(){
        AtomicInteger rank = new AtomicInteger();
        return this.users.stream()
                .sorted(Comparator.comparingInt(User::getScore).reversed())
                .map(u->new UserRank(u.getId(),u.getName(),rank.incrementAndGet(),u.getScore()))
                .collect(Collectors.toList());

    }
}
