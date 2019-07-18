package com.rps.core;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DefaultGetPlayerStatsUseCaseTest {

    DefaultGetPlayerStatsUseCase defaultGetPlayerStatsUseCase;
    DefaultCreateGameResultUseCase defaultCreateGameResultUseCase;
    InMemoryGameResultRepository gameResultRepository;
    GameResultIdProvider gameResultIdProvider;

    @Before
    public void setup() {
        gameResultRepository = new InMemoryGameResultRepository();
        gameResultIdProvider = new StubGameResultIdProvider();
        defaultGetPlayerStatsUseCase = new DefaultGetPlayerStatsUseCase(gameResultRepository);
        defaultCreateGameResultUseCase = new DefaultCreateGameResultUseCase(gameResultRepository, gameResultIdProvider);

        Player player1 = new Player("Wonder Woman", 41);
        Player player2 = new Player("Black Panther", 42);
        Player player3 = new Player("Iron Man", 43);
        Player player4 = new Player("Deadpool", 44);
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player1, player2, Throw.ROCK, Throw.SCISSORS));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player2, player1, Throw.ROCK, Throw.PAPER));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player1, player3, Throw.SCISSORS, Throw.PAPER));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player2, player3, Throw.PAPER, Throw.ROCK));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player2, player4, Throw.SCISSORS, Throw.SCISSORS));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player2, player3, Throw.ROCK, Throw.SCISSORS));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player4, player2, Throw.ROCK, Throw.PAPER));
        defaultCreateGameResultUseCase.execute(new CreateGameResultUseCase.Request(player4, player3, Throw.PAPER, Throw.SCISSORS));

        }

    @Test
    public void execute_returnsPlayerStats() {

        List<PlayerStat> stats = defaultGetPlayerStatsUseCase.execute();
        Assert.assertEquals(4, stats.size());
        Assert.assertEquals(41, stats.get(0).getPlayer().getId());

        }
}

