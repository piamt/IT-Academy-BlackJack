package com.pia.itacademy.BlackJack.controller;

import com.pia.itacademy.BlackJack.exception.GameNotFoundException;
import com.pia.itacademy.BlackJack.exception.UserNotFoundException;
import com.pia.itacademy.BlackJack.mongodb.model.Game;
import com.pia.itacademy.BlackJack.mongodb.model.Ranking;
import com.pia.itacademy.BlackJack.mongodb.service.GameService;
import com.pia.itacademy.BlackJack.mysql.model.User;
import com.pia.itacademy.BlackJack.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/ranking")
public class RankingController {

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    // Llista de jugadors ordenada per la seva posició en el rànquing i la seva puntuació.
    @GetMapping
    public ResponseEntity<List<Ranking>> getUserRanking() throws GameNotFoundException, UserNotFoundException {
        List<Game> games = gameService.findAllGames(); // TODO: Create filter to order by points or quantity

        List<Ranking> rankings = new ArrayList<>();
        for (Game game : games) {
            Integer playerId = game.getPlayerId();
            User user = userService.getUserById(playerId);
            Ranking ranking = new Ranking(user.getName(), game.getGameState().getPoints());
            rankings.add(ranking);
        }
        return ResponseEntity.ok(rankings);
    }
}
