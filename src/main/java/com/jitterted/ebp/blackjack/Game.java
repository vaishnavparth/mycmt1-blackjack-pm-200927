package com.jitterted.ebp.blackjack;

import org.fusesource.jansi.Ansi;

import java.util.Scanner;

import static org.fusesource.jansi.Ansi.ansi;

public class Game {

  private final Deck deck;

  final Hand dealerHand = new Hand();
  final Hand playerHand = new Hand();

  public static void main(String[] args) {
    Game game = new Game();

    System.out.println(ansi()
                           .bgBright(Ansi.Color.WHITE)
                           .eraseScreen()
                           .cursor(1, 1)
                           .fgGreen().a("Welcome to")
                           .fgRed().a(" Jitterted's")
                           .fgBlack().a(" BlackJack"));


    game.initialDeal();
    game.play();

    System.out.println(ansi().reset());
  }

  public Game() {
    deck = new Deck();
  }

  public void initialDeal() {

    // deal first round of cards, players first
    dealCards();

    // deal next round of cards
    dealCards();
  }

  private void dealCards() {
    playerHand.add(deck.draw());
    dealerHand.add(deck.draw());
  }

  public void play() {
    // get Player's decision: hit until they stand, then they're done (or they go bust)
    boolean playerBusted = getPlayerMove();

    // Dealer makes its choice automatically based on a simple heuristic (<=16, hit, 17>stand)
    dealerMove(playerBusted);
    displayFinalGameState();
    handleResults(playerBusted);
  }

  private void handleResults(boolean playerBusted) {
    if (playerBusted) {
      System.out.println("You Busted, so you lose.  💸");
    } else if (dealerHand.isBust()) {
      System.out.println("Dealer went BUST, Player wins! Yay for you!! 💵");
    } else if (dealerHand.isLessThan(playerHand)) {
      System.out.println("You beat the Dealer! 💵");
    } else if (dealerHand.isEquals(playerHand)) {
      System.out.println("Push: The house wins, you Lose. 💸");
    } else {
      System.out.println("You lost to the Dealer. 💸");
    }
  }

  private void dealerMove(boolean playerBusted) {
    if (!playerBusted) {
      while (dealerHand.isLessThanOrEquals(16)) {
        dealerHand.add(deck.draw());
      }
    }
  }

  private boolean getPlayerMove() {
    boolean playerBusted = false;
    while (!playerBusted) {
      displayGameState();
      String playerChoice = inputFromPlayer().toLowerCase();
      if (playerChoice.startsWith("s")) {
        break;
      }
      if (playerChoice.startsWith("h")) {
        playerHand.add(deck.draw());
        if (playerHand.isBust()) {
          playerBusted = true;
        }
      } else {
        System.out.println("You need to [H]it or [S]tand");
      }
    }
    return playerBusted;
  }

  private String inputFromPlayer() {
    System.out.println("[H]it or [S]tand?");
    Scanner scanner = new Scanner(System.in);
    return scanner.nextLine();
  }

  private void displayGameState() {
    System.out.print(ansi().eraseScreen().cursor(1, 1));
    System.out.println("Dealer has: ");
    System.out.println(dealerHand.displayFirstCard()); // first card is Face Up

    // second card is the hole card, which is hidden
    displayBackOfCard();

    playerHand.displayState("Player has: ");
  }

  private void displayBackOfCard() {
    System.out.print(
        ansi()
            .cursorUp(7)
            .cursorRight(12)
            .a("┌─────────┐").cursorDown(1).cursorLeft(11)
            .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
            .a("│░ J I T ░│").cursorDown(1).cursorLeft(11)
            .a("│░ T E R ░│").cursorDown(1).cursorLeft(11)
            .a("│░ T E D ░│").cursorDown(1).cursorLeft(11)
            .a("│░░░░░░░░░│").cursorDown(1).cursorLeft(11)
            .a("└─────────┘"));
  }

  private void displayFinalGameState() {
    System.out.print(ansi().eraseScreen().cursor(1, 1));
    dealerHand.displayState("Dealer has: ");
    playerHand.displayState("Player has: ");
  }

}