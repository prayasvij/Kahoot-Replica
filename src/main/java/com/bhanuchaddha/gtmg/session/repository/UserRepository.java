package com.bhanuchaddha.gtmg.session.repository;

import com.bhanuchaddha.gtmg.session.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
