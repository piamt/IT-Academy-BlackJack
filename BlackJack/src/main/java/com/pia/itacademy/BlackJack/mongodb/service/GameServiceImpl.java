package com.pia.itacademy.BlackJack.mongodb.service;

import com.pia.itacademy.BlackJack.exception.GameNotFoundException;
import com.pia.itacademy.BlackJack.mongodb.model.Game;
import com.pia.itacademy.BlackJack.mongodb.model.Move;
import com.pia.itacademy.BlackJack.mongodb.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

public class GameServiceImpl implements GameService {

    @Autowired
    GameRepository gameRepository;

    @Autowired
    private SequenceGeneratorService service;

    @Override
    public List<Game> findAllGames() throws GameNotFoundException {
        List<Game> games = gameRepository.findAll();
        if (games.isEmpty()) throw new GameNotFoundException("No games in ddbb");
        return games;
    }

    @Override
    public Game saveGame(Game game) {
        game.setId(service.getSequenceNumber(Game.SEQUENCE_NAME));
        return gameRepository.save(game);
    }

    @Override
    public Game addMoveToGame(Integer id, Move move) throws GameNotFoundException {
        Optional<Game> gameOp = gameRepository.findById(id);
        if (gameOp.isEmpty()) throw new GameNotFoundException("Game does not exist for id");

        Game gamedb = gameOp.get();


    // TODO: Double check move is not empty or corrupted
        // TODO: Check cómo gestionar el Split

        /*if(Objects.nonNull(game.()) && !"".equalsIgnoreCase(game.getName())) {
            fruitDb.setName(fruit.getName());
        }

        Fruit fruitDb = fruitOp.get();
        if(Objects.nonNull(fruit.getName()) && !"".equalsIgnoreCase(fruit.getName())) {
            fruitDb.setName(fruit.getName());
        }
        fruitDb.setQuantity(fruit.getQuantity());*/

        gamedb.addMove(move);
        return gameRepository.save(gamedb);
    }

    @Override
    public Game getGameById(Integer id) throws GameNotFoundException {
        Optional<Game> game = gameRepository.findById(id);
        if (game.isEmpty()) throw new GameNotFoundException("Game does not exist for id");
        return game.get();
    }

    @Override
    public void deleteGame(Integer id) throws GameNotFoundException {
        if (!gameRepository.existsById(id)) throw new GameNotFoundException("Game does not exist for id");
        gameRepository.deleteById(id);
    }
}
