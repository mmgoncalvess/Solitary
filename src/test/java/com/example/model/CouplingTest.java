package com.example.model;

import com.example.enums.Rank;
import com.example.enums.StackName;
import com.example.enums.Suit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CouplingTest {
    Coupling coupling;

    @BeforeEach
    void setUp() {
        coupling = new Coupling();
    }

    @AfterEach
    void tearDown() {
        coupling = null;
    }

    @Test
    void newGame() {
        coupling.newGame();
        Game expectedGame = coupling.game;
        coupling.newGame();
        Game actualGame = coupling.game;
        Assertions.assertNotEquals(expectedGame, actualGame);
    }

    @Test
    void initialDeal() {
        coupling.newGame();
        InitialStack initialStack = (InitialStack) coupling.game.getStacks().get(3);
        Card card = initialStack.getCard(0);
        assertNull(card);
        coupling.initialDeal();
        card = initialStack.getCard(0);
        assertNotNull(card);
    }

    @Test
    void moveCard() {
        coupling.newGame();
        Card cardOne = new Card(Rank.QUEEN, Suit.HEART);
        Card cardTwo = new Card(Rank.KING, Suit.CLUB);
        InitialStack initialStackOne = (InitialStack) coupling.game.getStacks().get(0);
        InitialStack initialStackTwo = (InitialStack) coupling.game.getStacks().get(1);
        assertFalse(initialStackOne.setCard(cardOne));
        assertTrue(initialStackOne.setCard(cardTwo));
        assertTrue(initialStackOne.setCard(cardOne));
        cardTwo.setCovered(false);
        assertTrue(coupling.moveCard(cardTwo, StackName.INITIAL_TWO, 0));
        Card actualCard = initialStackTwo.getCard(0);
        assertEquals(cardTwo, actualCard);
        actualCard = initialStackTwo.getCard(1);
        assertNull(actualCard);
    }

    @Test
    void next() {
        coupling.newGame();
        coupling.next();
        Movement movement = coupling.getMovement();
        Card cardOne = movement.getCard();
        coupling.next();
        movement = coupling.getMovement();
        Card cardTwo = movement.getCard();
        assertNotEquals(cardOne, cardTwo);
    }

    @Test
    void isSolved() {
        coupling.newGame();
        boolean initialValue = coupling.isSolved();
        assertTrue(initialValue);
        coupling.initialDeal();
        boolean finalValue = coupling.isSolved();
        Assertions.assertFalse(finalValue);
    }

    @Test
    void completeGame() {
        coupling.newGame();
        coupling.initialDeal();
        boolean valueTwo = coupling.completeGame();
        Assertions.assertFalse(valueTwo);
    }

    @Test
    void getMovement() {
        coupling.newGame();
        coupling.initialDeal();
        Movement movement = coupling.getMovement();
        Card card = movement.getCard();
        Assertions.assertNotNull(card);
        StackName stackName = movement.getStackName();
        Assertions.assertEquals(stackName, StackName.INITIAL_ONE);
    }
}