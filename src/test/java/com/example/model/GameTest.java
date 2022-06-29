package com.example.model;

import com.example.enums.Rank;
import com.example.enums.StackName;
import com.example.enums.Suit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    Game game;

    @BeforeEach
    void setUp() {
        game = new Game();
    }

    @AfterEach
    void tearDown() {
        game = null;
    }

    @Test
    void initialDeal() {
        game.initialDeal();
        boolean result = true;
        for (int stackIndex = 0; stackIndex < 7; stackIndex++) {
            Card card = game.getStacks().get(stackIndex).getCard(stackIndex);
            if (card == null || card.isCovered()) result = false;
        }
        assertTrue(result);
    }

    @Test
    void moveCard() {
        Card cardOne = new Card(Rank.QUEEN, Suit.HEART);
        Card cardTwo = new Card(Rank.KING, Suit.CLUB);
        InitialStack initialStackOne = (InitialStack) game.getStacks().get(0);
        InitialStack initialStackTwo = (InitialStack) game.getStacks().get(1);
        assertFalse(initialStackOne.setCard(cardOne));
        assertTrue(initialStackOne.setCard(cardTwo));
        assertTrue(initialStackOne.setCard(cardOne));
        cardTwo.setCovered(false);
        assertTrue(game.moveCard(cardTwo, StackName.INITIAL_TWO, 0));
        Card actualCard = initialStackTwo.getCard(0);
        assertEquals(cardTwo, actualCard);
        actualCard = initialStackTwo.getCard(1);
        assertNull(actualCard);
    }

    @Test
    void isSolved() {
        boolean initialValue = game.isSolved();
        assertTrue(initialValue);
        game.initialDeal();
        boolean finalValue = game.isSolved();
        Assertions.assertFalse(finalValue);
    }

    @Test
    void completeGame() {
        game.initialDeal();
        boolean valueTwo = game.completeGame();
        Assertions.assertFalse(valueTwo);
    }

    @Test
    void getMovement() {
        Movement movement = game.getMovement();
        assertNull(movement);
        game.initialDeal();
        movement = game.getMovement();
        Card card = movement.getCard();
        Assertions.assertNotNull(card);
        StackName stackName = movement.getStackName();
        Assertions.assertEquals(stackName, StackName.INITIAL_ONE);
    }

    @Test
    void next() {
        game.next();
        Movement movement = game.getMovement();
        Card cardOne = movement.getCard();
        game.next();
        movement = game.getMovement();
        Card cardTwo = movement.getCard();
        assertNotEquals(cardOne, cardTwo);
    }

    @Test
    void getStacks() {
        assertEquals(12, game.getStacks().size());
    }
}