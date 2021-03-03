package com.prs.rest;

import com.rps.core.*;

public class StubCreateGameResultUseCase implements CreateGameResultUseCase {

    public GameResult stubbedCreateGameResultUseCaseResponse =
            new GameResult(
                    new Player("Jane Doe", 1),
                    new Player("John Doe", 2),
                    Outcome.P1_WINS,
                    Throw.ROCK, Throw.SCISSORS, 42, "stubCreatedValue"
                    );
    public Request executeCalledWith;

    @Override
    public GameResult execute(Request request) {
        executeCalledWith = request;
        return stubbedCreateGameResultUseCaseResponse;
    }
}
