package com.rps.core;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DefaultCreateGameResultUseCase implements CreateGameResultUseCase {

    private GameResultRepository gameResultRepository;

    public DefaultCreateGameResultUseCase(GameResultRepository gameResultRepository, GameResultIdProvider gameResultIdProvider) {
        this.gameResultRepository = gameResultRepository;
        this.gameResultIdProvider = gameResultIdProvider;
    }

    private GameResultIdProvider gameResultIdProvider;

    @Override
    public GameResult execute(Request request) {
        if (request.player1.getId() != request.player2.getId()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm");
            String dateTimeGameWasPlayed = LocalDateTime.now().format(formatter);
            return gameResultRepository.save(
                    new GameResult(
                            request.player1,
                            request.player2,
                            RPS.play( request.player1Throw, request.player2Throw),
                            request.player1Throw, request.player2Throw, gameResultIdProvider.getId(),
                            dateTimeGameWasPlayed)
            );
        }
        return new GameResult(request.player1, request.player2, Outcome.INVALID, request.player1Throw, request.player2Throw, -1, "");
    }
}
