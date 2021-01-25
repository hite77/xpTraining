package com.rps.core;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public abstract class PlayerRepositoryTest {

    PlayerRepository playerRepository;

    protected abstract PlayerRepository getPlayerRepository();

    @Before
    public void setup() {
        playerRepository = getPlayerRepository();
        playerRepository.save( new Player(  "Wonder Woman", 1));
        playerRepository.save( new Player( "Deadpool", 8));
    }

    @Test
    public void canGetPlayerList() {
        List<Player> playerList = playerRepository.findAll();
        assertEquals( 8, playerList.size() );
        assertEquals( "Wonder Woman", playerList.get(0).getName());
        assertEquals( 1, playerList.get(0).getId());
        assertEquals( "Deadpool", playerList.get(7).getName());
        assertEquals( 8, playerList.get(7).getId());
    }
}
