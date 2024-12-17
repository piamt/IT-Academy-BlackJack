package com.pia.itacademy.BlackJack.controller;

import com.pia.itacademy.BlackJack.exception.GameNotFoundException;
import com.pia.itacademy.BlackJack.exception.UserAlreadyExistsException;
import com.pia.itacademy.BlackJack.exception.UserNotFoundException;
import com.pia.itacademy.BlackJack.mongodb.model.Game;
import com.pia.itacademy.BlackJack.mongodb.model.Move;
import com.pia.itacademy.BlackJack.mongodb.service.GameService;
import com.pia.itacademy.BlackJack.mysql.model.User;
import com.pia.itacademy.BlackJack.mysql.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/game")
public class GameController {

    @Autowired
    GameService gameService;

    @Autowired
    UserService userService;

    @PostMapping("/new")
    public ResponseEntity<Game> saveGame(@RequestBody String playerName) throws UserAlreadyExistsException { // TODO: Check how to manage, I know this is never going to throw
        User user;
        try {
            user = userService.getUserByName(playerName);
        } catch (UserNotFoundException e) {
            user = userService.saveUser(new User(playerName));
        }

        Game game = new Game(user.getId());
        Game gamedb = gameService.saveGame(game);
        return ResponseEntity.created(ServletUriComponentsBuilder.fromCurrentRequest().path("/new").buildAndExpand(gamedb).toUri()).build();
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Game> getGameById(@PathVariable(value = "id") Integer gameId) throws GameNotFoundException {
        return ResponseEntity.ok(gameService.getGameById(gameId));
    }

    @PostMapping("/{id}/play")
    public ResponseEntity<Game> play(@PathVariable(value = "id") Integer gameId, @RequestBody Move move) throws GameNotFoundException {   // TODO: Check como gestionar el Split
        Game game = gameService.getGameById(gameId);

        return ResponseEntity.ok(gameService.addMoveToGame(gameId, move));
    }

    @DeleteMapping("/{id}/delete")
    public ResponseEntity<String> deleteGame(@PathVariable Integer id) throws GameNotFoundException {
        gameService.deleteGame(id);
        return ResponseEntity.noContent().build();
    }
}
