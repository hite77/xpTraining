import {Component, OnDestroy, OnInit} from '@angular/core';
import {Outcome, Player, Throw, throwLocalization} from './game';
import {GameGateway, PlayGameRequest, PlayPracticeGameRequest} from './game.gateway';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {takeUntil} from 'rxjs/operators';
import {Subject} from 'rxjs';


@Component({
  selector: 'app-game',
  templateUrl: './game.component.html',
  styleUrls: ['./game.component.css']
})
export class GameComponent implements OnInit, OnDestroy {
  
  gameResult: string;
  throwTypes: Throw[] = Object.keys(Throw).map(value => Throw[value]);
  throwLocalization = throwLocalization;
  mostRecentOutcome = '';
  rankedGameRequest: PlayGameRequest;
  practiceGameRequest: PlayPracticeGameRequest;
  playerList: Player[] = [];

  _destroy: Subject<void> = new Subject<void>();

  isPracticeGame = false;

  gameForm: FormGroup;

  constructor(private gameGateway: GameGateway, private fb: FormBuilder) {

  }

  ngOnDestroy(): void {
    this._destroy.next();
    this._destroy.complete();
  }

  ngOnInit() {
    this.playerList = [];
    this.createForm();
    this.getPlayers();

    // this.gameForm.get('player1Throw').pipe(takeUntill(this._destroy)).valueChanges.subscribe(x =>{
    //   console.log(x);
    // this.gameForm.get('pgagdsg').validator;
    // this.gameForm.updateValueAndValidity()
    // })
    this.updateValidator();

  }

  createForm() {
    this.gameForm = this.fb.group({
      selectedPlayer1:[null],
      selectedPlayer2: [null],
      player1Throw: [null, [Validators.required]],
      player2Throw: [null, [Validators.required]]
    })
  }

  getValue( name ){
    return this.gameForm.get( name ).value;
  }

  processRankedGame() {
    this.mostRecentOutcome = '';
    this.rankedGameRequest = new PlayGameRequest(this.getValue('selectedPlayer1'), this.getValue('selectedPlayer2'),
        this.getValue('player1Throw'), this.getValue('player2Throw'));

    this.gameGateway.playGame(this.rankedGameRequest).pipe(takeUntil(this._destroy)).subscribe(gameResult => {
     if (gameResult.outcome === Outcome.P1Wins) {
        this.mostRecentOutcome = gameResult.player1.name + ' Wins';
      } else {
        this.mostRecentOutcome = gameResult.player2.name + ' Wins';
      }
    });
  }

  processPracticeGame() {
    this.mostRecentOutcome = '';

    this.practiceGameRequest = new PlayPracticeGameRequest(this.getValue('player1Throw'), this.getValue('player2Throw'));

    this.gameGateway.playPracticeGame(this.practiceGameRequest).subscribe(gameResult => {
      this.mostRecentOutcome = gameResult.outcome;
    });
  }

  flipToggle() {
    this.isPracticeGame = !this.isPracticeGame;
    this.updateValidator();
  }

  updateValidator() {
    if (this.isPracticeGame == false) {
      // console.log("Real");
      this.gameForm.get('selectedPlayer1').setValidators([Validators.required]);
      this.gameForm.get('selectedPlayer2').setValidators([Validators.required]);
    }
    else {
      // console.log("Practice");
      this.gameForm.get('selectedPlayer1').clearValidators();
      this.gameForm.get('selectedPlayer1').updateValueAndValidity();
      this.gameForm.get('selectedPlayer2').clearValidators();
      this.gameForm.get('selectedPlayer2').updateValueAndValidity();
    }
    // this.gameForm.updateValueAndValidity();
  }

  getPlayers() {
    this.gameGateway.getPlayers().subscribe(returnedPlayers => {
      for(let i = 0; i < returnedPlayers.length; i++) {
        this.playerList.push(returnedPlayers[i]);
      }
      this.playerList = this.playerList.sort((a,b) => a.name.localeCompare(b.name));
      console.log('got players', this.playerList);
    })
  }
}
