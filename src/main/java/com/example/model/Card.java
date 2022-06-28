package com.example.model;

import com.example.enums.Rank;
import com.example.enums.Suit;

public class Card {
    private final Rank rank;
    private final Suit suit;
    private final boolean red;
    private boolean covered = true;

    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;
        this.red = suit == Suit.HEART || suit == Suit.DIAMOND;
    }

    public void setCovered(boolean covered) {
        this.covered = covered;
    }

    public Rank getRank() {
        return rank;
    }

    public Suit getSuit() {
        return suit;
    }

    public boolean isRed() {
        return red;
    }

    public boolean isCovered() {
        return covered;
    }

    @Override
    public String toString() {
        return rank.getRankChar() + suit.getSuitChar() + (isRed()? "-RED" : "-BLK");
    }
}
