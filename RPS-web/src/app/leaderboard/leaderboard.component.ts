import { Component, OnInit } from '@angular/core';
import { PlayerStat, GameRecord } from '../game/game';
import { GameGateway } from '../game/game.gateway';

@Component({
  selector: 'app-leaderboard',
  templateUrl: './leaderboard.component.html',
  styleUrls: ['./leaderboard.component.css']
})
export class LeaderboardComponent implements OnInit {

  playerStats: PlayerStat[] = [];
  gameRecords: GameRecord[] = [];
  selectedPlayer = -1;

  constructor(private gameGateway: GameGateway) {
    this.playerStats = [];
    this.gameRecords = [];
  }

  ngOnInit() {
    this.getPlayerStats();
  }

  showPlayer( playerId: number ) {
    console.log('Click - Player: ', playerId );
    this.selectedPlayer = playerId;
    if ( playerId === -1 ) {
      this.getPlayerStats();
    } else {
      this.getGameRecords();
    }

  }

  getPlayerStats() {
    this.playerStats = [];
    this.gameGateway.getPlayerStats().subscribe(returnedPlayerStats => {
      for (let i = 0; i < returnedPlayerStats.length; i++) {
        console.log(returnedPlayerStats[i].winPercentage);
        this.playerStats.push(this.roundToOneDecimal(returnedPlayerStats[i]));
      }
      // this.playerList = this.playerList.sort((a,b) => a.name.localeCompare(b.name));
      console.log('got player Stats', this.playerStats);
    });
  }

  roundToOneDecimal(playerStat: PlayerStat) {
    playerStat.winPercentage = Math.round(playerStat.winPercentage * 10) / 10;
    playerStat.rockPercent = Math.round(playerStat.rockPercent * 10) / 10;
    playerStat.paperPercent = Math.round(playerStat.paperPercent * 10) / 10;
    playerStat.scissorsPercent = Math.round(playerStat.scissorsPercent * 10) / 10;
    return playerStat;
  }

  getGameRecords() {
    this.gameRecords = [];
    this.gameGateway.getPlayerGameRecords( this.selectedPlayer).subscribe(returnedGameRecords => {
      for (let i = 0; i < returnedGameRecords.length; i++) {
        this.gameRecords.push(returnedGameRecords[i]);
      }
      // this.playerList = this.playerList.sort((a,b) => a.name.localeCompare(b.name));
      console.log('got player game records', this.gameRecords);
    });
  }

  getPlayerDescriptionHeader(): string {
    for (const playerStat of this.playerStats) {
      if (playerStat.player.id === this.selectedPlayer) {
        return playerStat.player.name +
            ', Winning Percentage: ' + playerStat.winPercentage +
            '%, Rock Percentage: ' + playerStat.rockPercent +
            '%, Paper Percentage: ' + playerStat.paperPercent +
            '%, Scissors Percentage: ' + playerStat.scissorsPercent + '%';
      }
    }
    return 'not found';
  }

  colorForPercent(percent: number): string {
    if (percent >= 85) {
      return 'red';
    } else if (percent >= 70) {
      return 'orange';
    } else if ((percent > 15) && (percent <= 30)) {
      return 'lightblue';
    } else if (percent <= 15) {
      return 'blue';
    }
    return '';
  }
}
