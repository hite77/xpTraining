export interface Game {
  p1Throw: Throw;
  p2Throw: Throw;
  outcome: Outcome;
}

export enum Throw {
  Rock = 'ROCK',
  Paper = 'PAPER',
  Scissors = 'SCISSORS',
  Lizard = 'LIZARD',
  Spock = 'SPOCK'
}

export const throwLocalization = {
  [Throw.Rock]: 'Rock',
  [Throw.Paper]: 'Paper',
  [Throw.Scissors]: 'Scissors',
  [Throw.Lizard]: 'Lizard',
  [Throw.Spock]: 'Spock'
};

export enum Outcome {
  P1Wins = 'P1_WINS',
  P2Wins = 'P2_WINS',
  Tie = 'TIE',
}

export enum Result {
  Won = 'WON',
  Loss = 'LOSS',
  Tie = 'TIE'
}

export const outcomeLocatization = {
  [Outcome.P1Wins]: 'Player 1 Wins',
  [Outcome.P2Wins]: 'Player 2 Wins',
  [Outcome.Tie]: 'Tie',
};

export class Player {
  constructor(
    public name: string,
    public id: number,
  ) {}
}

export class GameResult {
  constructor(
    public player1: Player,
    public player2: Player,
    public p1Throw: Throw,
    public p2Throw: Throw,
    public outcome: Outcome,
  ) {}
}

export class PlayerStat {
  constructor(
    public player: Player,
    public gamesWon: number,
    public gamesLost: number,
    public gamesTied: number,
    public gamesPlayed: number,
    public winPercentage: number,
    public rocksThrown: number,
    public papersThrown: number,
    public scissorsThrown: number,
    public rockPercent: number,
    public paperPercent: number,
    public scissorsPercent: number
  ) {}
}


export class GameRecord {
  constructor(
    public gameResultId: number,
    public player: Player,
    public opponent: Player,
    public result: Result,
    public playerThrow: Throw,
    public opponentThrow: Throw
  ) {}
}
