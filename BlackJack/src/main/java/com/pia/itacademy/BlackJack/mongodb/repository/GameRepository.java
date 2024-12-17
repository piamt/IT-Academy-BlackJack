package com.pia.itacademy.BlackJack.mongodb.repository;

import com.pia.itacademy.BlackJack.mongodb.model.Game;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GameRepository extends MongoRepository<Game, Integer> {
}
