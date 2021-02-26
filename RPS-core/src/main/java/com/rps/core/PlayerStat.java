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
        return (gamesWon + (gamesTied * .5)) / getGamesPlayed() * 100;
    }

    public Double getRockPercent(){
        return 100.0 * rocksThrown / getGamesPlayed();
    }

    public Double getPaperPercent(){
        return 100.0 * papersThrown / getGamesPlayed();
    }

    public Double getScissorsPercent(){
        return 100.0 * scissorsThrown / getGamesPlayed();
    }

    public Double getLizardPercent() { return 100.0 * lizardsThrown / getGamesPlayed(); }

    public Double getSpockPercent() { return 100.0 * spocksThrown / getGamesPlayed(); }

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
