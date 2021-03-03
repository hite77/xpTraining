import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LeaderboardComponent } from './leaderboard.component';
import { StubGameGateway } from '../game/stub.game.gateway';
import { GameGateway } from '../game/game.gateway';
import { By } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule } from '@angular/forms';
import {GameRecord, Player, Result, Throw} from '../game/game';

describe('LeaderboardComponent', () => {
  let component: LeaderboardComponent;
  let fixture: ComponentFixture<LeaderboardComponent>;
  let stubRpsGateway: StubGameGateway;

  beforeEach(async(() => {
    stubRpsGateway = new StubGameGateway();

    TestBed.configureTestingModule({
      declarations: [ LeaderboardComponent ],
      imports: [
        BrowserAnimationsModule,
        FormsModule
      ],
      providers: [
        { provide: GameGateway, useValue: stubRpsGateway }
      ]
    })
    .compileComponents();
  }));

  beforeEach(() => {

    fixture = TestBed.createComponent(LeaderboardComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create leaderboard with data', () => {
    expect(component).toBeTruthy();
    const tableRows = fixture.nativeElement.querySelectorAll('tr');
    console.log( 'tableRows: ', tableRows );
    // Data rows
    const row1 = tableRows[1];
    expect(row1.cells[1].innerHTML).toBe('100');
    expect(row1.cells[2].innerHTML).toBe('10');
    expect(row1.cells[3].innerHTML).toBe('10');
    expect(row1.cells[4].innerHTML).toBe('0');

    const row2 = tableRows[2];
    expect(row2.cells[1].innerHTML).toBe('70');
    expect(row2.cells[2].innerHTML).toBe('10');
    expect(row2.cells[3].innerHTML).toBe('6');
    expect(row2.cells[4].innerHTML).toBe('2');

    const row3 = tableRows[3];
    expect(row3.cells[1].innerHTML).toBe('40');
    expect(row3.cells[2].innerHTML).toBe('10');
    expect(row3.cells[3].innerHTML).toBe('2');
    expect(row3.cells[4].innerHTML).toBe('4');
    stubRpsGateway.playerStats[0].gamesWon = 11;
    const player = fixture.nativeElement.querySelector('button');
    console.log('Button', player);
    player.click();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const tableRows = fixture.nativeElement.querySelectorAll('tr');
      const row1 = tableRows[1];
      expect(row1.cells[0].innerHTML).toBe('Player 2');
      expect(row1.cells[1].innerHTML).toBe('WON');
      expect(row1.cells[2].innerHTML).toBe('ROCK');
      expect(row1.cells[3].innerHTML).toBe('SCISSORS');
      expect(row1.cells[4].innerHTML).toBe('12/02/2013 15:30');
    });
  });

  it('should refresh leaderboard with data', () => {
    expect(component).toBeTruthy();
    const tableRows = fixture.nativeElement.querySelectorAll('tr');
    console.log( 'tableRows: ', tableRows );
    // Data rows
    const row1 = tableRows[1];
    expect(row1.cells[1].innerHTML).toBe('100');
    expect(row1.cells[2].innerHTML).toBe('10');
    expect(row1.cells[3].innerHTML).toBe('10');
    expect(row1.cells[4].innerHTML).toBe('0');

    stubRpsGateway.playerStats[0].gamesWon = 11;
    stubRpsGateway.playerStats[0].winPercentage = 95;
    stubRpsGateway.playerStats[0].rockPercent = 80;
    const refresh = fixture.nativeElement.querySelector('button.refresh');
    console.log('Button', refresh);
    refresh.click();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const tableRows = fixture.nativeElement.querySelectorAll('tr');
      const row1 = tableRows[1];
      expect(row1.cells[1].innerHTML).toBe('95');
      expect(row1.cells[3].innerHTML).toBe('11');
      expect(row1.cells[6].innerHTML).toBe('80');

    });
  });

  it('leaderboard percentages should only show one decimal place', () => {
    expect(component).toBeTruthy();

    stubRpsGateway.playerStats[0].winPercentage = 22.1111;
    stubRpsGateway.playerStats[1].winPercentage = 33.1111;
    stubRpsGateway.playerStats[2].winPercentage = 44.1111;

    stubRpsGateway.playerStats[0].rockPercent = 22.2222;
    stubRpsGateway.playerStats[1].rockPercent = 33.2222;
    stubRpsGateway.playerStats[2].rockPercent = 44.2222;

    stubRpsGateway.playerStats[0].paperPercent = 22.3333;
    stubRpsGateway.playerStats[1].paperPercent = 33.3333;
    stubRpsGateway.playerStats[2].paperPercent = 44.3333;

    stubRpsGateway.playerStats[0].scissorsPercent = 22.4444;
    stubRpsGateway.playerStats[1].scissorsPercent = 33.4444;
    stubRpsGateway.playerStats[2].scissorsPercent = 44.4444;

    stubRpsGateway.playerStats[0].lizardPercent = 22.5555;
    stubRpsGateway.playerStats[1].lizardPercent = 33.5555;
    stubRpsGateway.playerStats[2].lizardPercent = 44.5555;

    stubRpsGateway.playerStats[0].spockPercent = 22.6666;
    stubRpsGateway.playerStats[1].spockPercent = 33.6666;
    stubRpsGateway.playerStats[2].spockPercent = 44.6666;

    const refresh = fixture.nativeElement.querySelector('button.refresh');
    console.log('Button', refresh);
    refresh.click();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const tableRows = fixture.nativeElement.querySelectorAll('tr');
      console.log( 'tableRows: ', tableRows );
      const row1 = tableRows[1];
      expect(row1.cells[1].innerHTML).toBe('22.1');
      expect(row1.cells[6].innerHTML).toBe('22.2');
      expect(row1.cells[7].innerHTML).toBe('22.3');
      expect(row1.cells[8].innerHTML).toBe('22.4');
      expect(row1.cells[9].innerHTML).toBe('22.6');
      expect(row1.cells[10].innerHTML).toBe('22.7');

      const row2 = tableRows[2];
      expect(row2.cells[1].innerHTML).toBe('33.1');
      expect(row2.cells[6].innerHTML).toBe('33.2');
      expect(row2.cells[7].innerHTML).toBe('33.3');
      expect(row2.cells[8].innerHTML).toBe('33.4');
      expect(row2.cells[9].innerHTML).toBe('33.6');
      expect(row2.cells[10].innerHTML).toBe('33.7');

      const row3 = tableRows[3];
      expect(row3.cells[1].innerHTML).toBe('44.1');
      expect(row3.cells[6].innerHTML).toBe('44.2');
      expect(row3.cells[7].innerHTML).toBe('44.3');
      expect(row3.cells[8].innerHTML).toBe('44.4');
      expect(row3.cells[9].innerHTML).toBe('44.6');
      expect(row3.cells[10].innerHTML).toBe('44.7');
  });
  });

  it('leaderboard should show games tied', () => {
    expect(component).toBeTruthy();

    stubRpsGateway.playerStats[0].gamesTied = 1;
    stubRpsGateway.playerStats[1].gamesTied = 2;
    stubRpsGateway.playerStats[2].gamesTied = 3;

    const refresh = fixture.nativeElement.querySelector('button.refresh');
    console.log('Button', refresh);
    refresh.click();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const tableRows = fixture.nativeElement.querySelectorAll('tr');
      console.log('tableRows: ', tableRows);
      const row1 = tableRows[1];
      expect(row1.cells[5].innerHTML).toBe('1');

      const row2 = tableRows[2];
      expect(row2.cells[5].innerHTML).toBe('2');

      const row3 = tableRows[3];
      expect(row3.cells[5].innerHTML).toBe('3');
    });
  });

  it('leaderboard header contains: Leader Board', () => {
    expect(component).toBeTruthy();
    const header = fixture.nativeElement.querySelector('.page-header');
    expect(header.innerHTML).toBe('LEADER BOARD');
  });

  it('leaderboard header contains correct percentages', () => {
    expect(component).toBeTruthy();
    stubRpsGateway.playerStats[1].winPercentage = 55;
    stubRpsGateway.playerStats[1].rockPercent = 75;
    stubRpsGateway.playerStats[1].paperPercent = 33;
    stubRpsGateway.playerStats[1].scissorsPercent = 42;
    stubRpsGateway.playerStats[1].lizardPercent = 11;
    stubRpsGateway.playerStats[1].spockPercent = 22;

    const player = fixture.nativeElement.querySelectorAll('button');
    console.log('Button', player);
    player[1].click();
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const header = fixture.nativeElement.querySelector('.page-header');
      expect(header.innerHTML).toBe('Player 2, Winning Percentage: 55%, Rock Percentage: 75%, ' +
          'Paper Percentage: 33%, Scissors Percentage: 42%, Lizard Percentage: 11%, Spock Percentage: 22%');
    });
  });

  it('In leaderboard player profile, Wins are color coded in Green, the Loses are Red and the Ties are Light Blue', () => {
    expect(component).toBeTruthy();
    stubRpsGateway.gameStats = [];
    stubRpsGateway.gameStats.push(new GameRecord( 1, new Player('Player 1', 1 ),
        new Player('Player 2', 2 ), Result.Won, Throw.Rock, Throw.Scissors));
    stubRpsGateway.gameStats.push(new GameRecord( 2, new Player('Player 1', 1 ),
        new Player('Player 2', 2 ), Result.Loss, Throw.Rock, Throw.Paper));
    stubRpsGateway.gameStats.push(new GameRecord( 3, new Player('Player 1', 1 ),
        new Player('Player 3', 3 ), Result.Tie, Throw.Rock, Throw.Rock));
    const player = fixture.nativeElement.querySelectorAll('button');
    console.log('Button', player);
    player[0].click();
    fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const tableRows = fixture.nativeElement.querySelectorAll('tr');
      expect(tableRows[1].cells[1].style.color).toBe('green');
      expect(tableRows[2].cells[1].style.color).toBe('red');
      expect(tableRows[3].cells[1].style.color).toBe('lightblue');
    });
  });

  it('value outside color ranges returns blank style string', () => {
    expect(component.colorForPercent(50)).toBe('');
  });

  it('Percent throws are color coded to indicate patterns', () => {
    expect(component).toBeTruthy();

    stubRpsGateway.playerStats[0].rockPercent = 85;
    stubRpsGateway.playerStats[0].paperPercent = 70;
    stubRpsGateway.playerStats[0].scissorsPercent = 15;
    stubRpsGateway.playerStats[1].rockPercent = 50;
    stubRpsGateway.playerStats[1].scissorsPercent = 30;

    fixture.detectChanges();
    fixture.whenStable().then(() => {
      fixture.detectChanges();
      const tableRows = fixture.nativeElement.querySelectorAll('tr');

      expect(tableRows[1].cells[6].style.color).toBe('red');
      expect(tableRows[1].cells[7].style.color).toBe('orange');
      expect(tableRows[1].cells[8].style.color).toBe('blue');
      expect(tableRows[2].cells[8].style.color).toBe('lightblue');
      expect(tableRows[2].cells[6].innerHTML).toBe('50');
      expect(tableRows[2].cells[6].style.color).toBe('');
    });
  });
});
