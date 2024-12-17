package com.pia.itacademy.BlackJack.mongodb.model;

import jakarta.persistence.Id;
import lombok.*;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Document(collection = "game")
public class Game {

    @Transient
    public static final String SEQUENCE_NAME = "game_sequence";

    @Id
    private Integer id;
    @Getter
    private Integer playerId;

    private List<Move> moves;

    @Getter
    private GameState gameState;

    // TODO: Check cómo gestionar el Split
    private List<Move> movesSplit;
    private GameState gameStateSplit;

    public Game(Integer playerId) {
        this.playerId = playerId;
    }

    public void addMove(Move move) { // TODO: Check cómo gestionar el Split
        this.moves.add(move);
    }
}
