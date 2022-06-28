package com.example.enums;

public enum Rank {
    ACE("A"),
    TWO("2"),
    THREE("3"),
    FOUR("4"),
    FIVE("5"),
    SIX("6"),
    SEVEN("7"),
    EIGHT("8"),
    NINE("9"),
    TEN("10"),
    JACK("J"),
    QUEEN("Q"),
    KING("K");

    private final String rankChar;

    Rank(String rankChar) {
        this.rankChar = rankChar;
    }

    public String getRankChar() {
        return rankChar;
    }
}
