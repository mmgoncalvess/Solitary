package com.example.controller;

import com.example.enums.StackName;
import com.example.model.Card;
import com.example.model.Coupling;
import com.example.model.Movement;

public class Controller {
    Coupling coupling = new Coupling();

    public void newGame() {
        coupling.newGame();
    }

    public void initialDeal() {
        coupling.initialDeal();
    }

    public boolean moveCard(Card card, StackName stack, int index) {
        return coupling.moveCard(card, stack, index);
    }

    public void next() {
        coupling.next();
    }

    public boolean isSolved() {
        return coupling.isSolved();
    }

    public boolean completeGame() {
        return coupling.completeGame();
    }

    public Movement getMovement() {
        return coupling.getMovement();
    }
}
