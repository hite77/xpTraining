import { GameResult, Player, PlayerStat, Throw, Outcome, Result, GameRecord } from './game';
import { Observable, of } from 'rxjs';
import { GameGateway, PlayPracticeGameRequest, PlayPracticeGameResponse, PlayGameRequest } from './game.gateway';

export class StubGameGateway implements GameGateway {

  playPracticeGameCalledWith: PlayPracticeGameRequest;
  playGameCalledWith: PlayGameRequest;
  stubOutcome: Outcome;
  playerStats: PlayerStat[] = [];
  gameStats: GameRecord[] = [];

  constructor() {
    this.playerStats.push(new PlayerStat(new Player('Player 1', 1 ), 10, 0, 0, 10, 100, 10, 0, 0, 100, 0, 0, 0, 0, 0, 0));
    this.playerStats.push(new PlayerStat(new Player('Player 2', 2 ), 6, 2, 2, 10, 70, 5, 2, 3, 50, 20, 30, 0, 0, 0, 0));
    this.playerStats.push(new PlayerStat(new Player('Player 3', 3 ), 2, 4, 4, 10, 40, 2, 1, 7, 20, 10, 70, 0, 0, 0, 0));

    this.gameStats.push(new GameRecord( 1, new Player('Player 1', 1 ), new Player('Player 2', 2 ), Result.Won, Throw.Rock, Throw.Scissors, '12/02/2013 15:30'));
    this.gameStats.push(new GameRecord( 2, new Player('Player 1', 1 ), new Player('Player 2', 2 ), Result.Loss, Throw.Rock, Throw.Paper, '12/02/2013 15:31'));
    this.gameStats.push(new GameRecord( 3, new Player('Player 1', 1 ), new Player('Player 3', 3 ), Result.Won, Throw.Rock, Throw.Paper, '12/02/2013 15:32'));
  }

  playPracticeGame(request: PlayPracticeGameRequest): Observable<PlayPracticeGameResponse> {
    this.playPracticeGameCalledWith = request;

    return of(new PlayPracticeGameResponse(Outcome.P1Wins));
  }

  playGame(request: PlayGameRequest): Observable<GameResult> {
    this.playGameCalledWith = request;

    return of(new GameResult(request.player1, request.player2, request.player1Throw, request.player2Throw, this.stubOutcome));
  }

  getPlayers(): Observable<Player[]> {
    const tempArray: Player[] = [];
    tempArray.push(new Player('Player 1', 1 ));
    tempArray.push(new Player('Player 2', 2 ));
    tempArray.push(new Player('Player 3', 3 ));
    return of(tempArray);
  }

  getPlayerStats(): Observable<PlayerStat[]> {
    // let tempArray: PlayerStat[] = [];
    // tempArray.push(new PlayerStat(new Player("Player 1",1 ), 10, 0, 0, 10, 100, 10, 0, 0, 100, 0, 0));
    // tempArray.push(new PlayerStat(new Player("Player 2",2 ), 6, 2, 2, 10, 70, 5, 2, 3, 50, 20, 30));
    // tempArray.push(new PlayerStat(new Player("Player 3",3 ), 2, 4, 4, 10, 40, 2, 1, 7, 20, 10, 70));
    return of(this.playerStats);
  }

  getPlayerGameRecords(): Observable<GameRecord[]> {
    return of(this.gameStats);
  }
}
