package com.pia.itacademy.BlackJack.mongodb.service;

import com.pia.itacademy.BlackJack.exception.GameNotFoundException;
import com.pia.itacademy.BlackJack.mongodb.model.Game;
import com.pia.itacademy.BlackJack.mongodb.model.Move;

import java.util.List;

public interface GameService {
    List<Game> findAllGames() throws GameNotFoundException;
    Game saveGame(Game game);
    Game addMoveToGame(Integer id, Move move) throws GameNotFoundException;
    Game getGameById(Integer id) throws GameNotFoundException;
    void deleteGame(Integer id) throws GameNotFoundException;
}
