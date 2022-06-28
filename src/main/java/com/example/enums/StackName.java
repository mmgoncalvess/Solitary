package com.example.enums;

public enum StackName {
    INITIAL_ONE(60, 302),
    INITIAL_TWO(263, 302),
    INITIAL_THREE(466, 302),
    INITIAL_FOUR(669, 302),
    INITIAL_FIVE(872, 302),
    INITIAL_SIX(1075, 302),
    INITIAL_SEVEN(1278, 302),
    FINAL_ONE(669, 52),
    FINAL_TWO(872, 52),
    FINAL_THREE(1075, 52),
    FINAL_FOUR(1278, 52),
    DECK(60, 52);

    private final int pixelX;
    private final int pixelY;

    StackName(int pixelX, int pixelY) {
        this.pixelX = pixelX;
        this.pixelY = pixelY;
    }

    public int getX() {
        return pixelX;
    }

    public int getY() {
        return pixelY;
    }

    @Override
    public String toString() {
        return this.name() + "  X:" + pixelX +
                "  Y:" + pixelY;
    }
}
