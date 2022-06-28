package com.example.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class DeckTest {
    Deck deck;

    @BeforeEach
    void setUp() {
        deck = new Deck();
    }

    @AfterEach
    void tearDown() {
        deck = null;
    }

    @Test
    void getCard() {
        Card card = deck.getCard(5);
        assertNotEquals(card, null);
        for (int i = 0; i < 52; i++) {
            deck.removeCard(deck.getCard(0));
        }
        Card cardTwo = deck.getCard(5);
        assertNull(cardTwo);
    }

    @Test
    void removeCard() {
        Card cardOne = deck.getCard(deck.getSize() - 1);
        deck.removeCard(deck.getCard(deck.getSize() - 1));
        Card cardTwo = deck.getCard(deck.getSize() - 1);
        assertNotEquals(cardOne, cardTwo);
        boolean check = false;
        for (int i = 0; i < 60; i++) {
            Card card = deck.getCard(0);
            if (card == cardOne) {
                check = true;
                break;
            }
            deck.next();
        }
        assertFalse(check);
    }

    @Test
    void getSize() {
        int expectedValueOne = 52;
        int expectedValueTwo = 51;
        int actualValueOne = deck.getSize();
        deck.removeCard(deck.getCard(7));
        int actualValueTow = deck.getSize();
        assertEquals(expectedValueOne, actualValueOne);
        assertEquals(expectedValueTwo, actualValueTow);
    }

    @Test
    void setCard() {
        Card card = deck.getCard(5);
        deck.removeCard(card);
        boolean bool = deck.setCard(card);
        assertFalse(bool);
    }

    @Test
    void addCard() {
        Card card = deck.getCard(5);
        deck.removeCard(card);
        Card cardTwo = deck.getCard(5);
        assertNotEquals(card, cardTwo);
        deck.addCard(5, card);
        cardTwo = deck.getCard(5);
        assertEquals(card, cardTwo);
    }

    @Test
    void getIndex() {
        int expectedIndex = 17;
        Card card = deck.getCard(expectedIndex);
        int actualIndex = deck.getIndex(card);
        assertEquals(expectedIndex, actualIndex);
    }

    @Test
    void getArrayList() {
        int expectedValue = 52;
        ArrayList<Card> arrayList = deck.getArrayList();
        int actualValue = arrayList.size();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void next() {
        Card cardOne = deck.getCard(5);
        deck.next();
        Card cardTwo = deck.getCard(5);
        assertNotEquals(cardOne, cardTwo);
        cardOne = deck.getCard(5);
        cardTwo = deck.getCard(5);
        assertEquals(cardOne, cardTwo);
        cardOne = deck.getCard(deck.getSize() - 1);
        deck.next();
        cardTwo = deck.getCard(0);
        assertEquals(cardOne, cardTwo);
        for (int i = 0; i < 60; i++) {
            deck.next();
        }
        Card cardThree = deck.getCard(5);
        assertNotEquals(cardThree, null);
    }
}