package com.jitterted.ebp.blackjack;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.fusesource.jansi.Ansi.ansi;

public class Hand {
    private final List<Card> cards;

    public Hand() {
        this.cards = new ArrayList<>();
    }

    public void add(Card card) {
        cards.add(card);
    }

    private int getHandValue() {
      return cards
              .stream()
              .mapToInt(Card::rankValue)
              .sum();
    }

    private boolean hasAce() {
      return cards
              .stream()
              .anyMatch(card -> card.rankValue() == 1);
    }

    private int handValue() {
      int handValue = getHandValue();

      // does the hand contain at least 1 Ace?
      boolean hasAce = hasAce();

      // if the total hand value <= 11, then count the Ace as 11 by adding 10
      if (hasAce && handValue <= 11) {
        handValue += 10;
      }
      return handValue;
    }

    boolean isValueEquals(int val) {
        return handValue() == val;
    }

    boolean isBust() {
      return handValue() > 21;
    }

    void displayState(String message) {
        System.out.println();
        System.out.println(message);
        displayHand();
        System.out.println(" (" + handValue() + ")");
    }

    void displayHand() {
      System.out.println(cards.stream()
                             .map(Card::display)
                             .collect(Collectors.joining(
                                 ansi().cursorUp(6).cursorRight(1).toString())));
    }

    String displayFirstCard() {
        return cards.get(0).display();
    }

    boolean isLessThan(Hand otherHand) {
      return handValue() < otherHand.handValue();
    }

    boolean isEquals(Hand otherHand) {
      return otherHand.handValue() == handValue();
    }

    boolean isLessThanOrEquals(int value) {
      return handValue() <= value;
    }
}
