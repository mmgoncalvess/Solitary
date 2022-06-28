package com.example.enums;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StackNameTest {

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void name() {
        String expectedValue = "DECK";
        String actualValue = StackName.DECK.name();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void ordinal() {
        int expectedValue = 11;
        int actualValue = StackName.DECK.ordinal();
        assertEquals(expectedValue, actualValue);
        expectedValue = 7;
        actualValue = StackName.FINAL_ONE.ordinal();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void testToString() {
        String expectedValue = "DECK  X:60  Y:52";
        String actualValue = StackName.DECK.toString();
        assertEquals(expectedValue, actualValue);
        expectedValue = "FINAL_TWO  X:872  Y:52";
        actualValue = StackName.FINAL_TWO.toString();
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void getX() {
        double expectedValue = 60;
        double actualValue = StackName.DECK.getX();
        assertEquals(expectedValue, actualValue);
        expectedValue = 872;
        actualValue = StackName.FINAL_TWO.getX();
        assertEquals(expectedValue,actualValue);
    }

    @Test
    void getY() {
        double expectedValue = 52;
        double actualValue = StackName.DECK.getY();
        assertEquals(expectedValue, actualValue);
        actualValue = StackName.FINAL_TWO.getY();
        assertEquals(expectedValue,actualValue);
    }

    @Test
    void values() {
        StackName[] stackNames = StackName.values();
        int expectedValue = 12;
        int actualValue = stackNames.length;
        assertEquals(expectedValue, actualValue);
    }

    @Test
    void valueOf() {
        StackName expectedValue = StackName.FINAL_THREE;
        StackName actualValue = StackName.valueOf("FINAL_THREE");
        assertEquals(expectedValue, actualValue);
    }
}