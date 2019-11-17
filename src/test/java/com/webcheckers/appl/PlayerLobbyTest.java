package com.webcheckers.appl;


import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Class to test the GameCenter
 *
 * @author Aaron, Gavin, Mark, Ries, Zach
 */
@Tag("appl-tier")
public class PlayerLobbyTest {

    /**
     * Fields used for player lobby testing.
     */
    private PlayerLobby CuT;
    private Player player1;
    private Player player2;
    private Player player3;

    @BeforeEach
    private void setup(){

        player1 = mock(Player.class);
        player2 = mock(Player.class);
        player3 = mock(Player.class);

        CuT = new PlayerLobby();

    }

    /**
     * Test if the players have been added correctly to the user list. Uses getUser() to verify the result.
     */
    @Test
    public void testAddUsers(){

        CuT.addUser(player1);
        CuT.addUser(player2);
        CuT.addUser(player3);
        assertNotNull(CuT.getUser(player1.getName()));
        assertNotNull(CuT.getUser(player2.getName()));
        assertNotNull(CuT.getUser(player3.getName()));

    }

    /**
     * Test if the player has been added correctly to the player list. Uses isInGame() to verify the result.
     */
    @Test
    public void testAddPlayer(){

        CuT.addPlayer(player1);
        CuT.addPlayer(player2);
        CuT.addPlayer(player3);
        assertFalse(CuT.isInGame(player1));
        assertFalse(CuT.isInGame(player2));
        assertFalse(CuT.isInGame(player3));

    }

    /**
     * Test if a player is removed properly from the player list.
     */
    @Test
    public void testRemovePlayer(){

        CuT.addPlayer(player1);
        CuT.addPlayer(player2);
        CuT.addPlayer(player3);
        CuT.removeUser(player1);
        CuT.removeUser(player2);
        CuT.removeUser(player3);
        CuT.removePlayer(player1);
        CuT.removePlayer(player2);
        CuT.removePlayer(player3);
        CuT.removeGamePlayer(player1);
        CuT.removeGamePlayer(player2);
        CuT.removeGamePlayer(player3);
        assertNull(CuT.getUser(player1.getName()));
        assertNull(CuT.getUser(player2.getName()));
        assertNull(CuT.getUser(player3.getName()));
    }

    /**
     * Test if a player is properly added to the inGame list
     */
    @Test
    public void testAddGamePlayer(){

        CuT.addPlayer(player1);
        CuT.addPlayer(player2);
        CuT.addPlayer(player3);
        CuT.addGamePlayer(player1);
        CuT.addGamePlayer(player2);
        CuT.addGamePlayer(player3);
        assertTrue(CuT.isInGame(player1));
        assertTrue(CuT.isInGame(player2));
        assertTrue(CuT.isInGame(player3));

    }

}
