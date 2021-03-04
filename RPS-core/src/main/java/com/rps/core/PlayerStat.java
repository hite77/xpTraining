package com.rps.core;

public class PlayerStat {

    private Player player;
    private int gamesWon;
    private int gamesLost;
    private int gamesTied;
    private int rocksThrown;
    private int papersThrown;
    private int scissorsThrown;
    private int lizardsThrown;
    private int spocksThrown;

    public PlayerStat (){
        player = null;
    }

    public PlayerStat (Player player) {
        this.player = player;
        this.gamesWon = 0;
        this.gamesLost = 0;
        this.gamesTied = 0;
        this.rocksThrown = 0;
        this.papersThrown = 0;
        this.scissorsThrown = 0;
        this.lizardsThrown = 0;
        this.spocksThrown = 0;
    }

    public PlayerStat(Player player, int gamesWon, int gamesLost, int gamesTied, int rocksThrown, int papersThrown, int scissorsThrown, int lizardsThrown, int spocksThrown) {
        this.player = player;
        this.gamesWon = gamesWon;
        this.gamesLost = gamesLost;
        this.gamesTied = gamesTied;
        this.rocksThrown = rocksThrown;
        this.papersThrown = papersThrown;
        this.scissorsThrown = scissorsThrown;
        this.lizardsThrown = lizardsThrown;
        this.spocksThrown = spocksThrown;
    }


    public Double getWinPercentage(){
        if (getGamesPlayed() == 0) {
            return 0.0;
        }
        return (gamesWon + (gamesTied * .5)) / getGamesPlayed() * 100;
    }

    public Double getRockPercent(){
        return getPercentage(rocksThrown);
    }

    public Double getPaperPercent(){
        return getPercentage(papersThrown);
    }

    public Double getScissorsPercent(){
        return getPercentage(scissorsThrown);
    }

    public Double getLizardPercent() {
        return getPercentage(lizardsThrown);
    }

    public Double getSpockPercent() {
        return getPercentage(spocksThrown);
    }

    private Double getPercentage(int typeOfThrow) {
        if (getGamesPlayed() == 0) {
            return 0.0;
        }
        return 100.0 * typeOfThrow / getGamesPlayed();
    }

    public long getGamesPlayed(){
        return gamesWon + gamesLost + gamesTied;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getGamesWon() {
        return gamesWon;
    }

    public void setGamesWon(int gamesWon) {
        this.gamesWon = gamesWon;
    }

    public int getGamesLost() {
        return gamesLost;
    }

    public void setGamesLost(int gamesLost) {
        this.gamesLost = gamesLost;
    }

    public int getGamesTied() {
        return gamesTied;
    }

    public void setGamesTied(int gamesTied) {
        this.gamesTied = gamesTied;
    }

    public int getRocksThrown() {
        return rocksThrown;
    }

    public void setRocksThrown(int rocksThrown) {
        this.rocksThrown = rocksThrown;
    }

    public int getPaperssThrown() {
        return papersThrown;
    }

    public void setPaperssThrown(int paperssThrown) {
        this.papersThrown = paperssThrown;
    }

    public int getScissorsThrown() {
        return scissorsThrown;
    }

    public void setScissorsThrown(int scissorsThrown) {
        this.scissorsThrown = scissorsThrown;
    }
}
