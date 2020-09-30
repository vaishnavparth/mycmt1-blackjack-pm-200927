package com.jitterted.ebp.blackjack;

public enum Suit {
    HEARTS("♥", true),
    SPADES("♠", false),
    DIAMONDS("♦", true),
    CLUBSS("♣", false);

    private final String icon;
    private final boolean isRed;

    Suit(String icon, boolean isRed) {
        this.icon = icon;
        this.isRed = isRed;
    }

    public String getIcon() {
        return this.icon;
    }

    public boolean isRed() {
        return this.isRed;
    }
}
