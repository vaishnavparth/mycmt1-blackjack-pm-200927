package com.jitterted.ebp.blackjack;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class HandValueAceTest {

    @Test
    public void handWithOneAceTwoCardsIsValuedAt11() {
        Hand hands = new Hand();
        hands.add(new Card(null, "A"));
        hands.add(new Card(null, "5"));

        assertThat(hands.isValueEquals(11 + 5)).isTrue();
    }

    @Test
    public void handWithOneAceAndOtherCardsEqualTo11IsValuedAt1() {
        Hand hands = new Hand();
        hands.add(new Card(null, "A"));
        hands.add(new Card(null, "8"));
        hands.add(new Card(null, "3"));
        assertThat(hands.isValueEquals(1 + 8 + 3)).isTrue();
    }

    @Test
    public void handWithOneAceAndOtherTen() {
        Hand hands = new Hand();
        hands.add(new Card(null, "A"));
        hands.add(new Card(null, "10"));
        assertThat(hands.isValueEquals(11 + 10)).isTrue();
    }

    @Test
    public void handWithOneAceAndOtherEleven() {
        Hand hands = new Hand();
        hands.add(new Card(null, "A"));
        hands.add(new Card(null, "11"));
        assertThat(hands.isValueEquals(1 + 11)).isTrue();
    }

    @Test
    public void handWithOneAceAndOtherTwelve() {
        Hand hands = new Hand();
        hands.add(new Card(null, "A"));
        hands.add(new Card(null, "12"));
        assertThat(hands.isValueEquals(1 + 12)).isTrue();
    }
}
