package com.rps.core;

import org.junit.Test;
import org.junit.Before;

import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class GameResultRepositoryTest {

    GameResultRepository gameResultRepository;
    PlayerRepository playerRepository;

    protected abstract GameResultRepository getGameResultRepository();
    protected abstract PlayerRepository getPlayerRepository();

    @Before
    public void setup() {
        gameResultRepository = getGameResultRepository();
        playerRepository = getPlayerRepository();
    }

    @Test
    public void canGetResults(){
        Player player1 = new Player("Jane Doe", 1 );
        playerRepository.save( player1 );
        Player player2 = new Player("John Doe", 2 );
        playerRepository.save( player2 );
        GameResult gameResult = new GameResult(player1, player2, Outcome.P1_WINS, Throw.ROCK, Throw.PAPER, 1, "GameTime" );
        gameResultRepository.save( gameResult );
        List<GameResult> gameResults = gameResultRepository.findAll();
        assertEquals( 1, gameResults.size() );
        assertEquals( true, gameResult.equals( gameResults.get(0) ) );
        assertEquals("GameTime", gameResult.getGameTime());
    }

    @Test
    public void canGetResultsById(){
        Player player1 = new Player("Jane Smith", 1 );
        playerRepository.save( player1 );
        Player player2 = new Player("John Smith", 2 );
        playerRepository.save( player2 );
        GameResult gameResult = new GameResult(player1, player2, Outcome.P1_WINS, Throw.PAPER, Throw.ROCK, 1, "gameTime1" );
        gameResultRepository.save( gameResult );
        gameResult = new GameResult(player1, player2, Outcome.P2_WINS, Throw.PAPER, Throw.SCISSORS, 2, "gameTime2" );
        gameResultRepository.save( gameResult );
        GameResult gameResultById = gameResultRepository.findById(2);
        assertEquals(gameResultById.getOutcome(), Outcome.P2_WINS );
        assertEquals( true, gameResult.equals( gameResultById ));
        assertEquals("gameTime2", gameResultById.getGameTime());
        gameResultById = gameResultRepository.findById(1);
        assertEquals(gameResultById.getOutcome(), Outcome.P1_WINS );
        assertEquals("gameTime1", gameResultById.getGameTime());
    }

    @Test
    public void canGetThePersistedGameResultFromSave() {
        Player player1 = new Player("Jane Smith", 1 );
        Player player2 = new Player("John Smith", 2 );
        GameResult gameResult = new GameResult(player1, player2, Outcome.P1_WINS, Throw.PAPER, Throw.ROCK, 1, "gameTime1" );
        gameResult = new GameResult(player1, player2, Outcome.P2_WINS, Throw.PAPER, Throw.SCISSORS, 2, "gameTime2" );
        GameResult savedGameResult = gameResultRepository.save(gameResult);
        assertEquals(savedGameResult, gameResult);
    }
}
