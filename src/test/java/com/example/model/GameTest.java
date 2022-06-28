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
    void checkSuits() {
        FinalStack stackTwo = (FinalStack) game.getStacks().get(8);
        FinalStack stackThree = (FinalStack) game.getStacks().get(9);
        FinalStack stackFour = (FinalStack) game.getStacks().get(10);
        stackTwo.setCard(new Card(Rank.ACE, Suit.SPADE));
        stackThree.setCard(new Card(Rank.ACE, Suit.CLUB));
        stackTwo.setCard(new Card(Rank.TWO,Suit.SPADE));
        stackFour.setCard(new Card(Rank.ACE, Suit.HEART));
        Suit[] suits = game.checkSuits();
        StringBuilder builder = new StringBuilder();
        for (Suit item : suits) {
            builder.append(item.getSuitChar());
        }
        String actualValue = builder.toString();
        String expectedValue = "DSCH";
        Assertions.assertEquals(actualValue, expectedValue);
    }

    @Test
    void findCard() {
        game.initialDeal();
        game.next();
        Deck deck = (Deck) game.getStacks().get(11);
        Card expectedCardOne = deck.getCard(0);
        Rank rank = expectedCardOne.getRank();
        Suit suit = expectedCardOne.getSuit();
        deck.next();
        deck.next();
        Card actualCardOne = game.findCard(rank, suit);

        Card expectedCardTwo = game.getStacks().get(2).getCard(game.getStacks().get(2).getSize()-1);
        Card expectedCardThree = game.getStacks().get(5).getCard(game.getStacks().get(5).getSize()-1);
        Card expectedCardFour = game.getStacks().get(4).getCard(game.getStacks().get(4).getSize()-1);
        Card actualCardTwo = game.findCard(expectedCardTwo.getRank(), expectedCardTwo.getSuit());
        Card actualCardThree = game.findCard(expectedCardThree.getRank(), expectedCardThree.getSuit());
        Card actualCardFour = game.findCard(expectedCardFour.getRank(), expectedCardFour.getSuit());

        System.out.println("Expected: " + expectedCardOne + "  Actual: " + actualCardOne);
        System.out.println("Expected: " + expectedCardTwo + "  Actual: " + actualCardTwo);
        System.out.println("Expected: " + expectedCardThree + "  Actual: " + actualCardThree);
        System.out.println("Expected: " + expectedCardFour + "  Actual: " + actualCardFour);

        Assertions.assertEquals(expectedCardOne, actualCardOne);
        Assertions.assertEquals(expectedCardTwo, actualCardTwo);
        Assertions.assertEquals(expectedCardThree, actualCardThree);
        Assertions.assertEquals(expectedCardFour, actualCardFour);
    }

    @Test
    void fillRank() {
        Suit[] suits = game.checkSuits();
        assertNotEquals(13, game.getStacks().get(9).getSize());
        for (Rank rank : Rank.values()) {
            game.fillRank(rank, suits);
        }
        assertEquals(13, game.getStacks().get(9).getSize());
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