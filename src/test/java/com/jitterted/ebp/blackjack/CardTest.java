package com.jitterted.ebp.blackjack;

import com.jitterted.ebp.blackjack.Card;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.fusesource.jansi.Ansi.ansi;

class CardTest {

  @Test
  public void withNumberCardHasNumericValueOfTheNumber() {
    Card card = new Card(null, "7");

    assertThat(card.rankValue())
        .isEqualTo(7);
  }

  @Test
  public void withValueOfQueenHasNumericValueOf10() {
    Card card = new Card(null, "Q");

    assertThat(card.rankValue())
        .isEqualTo(10);
  }

  @Test
  public void withAceHasNumericValueOf1() {
    Card card = new Card(null, "A");

    assertThat(card.rankValue())
        .isEqualTo(1);
  }

  @Test
  public void suitOfHeartsOrDiamondsIsDisplayedInRed() {
    // given a card with Hearts or Diamonds
    Card heartsCard = new Card(Suit.HEARTS, "10");
    Card diamondsCard = new Card(Suit.DIAMONDS, "8");

    // when we ask for its display representation
    String ansiRedString = ansi().fgRed().toString();

    // then we expect a red color ansi sequence
    assertThat(heartsCard.display())
        .contains(ansiRedString);
    assertThat(diamondsCard.display())
        .contains(ansiRedString);
  }

}