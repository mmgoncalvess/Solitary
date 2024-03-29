package com.example.model;

import com.example.enums.Rank;
import com.example.enums.StackName;
import com.example.enums.Suit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PilesTest {
    Piles piles;
    Movements movements;

    @BeforeEach
    void setUp() {
        movements = new Movements();
        piles = new Piles(movements);
    }

    @AfterEach
    void tearDown() {
        piles = null;
        movements = null;
    }

    @Test
    void initialDeal() {
        piles.initialDeal();
        boolean result = true;
        for (int stackIndex = 0; stackIndex < 7; stackIndex++) {
            Card card = piles.getStacks().get(stackIndex).getCard(stackIndex);
            if (card == null || card.isCovered()) result = false;
        }
        assertTrue(result);
    }

    @Test
    void move() {
        Card cardOne = new Card(Rank.QUEEN, Suit.HEART);
        Card cardTwo = new Card(Rank.KING, Suit.CLUB);
        InitialStack initialStackOne = (InitialStack) piles.getStacks().get(0);
        InitialStack initialStackTwo = (InitialStack) piles.getStacks().get(1);
        assertFalse(initialStackOne.setCard(cardOne));
        assertTrue(initialStackOne.setCard(cardTwo));
        assertTrue(initialStackOne.setCard(cardOne));
        cardTwo.setCovered(false);
        assertTrue(piles.move(cardTwo, StackName.INITIAL_TWO, 0));
        Card actualCard = initialStackTwo.getCard(0);
        assertEquals(cardTwo, actualCard);
        actualCard = initialStackTwo.getCard(1);
        assertNull(actualCard);
    }

    @Test
    void isSolved() {
        boolean initialValue = piles.isSolved();
        assertTrue(initialValue);
        piles.initialDeal();
        boolean finalValue = piles.isSolved();
        Assertions.assertFalse(finalValue);
    }

    @Test
    void completeGame() {
        piles.initialDeal();
        boolean valueTwo = piles.completeGame();
        Assertions.assertFalse(valueTwo);
    }

    @Test
    void checkSuits() {
        FinalStack stackTwo = (FinalStack) piles.getStacks().get(8);
        FinalStack stackThree = (FinalStack) piles.getStacks().get(9);
        FinalStack stackFour = (FinalStack) piles.getStacks().get(10);
        stackTwo.setCard(new Card(Rank.ACE, Suit.SPADE));
        stackThree.setCard(new Card(Rank.ACE, Suit.CLUB));
        stackTwo.setCard(new Card(Rank.TWO,Suit.SPADE));
        stackFour.setCard(new Card(Rank.ACE, Suit.HEART));
        Suit[] suits = piles.checkSuits();
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
        piles.initialDeal();
        piles.next();
        Deck deck = (Deck) piles.getStacks().get(11);
        Card expectedCardOne = deck.getCard(0);
        Rank rank = expectedCardOne.getRank();
        Suit suit = expectedCardOne.getSuit();
        deck.next();
        deck.next();
        Card actualCardOne = piles.findCard(rank, suit);

        Card expectedCardTwo = piles.getStacks().get(2).getCard(piles.getStacks().get(2).getSize()-1);
        Card expectedCardThree = piles.getStacks().get(5).getCard(piles.getStacks().get(5).getSize()-1);
        Card expectedCardFour = piles.getStacks().get(4).getCard(piles.getStacks().get(4).getSize()-1);
        Card actualCardTwo = piles.findCard(expectedCardTwo.getRank(), expectedCardTwo.getSuit());
        Card actualCardThree = piles.findCard(expectedCardThree.getRank(), expectedCardThree.getSuit());
        Card actualCardFour = piles.findCard(expectedCardFour.getRank(), expectedCardFour.getSuit());

        System.out.println("Expected: " + expectedCardOne + "  Actual: " + actualCardOne);
        System.out.println("Expected: " + expectedCardTwo + "  Actual: " + actualCardTwo);
        System.out.println("Expected: " + expectedCardThree + "  Actual: " + actualCardThree);
        System.out.println("Expected: " + expectedCardFour + "  Actual: " + actualCardFour);

        assertEquals(expectedCardOne, actualCardOne);
        assertEquals(expectedCardTwo, actualCardTwo);
        assertEquals(expectedCardThree, actualCardThree);
        assertEquals(expectedCardFour, actualCardFour);
    }

    @Test
    void fillRank() {
        Suit[] suits = piles.checkSuits();
        assertNotEquals(13, piles.getStacks().get(9).getSize());
        for (Rank rank : Rank.values()) {
            piles.fillRank(rank, suits);
        }
        assertEquals(13, piles.getStacks().get(9).getSize());
    }

    @Test
    void next() {
        piles.next();
        Movement movement = movements.getMovement();
        Card cardOne = movement.getCard();
        piles.next();
        movement = movements.getMovement();
        Card cardTwo = movement.getCard();
        assertNotEquals(cardOne, cardTwo);
    }

    @Test
    void getStacks() {
        assertEquals(12, piles.getStacks().size());
    }
}