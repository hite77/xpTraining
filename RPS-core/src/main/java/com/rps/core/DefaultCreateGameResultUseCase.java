package com.rps.core;

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
            return gameResultRepository.save(
                    new GameResult(
                            request.player1,
                            request.player2,
                            RPS.play( request.player1Throw, request.player2Throw),
                            request.player1Throw, request.player2Throw, gameResultIdProvider.getId() )
            );
        }
        return new GameResult(request.player1, request.player2, Outcome.INVALID, request.player1Throw, request.player2Throw, -1);
    }
}
