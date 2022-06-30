package com.example.controller;

import com.example.model.Game;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    Controller controller;

    @BeforeEach
    void setUp() {
        controller = new Controller();
    }

    @AfterEach
    void tearDown() {
        controller = null;
    }

    @Test
    void newGame() {
    }

    @Test
    void initialDeal() {
    }

    @Test
    void moveCard() {
    }

    @Test
    void next() {
    }

    @Test
    void isSolved() {
    }

    @Test
    void completeGame() {
    }

    @Test
    void getMovement() {
    }
}