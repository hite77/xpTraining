package com.example.demo;

public class Game {
    public Outcome outcome(Player player1, Player player2) {
        if (player1.play == player2.play) {
            return Outcome.Tie;
        }
        return Outcome.Player1Wins;
    }
}
