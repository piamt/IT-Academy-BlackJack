package com.pia.itacademy.BlackJack.mongodb.model;

import com.pia.itacademy.BlackJack.enums.MoveType;

import java.util.List;

public class Move {

    private MoveType type;
    private double addedQuantity;
    private List<Card> addedCards;
    private int addedPoints;
}
