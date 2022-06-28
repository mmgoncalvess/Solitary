package com.example.model;

import com.example.enums.Rank;
import com.example.enums.StackName;
import com.example.enums.Suit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementTest {
    Movement movement;

    @BeforeEach
    void setUp() {
        movement = new Movement(new Card(Rank.QUEEN, Suit.HEART), StackName.FINAL_TWO, 2);
    }

    @AfterEach
    void tearDown() {
        movement = null;
    }

    @Test
    void getCard() {
        Card card = movement.getCard();
        assertEquals(card.getRank(), Rank.QUEEN);
    }

    @Test
    void getStackName() {
        StackName actualValue = movement.getStackName();
        assertEquals(StackName.FINAL_TWO, actualValue);
    }

    @Test
    void getIndex() {
        assertEquals(2, movement.getIndex());
    }
}