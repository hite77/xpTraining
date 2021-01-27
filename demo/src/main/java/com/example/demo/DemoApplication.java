package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/round/{move1}/{move2}")
	public String round(@PathVariable Moves move1, @PathVariable Moves move2) {
		Game game = new Game();
		Player player1 = new Player();
		Player player2 = new Player();
		player1.play = move1;
		player2.play = move2;
		return game.outcome(player1, player2).toString();
	}
}
