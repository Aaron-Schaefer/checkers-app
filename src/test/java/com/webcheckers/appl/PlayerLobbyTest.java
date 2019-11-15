package com.webcheckers.appl;


import com.webcheckers.model.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;

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
     * Test if the players have been added correctly.
     */
    @Test
    public void testAddUsesr(){

        CuT.addPlayer(player1);
        CuT.addPlayer(player2);
        CuT.addPlayer(player3);

    }

}
