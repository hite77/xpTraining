package com.rps.core;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DefaultGetPlayerStatsUseCase implements GetPlayerStatsUseCase {

    private GameResultRepository gameResultRepository;
    private DefaultGetPlayersUseCase defaultGetPlayersUseCase;


    public DefaultGetPlayerStatsUseCase(GameResultRepository gameResultRepository, DefaultGetPlayersUseCase defaultGetPlayersUseCase) {
        this.gameResultRepository = gameResultRepository;
        this.defaultGetPlayersUseCase = defaultGetPlayersUseCase;
    }

    @Override
    public List<PlayerStat> execute() {

        Comparator<PlayerStat> reversePlayerStatComparator = (h1, h2) -> h2.getWinPercentage().compareTo(h1.getWinPercentage());

        List<PlayerStat> playerStats =
                gameResultRepository.findAll().stream()
                .flatMap( gr -> Stream.of(  new GameRecord( gr.getPlayer1().getId(), gr),
                                            new GameRecord( gr.getPlayer2().getId(), gr)) )
                .collect(Collectors.groupingBy(GameRecord::getPlayer))
                .entrySet().stream()
                .map( x -> {
                    int sumWins = x.getValue().stream().mapToInt( gr -> (gr.getResult() == GameRecord.Result.WON) ? 1 : 0).sum();
                    int sumLoses = x.getValue().stream().mapToInt( gr -> (gr.getResult() == GameRecord.Result.LOSS) ? 1 : 0).sum();
                    int sumTies = x.getValue().stream().mapToInt( gr -> (gr.getResult() == GameRecord.Result.TIE) ? 1 : 0).sum();
                    int sumRocks = x.getValue().stream().mapToInt( gr -> (gr.getPlayerThrow() == Throw.ROCK) ? 1 : 0).sum();
                    int sumPapers = x.getValue().stream().mapToInt( gr -> (gr.getPlayerThrow() == Throw.PAPER) ? 1 : 0).sum();
                    int sumScissors = x.getValue().stream().mapToInt( gr -> (gr.getPlayerThrow() == Throw.SCISSORS) ? 1 : 0).sum();
                    int sumLizards = x.getValue().stream().mapToInt( gr -> (gr.getPlayerThrow() == Throw.LIZARD) ? 1 : 0).sum();
                    int sumSpocks = x.getValue().stream().mapToInt( gr -> (gr.getPlayerThrow() == Throw.SPOCK) ? 1 : 0).sum();
                    return new PlayerStat(x.getKey(), sumWins, sumLoses, sumTies, sumRocks, sumPapers, sumScissors, sumLizards, sumSpocks);
                } )
                .sorted( reversePlayerStatComparator )
                .collect( Collectors.toList())
                ;

        List<Player> players = defaultGetPlayersUseCase.execute();
        List<Player> playersWithStats = new ArrayList<>();
        playerStats.stream().forEach(playerStat -> playersWithStats.add(playerStat.getPlayer()));

        players.stream().filter(player -> !playersWithStats.contains(player)).collect(Collectors.toList()).forEach(player -> playerStats.add(new PlayerStat(player)));

        return playerStats;
    }

}
