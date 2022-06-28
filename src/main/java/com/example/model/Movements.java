package com.example.model;

import com.example.enums.StackName;

import java.util.LinkedList;
import java.util.Queue;

public class Movements {
    private final Queue<Movement> queue = new LinkedList<>();

    public void addMovement(Card card, StackName stackName, int index) {
        Movement movement = new Movement(card, stackName, index);
        queue.add(movement);
    }

    public Movement getMovement() {
        return queue.poll();
    }
}
