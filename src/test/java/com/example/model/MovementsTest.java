package com.example.model;

import com.example.enums.Rank;
import com.example.enums.StackName;
import com.example.enums.Suit;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MovementsTest {
    Movements movements;

    @BeforeEach
    void setUp() {
        movements = new Movements();
    }

    @AfterEach
    void tearDown() {
        movements = null;
    }

    @Test
    void addMovement() {
        movements.addMovement(new Card(Rank.QUEEN, Suit.HEART), StackName.FINAL_TWO, 2);
        Rank expectedRank = Rank.QUEEN;
        StackName expectedStackName = StackName.FINAL_TWO;
        Movement movement = movements.getMovement();
        assertEquals(expectedRank, movement.getCard().getRank());
        movements.addMovement(new Card(Rank.QUEEN, Suit.HEART), StackName.FINAL_TWO, 2);
        movement = movements.getMovement();
        assertEquals(expectedStackName, movement.getStackName());
    }

    @Test
    void getMovement() {
        Card expectedCard = new Card(Rank.KING, Suit.CLUB);
        movements.addMovement(expectedCard, StackName.INITIAL_THREE, 3);
        Movement movement = movements.getMovement();
        Card actualCard = movement.getCard();
        Assertions.assertEquals(expectedCard, actualCard);
        StackName expectedStackName = StackName.INITIAL_THREE;
        StackName actualStackName = movement.getStackName();
        Assertions.assertEquals(expectedStackName, actualStackName);
    }
}