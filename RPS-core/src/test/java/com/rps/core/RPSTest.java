package com.rps.core;

import org.junit.Test;

import static com.rps.core.Outcome.*;
import static com.rps.core.Throw.*;
import static org.junit.Assert.assertEquals;

public class RPSTest {

    @Test
    public void RockVsScissors(){ assertEquals(P1_WINS, RPS.play(ROCK, SCISSORS)); }

    @Test
    public void ScissorsVsRock(){
        assertEquals(P2_WINS, RPS.play(SCISSORS, ROCK));
    }

    @Test
    public void ScissorsVsPaper(){
        assertEquals(P1_WINS, RPS.play(SCISSORS, PAPER));
    }

    @Test
    public void PaperVsScissors(){ assertEquals(P2_WINS, RPS.play(PAPER, SCISSORS)); }

    @Test
    public void PaperVsRock(){
        assertEquals(P1_WINS, RPS.play(PAPER, ROCK));
    }

    @Test
    public void RockVsPaper(){
        assertEquals(P2_WINS, RPS.play(ROCK, PAPER));
    }

    @Test
    public void RockVsLizard(){ assertEquals(P1_WINS, RPS.play(ROCK, LIZARD)); }

    @Test
    public void LizardVsRock(){ assertEquals(P2_WINS, RPS.play(LIZARD, ROCK)); }

    @Test
    public void LizardVsSpock(){ assertEquals(P1_WINS, RPS.play(LIZARD, SPOCK)); }

    @Test
    public void SpockVsLizard(){ assertEquals(P2_WINS, RPS.play(SPOCK, LIZARD)); }

    @Test
    public void SpockVsScissors(){ assertEquals(P1_WINS, RPS.play(SPOCK, SCISSORS)); }

    @Test
    public void ScissorsVsSpock(){ assertEquals(P2_WINS, RPS.play(SCISSORS, SPOCK)); }

    @Test
    public void ScissorsVsLizard(){ assertEquals(P1_WINS, RPS.play(SCISSORS, LIZARD)); }

    @Test
    public void LizardVsScissors(){ assertEquals(P2_WINS, RPS.play(LIZARD, SCISSORS)); }

    @Test
    public void LizardVsPaper(){ assertEquals(P1_WINS, RPS.play(LIZARD, PAPER)); }

    @Test
    public void PaperVsLizard(){ assertEquals(P2_WINS, RPS.play(PAPER, LIZARD)); }

    @Test
    public void PaperVsSpock(){ assertEquals(P1_WINS, RPS.play(PAPER, SPOCK)); }

    @Test
    public void SpockVsPaper(){ assertEquals(P2_WINS, RPS.play(SPOCK, PAPER)); }

    @Test
    public void SpockVsRock(){ assertEquals(P1_WINS, RPS.play(SPOCK, ROCK)); }

    @Test
    public void RockVsSpock(){ assertEquals(P2_WINS, RPS.play(ROCK, SPOCK)); }

    @Test
    public void RockVsRock(){
        assertEquals(TIE, RPS.play(ROCK, ROCK));
    }

    @Test
    public void PaperVsPaper(){
        assertEquals(TIE, RPS.play(PAPER, PAPER));
    }

    @Test
    public void ScissorsVsScissors(){
        assertEquals(TIE, RPS.play(SCISSORS, SCISSORS));
    }

    @Test
    public void LizardVsLizard() {assertEquals(TIE, RPS.play(LIZARD, LIZARD)); }

    @Test
    public void SpockVsSpock() {assertEquals(TIE, RPS.play(SPOCK, SPOCK)); }
}
