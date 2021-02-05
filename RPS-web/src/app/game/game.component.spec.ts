import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GameComponent } from './game.component';
import { MaterialModule } from '../../material.module';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { StubGameGateway } from './stub.game.gateway';
import { GameGateway } from './game.gateway';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { Outcome } from './game';

describe('GameComponent', () => {
  let component: GameComponent;
  let fixture: ComponentFixture<GameComponent>;
  let stubRpsGateway: StubGameGateway;
  let htmlTestBed: any;

  beforeEach(async(() => {
    stubRpsGateway = new StubGameGateway();

    TestBed.configureTestingModule({
      declarations: [ GameComponent ],
      imports: [
        MaterialModule,
        BrowserAnimationsModule,
        ReactiveFormsModule,
        FormsModule
      ],
      providers: [
        { provide: GameGateway, useValue: stubRpsGateway }
      ],
      schemas: [CUSTOM_ELEMENTS_SCHEMA]
    })
    .compileComponents();
  }));

  beforeEach(async(() => {
    fixture = TestBed.createComponent(GameComponent);
    component = fixture.componentInstance;
    htmlTestBed = fixture.debugElement.nativeElement;
    stubRpsGateway.stubOutcome = Outcome.P2Wins;
    fixture.detectChanges();
  }));

  function triggerSelect(id: string, passedValue: string) {
    const element: HTMLInputElement = fixture.nativeElement.querySelector(`#${id}`);
    element.value = passedValue;
    element.dispatchEvent(new Event('change'));
  }

  // This routine triggers a mat-select based on the drop down position
  // The first or 0 index position is 'select a value' so the actual
  // values are index positions 1 - N
  function triggerMatSelect(id: string, optionIndex: number) {
    const debugElement = fixture.debugElement.query(By.css(`#${id}`));
    const matSelect = debugElement.query(By.css('.mat-select-trigger')).nativeElement;
    matSelect.click();
    fixture.detectChanges();
    console.log('available options: ', debugElement.queryAll(By.css('.mat-option')));
    const matOption = debugElement.queryAll(By.css('.mat-option'))[optionIndex].nativeElement;
    matOption.click();
    fixture.detectChanges();
  }

  function triggerMatSlider(id: string) {
    // This does work, not sure which is better
    // const toggle = fixture.debugElement.query(By.css(`#${id}`));
    // toggle.triggerEventHandler('change', null);
    // fixture.detectChanges();

    const toggle = fixture.debugElement.query(By.css(`#${id}`));
    const matSelect = toggle.query(By.css('.mat-slide-toggle-input')).nativeElement;
    console.log(matSelect);
    matSelect.click();
    fixture.detectChanges();

  }

  function triggerInput(id: string, passedValue: string) {
    const element: HTMLInputElement = fixture.nativeElement.querySelector(`#${id}`);
    element.value = passedValue;
    element.dispatchEvent(new Event('input'));
  }

  function getOptionText(id: string, optionIndex: string): string {
    const debugElement = fixture.debugElement.query(By.css(`#${id}`));
    const matSelect = debugElement.query(By.css('.mat-select-trigger')).nativeElement;
    matSelect.click();
    fixture.detectChanges();
    const matOption = debugElement.queryAll(By.css('.mat-option'))[optionIndex].nativeElement;
    matOption.click();
    fixture.detectChanges();
    return matOption.textContent;
  }

  it('should toggle between ranked and practice game', () => {
    expect(component.isPracticeGame).toBe(false);

    expect(fixture.nativeElement.querySelector('#player1Throw')).toBeTruthy();
    expect(fixture.nativeElement.querySelector('#player2Throw')).toBeTruthy();
    expect(fixture.nativeElement.querySelector('#player1Name')).toBeTruthy();
    expect(fixture.nativeElement.querySelector('#player2Name')).toBeTruthy();

    triggerMatSlider('gameModeToggle');

    expect(component.isPracticeGame).toBe(true);
    expect(fixture.nativeElement.querySelector('#player1Name')).toBeNull();
    expect(fixture.nativeElement.querySelector('#player1Throw')).toBeTruthy();
    expect(fixture.nativeElement.querySelector('#player2Name')).toBeNull();
    expect(fixture.nativeElement.querySelector('#player2Throw')).toBeTruthy();
  });

  it('should process a ranked game through the gateway',  async(() => {
    component.ngOnInit();
    component.isPracticeGame = false;
    fixture.detectChanges();
    triggerMatSelect('player1Throw', 3);
    triggerMatSelect('player1Name', 0);

    triggerMatSelect('player2Throw', 1);
    triggerMatSelect('player2Name', 2);

    const submit = fixture.nativeElement.querySelector('#submit-ranked');
    submit.click();

    fixture.whenStable().then(() => {
      fixture.detectChanges();
      expect(stubRpsGateway.playGameCalledWith.player1Throw).toBe('SCISSORS');
      expect(stubRpsGateway.playGameCalledWith.player1.name).toBe('Player 1');
      expect(stubRpsGateway.playGameCalledWith.player1.id).toBe(1);

      expect(stubRpsGateway.playGameCalledWith.player2Throw).toBe('ROCK');
      expect(stubRpsGateway.playGameCalledWith.player2.name).toBe('Player 3');
      expect(stubRpsGateway.playGameCalledWith.player2.id).toBe(3);
      expect(fixture.nativeElement.querySelector('#game-outcome').innerHTML).toContain('Player 3 Wins');
    });
  }));

  it('should process a ranked game using lizard spock through the gateway',  async(() => {
    component.ngOnInit();
    component.isPracticeGame = false;
    stubRpsGateway.stubOutcome = Outcome.P1Wins;
    fixture.detectChanges();
    triggerMatSelect('player1Throw', 4); // lizard wins
    triggerMatSelect('player1Name', 0);

    triggerMatSelect('player2Throw', 5); // spock
    triggerMatSelect('player2Name', 2);

    const submit = fixture.nativeElement.querySelector('#submit-ranked');
    submit.click();

    fixture.whenStable().then(() => {
      fixture.detectChanges();
      expect(stubRpsGateway.playGameCalledWith.player1Throw).toBe('LIZARD');
      expect(stubRpsGateway.playGameCalledWith.player1.name).toBe('Player 1');
      expect(stubRpsGateway.playGameCalledWith.player1.id).toBe(1);

      expect(stubRpsGateway.playGameCalledWith.player2Throw).toBe('SPOCK');
      expect(stubRpsGateway.playGameCalledWith.player2.name).toBe('Player 3');
      expect(stubRpsGateway.playGameCalledWith.player2.id).toBe(3);
      expect(fixture.nativeElement.querySelector('#game-outcome').innerHTML).toContain('Player 1 Wins');
    });
  }));

  it('Text for throws is correctly displayed', async( () => {
    expect(getOptionText('player1Throw', '1').trim()).toBe('Rock');
    expect(getOptionText('player1Throw', '2').trim()).toBe('Paper');
    expect(getOptionText('player1Throw', '3').trim()).toBe('Scissors');
    expect(getOptionText('player1Throw', '4').trim()).toBe('Lizard');
    expect(getOptionText('player1Throw', '5').trim()).toBe('Spock');
  }));

  it('should process a practice game through the gateway', async(() => {
    component.isPracticeGame = true;
    component.updateValidator();
    fixture.detectChanges();
    triggerMatSelect('player1Throw', 3);
    triggerMatSelect('player2Throw', 1);

    const submit = fixture.nativeElement.querySelector('#submit-practice');
    submit.click();

    fixture.whenStable().then(() => {
      fixture.detectChanges();
      expect(stubRpsGateway.playPracticeGameCalledWith.player1Throw).toBe('SCISSORS');
      expect(stubRpsGateway.playPracticeGameCalledWith.player2Throw).toBe('ROCK');
    });
  }));

  it('should disable submit button on page load for both cases', () => {
    const submitRanked  = fixture.nativeElement.querySelector('#submit-ranked');
    const submitPractice = fixture.nativeElement.querySelector('#submit-practice');
    expect(submitRanked.disabled).toBeTruthy();
    expect(submitPractice).toBeFalsy();

    triggerMatSlider('gameModeToggle');

    const submitRankedFlipped = fixture.nativeElement.querySelector('#submit-ranked');
    const submitPracticeFlipped = fixture.nativeElement.querySelector('#submit-practice');
    expect(submitPracticeFlipped.disabled).toBeTruthy();
    expect(submitRankedFlipped).toBeFalsy();
  });

  it('when Player 1 wins the result is displayed as: Player 1 Wins', () => {

    // Setup here still needed
    component.ngOnInit();
    component.isPracticeGame = false;
    fixture.detectChanges();

    stubRpsGateway.stubOutcome = Outcome.P1Wins;

    triggerMatSelect('player1Name', 0);
    triggerMatSelect('player2Name', 2);
    triggerMatSelect('player1Throw', 3);
    triggerMatSelect('player2Throw', 1);

    const submit = fixture.nativeElement.querySelector('#submit-ranked');
    submit.click();

    fixture.whenStable().then(() => {
      fixture.detectChanges();
      expect(fixture.nativeElement.querySelector('#game-outcome').innerHTML).toContain('Player 1 Wins');
    });

  });

  it('when the game is a tie, the app displays: The game is a Tie', () => {

    // Setup here still needed
    component.ngOnInit();
    component.isPracticeGame = false;
    fixture.detectChanges();

    stubRpsGateway.stubOutcome = Outcome.Tie;

    triggerMatSelect('player1Name', 0);
    triggerMatSelect('player2Name', 2);
    triggerMatSelect('player1Throw', 3);
    triggerMatSelect('player2Throw', 1);

    const submit = fixture.nativeElement.querySelector('#submit-ranked');
    submit.click();

    fixture.whenStable().then(() => {
      fixture.detectChanges();
      expect(fixture.nativeElement.querySelector('#game-outcome').innerHTML).toContain('The game is a Tie');
    });
  });

  it('should disable submit button when same player is selected twice', () => {
    const submitRanked  = fixture.nativeElement.querySelector('#submit-ranked');
    triggerMatSelect('player1Name', 0);
    triggerMatSelect('player2Name', 0);
    triggerMatSelect('player1Throw', 3);
    triggerMatSelect('player2Throw', 1);
    expect(submitRanked.disabled).toBeTruthy();
  });
});
