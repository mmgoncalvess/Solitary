package com.example.enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SuitTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getSuitChar() {
        String expectedString = "CDHS";
        String actualString = Suit.CLUB.getSuitChar() +
                Suit.DIAMOND.getSuitChar() +
                Suit.HEART.getSuitChar() +
                Suit.SPADE.getSuitChar();
        Assertions.assertEquals(expectedString, actualString);
    }

    @Test
    void values() {
        Suit[] suits = Suit.values();
        boolean boolOne = suits.length == 4;
        boolean boolTwo = Suit.CLUB == suits[0];
        boolean boolThree = Suit.SPADE == suits[3];
        Assertions.assertTrue(boolOne && boolTwo && boolThree);
    }

    @Test
    void valueOf() {
        Suit a = Suit.valueOf("CLUB");
        Suit z = Suit.valueOf("SPADE");
        boolean boolOne = a.equals(Suit.CLUB);
        boolean boolTwo = z.equals(Suit.SPADE);
        Assertions.assertTrue(boolOne && boolTwo);
    }
}