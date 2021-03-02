import { Throw, Outcome, Player, GameResult, PlayerStat, GameRecord } from './game';
import { Observable } from 'rxjs';

export abstract class GameGateway {
  abstract playPracticeGame(request: PlayPracticeGameRequest ): Observable<PlayPracticeGameResponse>;
  abstract playGame(request: PlayGameRequest ): Observable<GameResult>;
  abstract getPlayers(): Observable<Player[]>;
  abstract getPlayerStats(): Observable<PlayerStat[]>;
  abstract getPlayerGameRecords( playerId: number ): Observable<GameRecord[]>;
}

export class PlayPracticeGameRequest {
  constructor(
    public player1Throw: Throw,
    public player2Throw: Throw
  ) {}
}

export class PlayPracticeGameResponse {
  constructor(
    public outcome: Outcome
  ) {}
}

export class PlayGameRequest {
  constructor(
    public player1: Player,
    public player2: Player,
    public player1Throw: Throw,
    public player2Throw: Throw
  ) {}


}

