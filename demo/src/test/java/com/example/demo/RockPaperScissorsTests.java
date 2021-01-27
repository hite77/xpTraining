package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
class RockPaperScissorsTests {
    Game game;

	@Test
	void WhenPlayer1ShowsRockAndPlayer2ShowsRockOutcomeIsTie() {
        game = new Game();

	    Player player1 = new Player();
	    Player player2 = new Player();

	    player1.play = Moves.Rock;
	    player2.play = Moves.Rock;

	    Outcome outcome = game.outcome(player1, player2);

	    assertEquals(Outcome.Tie, outcome);
	}

	@Test
	void WhenPlayer1ShowsPaperAndPlayer2ShowsRockOutcomeIsPlayer1Wins() {
		game = new Game();

		Player player1 = new Player();
		Player player2 = new Player();

		player1.play = Moves.Paper;
		player2.play = Moves.Rock;

		Outcome outcome = game.outcome(player1, player2);

		assertEquals(Outcome.Player1Wins, outcome);
	}

@Test
void WhenPlayer1ShowsScissorsAndPlayer2ShowsPaperOutcomeIsPlayer1Wins() {
	game = new Game();

	Player player1 = new Player();
	Player player2 = new Player();

	player1.play = Moves.Scissors;
	player2.play = Moves.Paper;

	Outcome outcome = game.outcome(player1, player2);

	assertEquals(Outcome.Player1Wins, outcome);
}

	@Test
	void WhenPlayer1ShowsRockAndPlayer2ShowsPaperOutcomeIsPlayer2Wins() {
		game = new Game();

		Player player1 = new Player();
		Player player2 = new Player();

		player1.play = Moves.Rock;
		player2.play = Moves.Paper;

		Outcome outcome = game.outcome(player1, player2);

		assertEquals(Outcome.Player2Wins, outcome);
	}
}
