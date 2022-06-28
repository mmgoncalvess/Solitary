package com.example.enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RankTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void getRankChar() {
        String expectedValue = "J";
        String actualValue = Rank.JACK.getRankChar();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void values() {
        Rank[] ranks = Rank.values();
        int expectedValue = 13;
        int actualValue = ranks.length;
        assertEquals(expectedValue, actualValue);
        String charExpectedValue = "5";
        String charActualValue = ranks[4].getRankChar();
        assertEquals(charExpectedValue, charActualValue);
    }

    @Test
    void valueOf() {
        Rank expectedValue = Rank.KING;
        Rank actualValue = Rank.valueOf("KING");
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void name() {
        String expectedValue = "QUEEN";
        String actualValue = Rank.QUEEN.name();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void ordinal() {
        int expectedValue = 7;
        int actualValue = Rank.EIGHT.ordinal();
        assertEquals(expectedValue, actualValue);
        expectedValue = 10;
        actualValue = Rank.JACK.ordinal();
        assertEquals(expectedValue, actualValue);
    }
}