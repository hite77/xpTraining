package com.rps.core;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class DefaultCreateGameResultUseCaseTest {

    DefaultCreateGameResultUseCase defaultCreateGameResultUseCase;
    GameResultRepository gameResultRepository;
    GameResultIdProvider gameResultIdProvider;

    @Before
    public void setup(){
        gameResultRepository = new InMemoryGameResultRepository();
        gameResultIdProvider = new StubGameResultIdProvider();
        defaultCreateGameResultUseCase = new DefaultCreateGameResultUseCase(gameResultRepository, gameResultIdProvider );
    }

    @Test
    public void execute_persistsTheResult(){
        Player player1 = new Player( "Jane Doe", 1);
        Player player2 = new Player("John Doe", 2);

        CreateGameResultUseCase.Request request = new CreateGameResultUseCase.Request( );
        request.player1 = player1;
        request.player2 = player2;
        request.player1Throw = Throw.ROCK;
        request.player2Throw = Throw.SCISSORS;

        GameResult gameResult = defaultCreateGameResultUseCase.execute( request );

        assertEquals( gameResult.getOutcome(), Outcome.P1_WINS );

        GameResult repoGameResult = gameResultRepository.findById( gameResult.getGameResultId() );
        assertEquals( repoGameResult.getPlayer1(), player1 );
        assertEquals( repoGameResult.getPlayer2(), player2 );
        assertEquals( repoGameResult.getOutcome(), Outcome.P1_WINS );
        assertEquals( repoGameResult.getGameResultId(), gameResult.getGameResultId() );
    }

    @Test
    public void execute_doNotPersistTheResultWhenPlayersAreNotDistinct(){
        Player player1 = new Player( "John Doe", 2);
        Player player2 = new Player("John Doe", 2);

        CreateGameResultUseCase.Request request = new CreateGameResultUseCase.Request( );
        request.player1 = player1;
        request.player2 = player2;
        request.player1Throw = Throw.ROCK;
        request.player2Throw = Throw.SCISSORS;

        GameResult gameResult = defaultCreateGameResultUseCase.execute( request );

        assertEquals( gameResult.getOutcome(), Outcome.INVALID );
    }
}
