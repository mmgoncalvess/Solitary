package com.example.model;

import com.example.enums.Rank;
import com.example.enums.Suit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CardTest {
    Card card;

    @BeforeEach
    void setUp() {
        card = new Card(Rank.QUEEN, Suit.SPADE);
    }

    @AfterEach
    void tearDown() {
        card = null;
    }

    @Test
    void setCovered() {
        card.setCovered(false);
        assertFalse(card.isCovered());
        card.setCovered(true);
        assertTrue(card.isCovered());
    }

    @Test
    void getRank() {
        Rank expectedValue = Rank.QUEEN;
        Rank actualValue = card.getRank();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getSuit() {
        Suit expectedValue = Suit.SPADE;
        Suit actualValue = card.getSuit();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void isRed() {
        boolean expectedValue = false;
        boolean actualValue = card.isRed();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void isCovered() {
        boolean expectedValue = true;
        boolean actualValue = card.isCovered();
        assertEquals(expectedValue, actualValue);
    }


    @Test
    void testToString() {
        String string = card.toString();
        String actualValue = string.substring(0,2);
        String expectedValue = "QS";
        assertEquals(expectedValue, actualValue);
    }
}