package com.pia.itacademy.BlackJack.mongodb.model;

import com.pia.itacademy.BlackJack.enums.MoveType;
import lombok.Getter;

import java.util.List;

public class GameState {

    private List<MoveType> nextPossibleMoves;
    private double quantity;
    private List<Card> cards;

    @Getter
    private int points;
}
