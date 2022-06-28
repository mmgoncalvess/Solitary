package com.example.model;

import com.example.enums.StackName;

public class Coupling {
    Game game;

    public void newGame() {
        game = null;
        game = new Game();
    }

    public void initialDeal() {
        game.initialDeal();
    }

    public boolean moveCard(Card card, StackName stack, int index) {
        return game.moveCard(card, stack, index);
    }

    public void next() {
        game.next();
    }

    public boolean isSolved() {
        return game.isSolved();
    }

    public boolean completeGame() {
        return game.completeGame();
    }

    public Movement getMovement() {
        return game.getMovement();
    }
}
