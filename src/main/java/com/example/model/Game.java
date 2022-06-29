package com.example.model;

import com.example.enums.StackName;

import java.util.ArrayList;

public class Game {
    private Piles piles;
    private Movements movements;

    public Game() {
        initialize();
    }

    private void initialize() {
        movements = new Movements();
        piles = new Piles(movements);
    }

    public void initialDeal() {
        piles.initialDeal();
    }

    public boolean moveCard(Card card, StackName stackTarget, int index) {
        return piles.move(card, stackTarget, index);
    }

    public boolean isSolved() {
        return piles.isSolved();
    }

    public boolean completeGame() {
        return piles.completeGame();
    }

    public Movement getMovement() {
        return movements.getMovement();
    }

    public void next() {
        piles.next();
    }

    public ArrayList<Stack> getStacks() {
        return piles.getStacks();
    }
}
