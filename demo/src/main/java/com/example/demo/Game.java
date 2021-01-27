package com.example.demo;

public class Game {
    public Outcome outcome(Player player1, Player player2) {
        if (player1.play == player2.play) {
            return Outcome.Tie;
        }
        if (player1.play == Moves.Rock && player2.play == Moves.Paper) {
            return Outcome.Player2Wins;
        }
        return Outcome.Player1Wins;
    }
}
