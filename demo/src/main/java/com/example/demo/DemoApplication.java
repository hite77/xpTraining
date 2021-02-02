package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication

@RestController
@RequestMapping("api")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@CrossOrigin(origins = "http://localhost:4200")
	@GetMapping("/round/{move1}/{move2}")
	public List<String> round(@PathVariable Moves move1, @PathVariable Moves move2) {
		Game game = new Game();
		Player player1 = new Player();
		Player player2 = new Player();

		player1.play = move1;
		player2.play = move2;

		List list = new ArrayList();
		list.add(game.outcome(player1, player2).toString());
		return list;
	}
}
