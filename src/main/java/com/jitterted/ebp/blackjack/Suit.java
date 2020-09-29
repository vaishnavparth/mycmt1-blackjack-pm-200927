package com.jitterted.ebp.blackjack;

public enum Suit {
    HEARTS("♥"),
    SPADES("♠"),
    DIAMONDS("♦"),
    CLUBSS("♣");

    private final String icon;

    Suit(String icon) {
        this.icon = icon;
    }

    public String getIcon() {
        return this.icon;
    }

    @Override
    public String toString() {
        return getIcon();
    }
}
