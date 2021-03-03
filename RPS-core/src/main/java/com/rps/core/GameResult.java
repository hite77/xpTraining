package com.rps.core;


public class GameResult {
    private Player player1;
    private Player player2;
    private Outcome outcome;
    private Throw player1Throw;
    private Throw player2Throw;
    private int gameResultId;
    private String gameTime;

    public GameResult(Player player1, Player player2, Outcome outcome, Throw player1Throw, Throw player2Throw, int gameResultId, String gameTime) {
        this.player1 = player1;
        this.player2 = player2;
        this.outcome = outcome;
        this.player1Throw = player1Throw;
        this.player2Throw = player2Throw;
        this.gameResultId = gameResultId;
        this.gameTime = gameTime;
    }

    public GameResult(GameResult gameResult) {
        this.player1 = gameResult.player1;
        this.player2 = gameResult.player2;
        this.outcome = gameResult.outcome;
        this.player1Throw = gameResult.player1Throw;
        this.player2Throw = gameResult.player2Throw;
        this.gameResultId = gameResult.gameResultId;
        this.gameTime = gameResult.gameTime;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    public Outcome getOutcome() {
        return outcome;
    }

    public int getGameResultId() {
        return gameResultId;
    }

    public String getGameTime() { return gameTime; }

    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    public void setOutcome(Outcome outcome) {
        this.outcome = outcome;
    }

    public void setGameResultId(int gameResultId) {
        this.gameResultId = gameResultId;
    }

    public Throw getPlayer1Throw() {
        return player1Throw;
    }

    public void setPlayer1Throw(Throw player1Throw) {
        this.player1Throw = player1Throw;
    }

    public Throw getPlayer2Throw() {
        return player2Throw;
    }

    public void setPlayer2Throw(Throw player2Throw) {
        this.player2Throw = player2Throw;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GameResult)) return false;
        GameResult that = (GameResult) o;
        return getPlayer1().equals( that.getPlayer1()) &&
                getPlayer2().equals( that.getPlayer2()) &&
                getOutcome() == that.getOutcome() &&
                getGameResultId() == that.getGameResultId() &&
                getGameTime().equals(that.getGameTime());
    }

    @Override
    public int hashCode() {
        return getPlayer1().hashCode() + getPlayer2().hashCode() + getOutcome().hashCode();
    }

    @Override
    public String toString() {
        return "GameResult{" +
                "player1=" + player1 +
                ", player2=" + player2 +
                ", outcome=" + outcome +
                ", gameResultId=" + gameResultId +
                ", gameTime=" + gameTime +
                '}';
    }
}
