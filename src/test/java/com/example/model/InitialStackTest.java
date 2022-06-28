package com.example.model;

import com.example.enums.Rank;
import com.example.enums.Suit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InitialStackTest {
    InitialStack initialStack;

    @BeforeEach
    void setUp() {
        initialStack = new InitialStack();
    }

    @AfterEach
    void tearDown() {
        initialStack = null;
    }

    @Test
    void getCard() {
        Card cardOne = new Card(Rank.KING, Suit.HEART);
        Card cardTwo = new Card(Rank.TWO, Suit.CLUB);
        Card cardThree = new Card(Rank.JACK, Suit.SPADE);
        initialStack.addCard(0, cardOne);
        initialStack.addCard(1, cardTwo);
        initialStack.addCard(2, cardThree);
        Card actualValue = initialStack.getCard(1);
        assertEquals(cardTwo, actualValue);
        actualValue = initialStack.getCard(-1);
        assertNull(actualValue);
        actualValue = initialStack.getCard(initialStack.getSize());
        assertNull(actualValue);
    }

    @Test
    void removeCard() {
        Card cardOne = new Card(Rank.KING, Suit.HEART);
        Card cardTwo = new Card(Rank.TWO, Suit.CLUB);
        Card cardThree = new Card(Rank.JACK, Suit.SPADE);
        initialStack.addCard(0, cardOne);
        initialStack.addCard(1, cardTwo);
        initialStack.addCard(2, cardThree);
        assertTrue(initialStack.getCard(1).isCovered());
        initialStack.removeCard(cardTwo);
        int expectedValue = 2;
        int actualValue = initialStack.getSize();
        assertEquals(expectedValue, actualValue);
        assertFalse(initialStack.getCard(initialStack.getSize() - 1).isCovered());
    }

    @Test
    void getSize() {
        assertEquals(0, initialStack.getSize());
        Card cardOne = new Card(Rank.KING, Suit.HEART);
        Card cardTwo = new Card(Rank.TWO, Suit.CLUB);
        Card cardThree = new Card(Rank.JACK, Suit.SPADE);
        initialStack.addCard(0, cardOne);
        initialStack.addCard(1, cardTwo);
        initialStack.addCard(2, cardThree);
        int expectedValueOne = 3;
        int expectedValueTwo = 2;
        int actualValueOne = initialStack.getSize();
        initialStack.removeCard(initialStack.getCard(0));
        int actualValueTow = initialStack.getSize();
        assertEquals(expectedValueOne, actualValueOne);
        assertEquals(expectedValueTwo, actualValueTow);
    }

    @Test
    void setCard() {
        assertFalse(initialStack.setCard(null));
        assertEquals(0, initialStack.getSize());
        Card cardOne = new Card(Rank.QUEEN, Suit.SPADE);
        assertFalse(initialStack.setCard(cardOne));
        assertEquals(0, initialStack.getSize());
        cardOne = new Card(Rank.KING, Suit.DIAMOND);
        assertTrue(initialStack.setCard(cardOne));
        assertEquals(1, initialStack.getSize());
        Card cardTwo = new Card(Rank.FIVE, Suit.DIAMOND);
        assertFalse(initialStack.setCard(cardTwo));
        assertEquals(1, initialStack.getSize());
        Card cardThree = new Card(Rank.QUEEN, Suit.CLUB);
        assertTrue(initialStack.setCard(cardThree));
        assertEquals(2, initialStack.getSize());
    }

    @Test
    void addCard() {
        assertEquals(0, initialStack.getSize());
        Card card = new Card(Rank.QUEEN, Suit.HEART);
        initialStack.addCard(0, card);
        assertEquals(1, initialStack.getSize());
    }

    @Test
    void getIndex() {
        Card cardOne = new Card(Rank.KING, Suit.HEART);
        Card cardTwo = new Card(Rank.TWO, Suit.CLUB);
        Card cardThree = new Card(Rank.JACK, Suit.SPADE);
        initialStack.addCard(0, cardOne);
        initialStack.addCard(1, cardTwo);
        initialStack.addCard(2, cardThree);
        assertEquals(1, initialStack.getIndex(cardTwo));
    }

    @Test
    void getArrayList() {
        assertNotNull(initialStack.getArrayList());
    }
}