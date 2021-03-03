package com.rps.persistence;

import com.rps.core.*;
import org.springframework.data.repository.Repository;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

public interface JpaGameResultsRepository extends Repository<JpaGameResultsRepository.GameResultJpaEntity, String> {

    GameResultJpaEntity save(GameResultJpaEntity gameResultJpaEntity );
    GameResultJpaEntity findByGameResultId( Integer gameResultId );
    List<GameResultJpaEntity> findAll();

    @Entity
    @Table(name = "game_results")
    class GameResultJpaEntity implements Serializable {

        public GameResultJpaEntity(){}

        @Id
        public Integer gameResultId;

        @Enumerated(EnumType.STRING)
        public Outcome outcome;
        @Enumerated(EnumType.STRING)
        public Throw player1Throw;
        @Enumerated(EnumType.STRING)
        public Throw player2Throw;

        public String gameTime;

        public int player1Id;
        public int player2Id;

        public GameResultJpaEntity(GameResult gameResult ){
            this.gameResultId = gameResult.getGameResultId();
            this.outcome = gameResult.getOutcome();
            this.player1Id = gameResult.getPlayer1().getId();
            this.player2Id = gameResult.getPlayer2().getId();
            this.player1Throw = gameResult.getPlayer1Throw();
            this.player2Throw = gameResult.getPlayer2Throw();
            this.gameTime = gameResult.getGameTime();
        }

        public GameResult toDomainObject( String player1Name, String player2Name ){
            return new GameResult(
                    new Player( player1Name, player1Id ),
                    new Player( player2Name, player2Id ),
                    outcome,
                    player1Throw, player2Throw, gameResultId, gameTime
            );
        }

        public GameResult toDomainObject(PlayerRepository playerRepository){
            return new GameResult(
                    new Player( playerRepository.findById(player1Id).getName(), player1Id ),
                    new Player( playerRepository.findById(player2Id).getName(), player2Id ),
                    outcome,
                    player1Throw, player2Throw, gameResultId, gameTime

            );
        }

    }

}
