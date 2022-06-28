package com.example.enums;

public enum Suit {
    CLUB("C"),
    DIAMOND("D"),
    HEART("H"),
    SPADE("S");

    private final String suitChar;

    Suit(String suitChar) {
        this.suitChar = suitChar;
    }

    public String getSuitChar() {
        return suitChar;
    }
}
