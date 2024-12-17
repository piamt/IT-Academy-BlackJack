package com.pia.itacademy.BlackJack.mysql.repository;

import com.pia.itacademy.BlackJack.mysql.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByName(String name);
    User findByName(String name);
}
