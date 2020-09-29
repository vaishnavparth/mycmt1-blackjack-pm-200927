package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class HandValueAceTest {

  @Test
  public void handWithOneAceTwoCardsIsValuedAt11() {
    Game game = new Game();
    var hand = List.of(new Card(null, "A"),
                       new Card(null, "5"));

    assertThat(game.handValueOf(hand))
        .isEqualTo(11 + 5);
  }

  @Test
  public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() {
    Game game = new Game();
    var hand = List.of(new Card(null, "A"),
                       new Card(null, "8"),
                       new Card(null, "3"));

    assertThat(game.handValueOf(hand))
        .isEqualTo(1 + 8 + 3);
  }

}
