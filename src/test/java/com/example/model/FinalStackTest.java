package com.example.model;

import com.example.enums.Rank;
import com.example.enums.Suit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FinalStackTest {
    FinalStack finalStack;

    @BeforeEach
    void setUp() {
        finalStack = new FinalStack();
    }

    @AfterEach
    void tearDown() {
        finalStack = null;
    }

    @Test
    void getCard() {
        Card cardOne = new Card(Rank.KING, Suit.HEART);
        Card cardTwo = new Card(Rank.TWO, Suit.CLUB);
        Card cardThree = new Card(Rank.JACK, Suit.SPADE);
        finalStack.addCard(0, cardOne);
        finalStack.addCard(1, cardTwo);
        finalStack.addCard(2, cardThree);
        Card actualValue = finalStack.getCard(1);
        assertEquals(cardTwo, actualValue);
        actualValue = finalStack.getCard(-1);
        assertNull(actualValue);
        actualValue = finalStack.getCard(finalStack.getSize());
        assertNull(actualValue);
    }

    @Test
    void removeCard() {
        Card cardOne = new Card(Rank.KING, Suit.HEART);
        Card cardTwo = new Card(Rank.TWO, Suit.CLUB);
        Card cardThree = new Card(Rank.JACK, Suit.SPADE);
        finalStack.addCard(0, cardOne);
        finalStack.addCard(1, cardTwo);
        finalStack.addCard(2, cardThree);
        assertTrue(finalStack.getCard(1).isCovered());
        finalStack.removeCard(cardTwo);
        int expectedValue = 2;
        int actualValue = finalStack.getSize();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getSize() {
        assertEquals(0, finalStack.getSize());
        Card cardOne = new Card(Rank.KING, Suit.HEART);
        Card cardTwo = new Card(Rank.TWO, Suit.CLUB);
        Card cardThree = new Card(Rank.JACK, Suit.SPADE);
        finalStack.addCard(0, cardOne);
        finalStack.addCard(1, cardTwo);
        finalStack.addCard(2, cardThree);
        int expectedValueOne = 3;
        int expectedValueTwo = 2;
        int actualValueOne = finalStack.getSize();
        finalStack.removeCard(finalStack.getCard(0));
        int actualValueTow = finalStack.getSize();
        assertEquals(expectedValueOne, actualValueOne);
        assertEquals(expectedValueTwo, actualValueTow);
    }

    @Test
    void setCard() {
        assertFalse(finalStack.setCard(null));
        assertEquals(0, finalStack.getSize());
        Card cardOne = new Card(Rank.QUEEN, Suit.SPADE);
        assertFalse(finalStack.setCard(cardOne));
        assertEquals(0, finalStack.getSize());
        cardOne = new Card(Rank.ACE, Suit.DIAMOND);
        assertTrue(finalStack.setCard(cardOne));
        assertEquals(1, finalStack.getSize());
        Card cardTwo = new Card(Rank.TWO, Suit.DIAMOND);
        assertTrue(finalStack.setCard(cardTwo));
        assertEquals(2, finalStack.getSize());
        Card cardThree = new Card(Rank.QUEEN, Suit.CLUB);
        assertFalse(finalStack.setCard(cardThree));
        assertEquals(2, finalStack.getSize());
    }

    @Test
    void addCard() {
        assertEquals(0, finalStack.getSize());
        Card card = new Card(Rank.QUEEN, Suit.HEART);
        finalStack.addCard(0, card);
        assertEquals(1, finalStack.getSize());
    }

    @Test
    void getIndex() {
        Card cardOne = new Card(Rank.KING, Suit.HEART);
        Card cardTwo = new Card(Rank.TWO, Suit.CLUB);
        Card cardThree = new Card(Rank.JACK, Suit.SPADE);
        finalStack.addCard(0, cardOne);
        finalStack.addCard(1, cardTwo);
        finalStack.addCard(2, cardThree);
        assertEquals(1, finalStack.getIndex(cardTwo));
    }

    @Test
    void getArrayList() {
        assertNotNull(finalStack.getArrayList());
    }
}