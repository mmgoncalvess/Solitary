package com.example.model;

import com.example.enums.StackName;
import com.example.model.Card;

public class Movement {
    private final Card card;
    private final StackName stackName;
    private final int index;

    public Movement(Card card, StackName stack, int index) {
        this.card = card;
        this.stackName = stack;
        this.index = index;
    }

    public Card getCard() {
        return card;
    }

    public StackName getStackName() {
        return stackName;
    }

    public int getIndex() {
        return index;
    }

    @Override
    public String toString() {
        String string = String.format("Card: %7s   Stack: %-30s  Index: %2d", card, stackName, index);
        return string;
    }
}
