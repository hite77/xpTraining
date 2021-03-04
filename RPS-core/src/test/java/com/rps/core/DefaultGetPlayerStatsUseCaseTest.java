package com.rps.core;

import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class DefaultGetPlayerStatsUseCaseTest {

    DefaultGetPlayerStatsUseCase defaultGetPlayerStatsUseCase;
    DefaultCreateGameResultUseCase defaultCreateGameResultUseCase;
    InMemoryGameResultRepository gameResultRepository;
    GameResultIdProvider gameResultIdProvider;
    DefaultGetPlayersUseCase defaultGetPlayersUseCase;
    InMemoryPlayerRepository playerRepository;

    int player1Id = 41;
    int player2Id = 42;
    int player3Id = 43;
    int player4Id = 44;
    int player5Id = 45;
    int player6Id = 46;

    List<PlayerStat> stats;

    PlayerStat player1Stat;
    PlayerStat player2stat;
    PlayerStat player3Stat;
    PlayerStat player4Stat;
    PlayerStat player5Stat;
    PlayerStat player6Stat;

    @Before
    public void setup() {
        playerRepository = new InMemoryPlayerRepository();
        defaultGetPlayersUseCase = new DefaultGetPlayersUseCase(playerRepository);

        Player player1 = new Player("Wonder Woman", player1Id);
        Player player2 = new Player("Black Panther", player2Id);
        Player player3 = new Player("Iron Man", player3Id);
        Player player4 = new Player("Deadpool", player4Id);
        Player player5 = new Player("Captain Nobody", player5Id);
        Player player6 = new Player("Lieutenant Not-There", player6Id);

        playerRepository.save(player1);
        playerRepository.save(player2);
        playerRepository.save(player3);
        playerRepository.save(player4);
        playerRepository.save(player5);
        playerRepository.save(player6);

        gameResultRepository = new InMemoryGameResultRepository();
        gameResultIdProvider = new StubGameResultIdProvider();
        defaultGetPlayerStatsUseCase = new DefaultGetPlayerStatsUseCase(gameResultRepository, defaultGetPlayersUseCase);
        defaultCreateGameResultUseCase = new DefaultCreateGameResultUseCase(gameResultRepository, gameResultIdProvider);


        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player1, player2, Throw.ROCK, Throw.SCISSORS));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player2, player1, Throw.SCISSORS, Throw.ROCK));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player1, player3, Throw.ROCK, Throw.SCISSORS));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player2, player3, Throw.ROCK, Throw.PAPER));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player2, player4, Throw.SCISSORS, Throw.SCISSORS));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player2, player3, Throw.ROCK, Throw.SCISSORS));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player4, player2, Throw.ROCK, Throw.PAPER));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player4, player3, Throw.PAPER, Throw.SCISSORS));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player4, player3, Throw.LIZARD, Throw.SPOCK));

        stats = defaultGetPlayerStatsUseCase.execute();
        player1Stat = stats.stream().filter(stat -> stat.getPlayer().getId() == player1Id).collect(Collectors.toList()).get(0);
        player2stat = stats.stream().filter(stat -> stat.getPlayer().getId() == player2Id).collect(Collectors.toList()).get(0);
        player3Stat = stats.stream().filter(stat -> stat.getPlayer().getId() == player3Id).collect(Collectors.toList()).get(0);
        player4Stat = stats.stream().filter(stat -> stat.getPlayer().getId() == player4Id).collect(Collectors.toList()).get(0);
        player5Stat = stats.stream().filter(stat -> stat.getPlayer().getId() == player5Id).collect(Collectors.toList()).get(0);
        player6Stat = stats.stream().filter(stat -> stat.getPlayer().getId() == player6Id).collect(Collectors.toList()).get(0);
    }

    @Test
    public void AllSixPlayersAreInResults() {
        assertEquals(6, stats.size());
        assertNotNull(player1Stat);
        assertNotNull(player2stat);
        assertNotNull(player3Stat);
        assertNotNull(player4Stat);
        assertNotNull(player5Stat);
        assertNotNull(player6Stat);
    }

    @Test
    public void winPercentIsCalculatedCorrectly() {
        assertEquals(100.0, player1Stat.getWinPercentage(), 0.001);
        assertEquals(41.666, player2stat.getWinPercentage(), 0.001);
        assertEquals(40.0, player3Stat.getWinPercentage(), 0.001);
        assertEquals(37.5, player4Stat.getWinPercentage(), 0.001);
        assertEquals(0.0, player5Stat.getWinPercentage(), 0.001);
    }

    @Test
    public void getRockPercentIsCalculatedCorrectly() {
        assertEquals(100.0, player1Stat.getRockPercent(), 0.001);
        assertEquals(33.333, player2stat.getRockPercent(), 0.001);
        assertEquals(0.0, player3Stat.getRockPercent(), 0.001);
        assertEquals(25.0, player4Stat.getRockPercent(), 0.001);
        assertEquals(0.0, player5Stat.getRockPercent(), 0.001);
    }

    @Test
    public void getPaperPercentIsCalculatedCorrectly() {
        assertEquals(0.0, player1Stat.getPaperPercent(), 0.001);
        assertEquals(16.666, player2stat.getPaperPercent(), 0.001);
        assertEquals(20.0, player3Stat.getPaperPercent(), 0.001);
        assertEquals(25.0, player4Stat.getPaperPercent(), 0.001);
        assertEquals(0.0, player5Stat.getPaperPercent(), 0.001);
    }

    @Test
    public void getScissorsPercentIsCalculatedCorrectly() {
        assertEquals(0.0, player1Stat.getScissorsPercent(), 0.001);
        assertEquals(50.0, player2stat.getScissorsPercent(), 0.001);
        assertEquals(60.0, player3Stat.getScissorsPercent(), 0.001);
        assertEquals(25.0, player4Stat.getScissorsPercent(), 0.001);
        assertEquals(0.0, player5Stat.getScissorsPercent(), 0.001);
    }

    @Test
    public void getLizardPercentIsCalculatedCorrectly() {
        assertEquals(0.0, player1Stat.getLizardPercent(), 0.001);
        assertEquals(0.0, player2stat.getLizardPercent(), 0.001);
        assertEquals(0.0, player3Stat.getLizardPercent(), 0.001);
        assertEquals(25.0, player4Stat.getLizardPercent(), 0.001);
        assertEquals(0.0, player5Stat.getLizardPercent(), 0.001);
    }

    @Test
    public void getSpockPercentIsCalculatedCorrectly() {
        assertEquals(0.0, player1Stat.getSpockPercent(), 0.001);
        assertEquals(0.0, player2stat.getSpockPercent(), 0.001);
        assertEquals(20.0, player3Stat.getSpockPercent(), 0.001);
        assertEquals(0.0, player4Stat.getSpockPercent(), 0.001);
        assertEquals(0.0, player5Stat.getSpockPercent(), 0.001);
    }
}

