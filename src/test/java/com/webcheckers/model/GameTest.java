package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Class to test the Game
 *
 * @author An Chang (Mark).
 */

@Tag("Model-tier")
public class GameTest {

    /**
     * Fields used to test the Game class.
     */
    private Game CuT;
    private Board board;
    private Player redPlayer;
    private Player whitePlayer;

    /**
     * Setup before each test.
     */
    @BeforeEach
    public void Setup(){

        redPlayer = mock(Player.class);
        whitePlayer = mock(Player.class);
        board = mock(Board.class);
        CuT = new Game(redPlayer, whitePlayer,1);

    }

    /**
     * Test if players are added properly.
     */
    @Test
    public void testPlayerAddition(){

        assertEquals(CuT.getRedPlayer(),redPlayer);
        assertEquals(CuT.getWhitePlayer(),whitePlayer);

    }

    /**
     * Test if the board can be retrieved properly.
     */
    @Test
    public void testGetBoard(){

        assertNotNull(CuT.getBoard());

    }

    /**
     * Test if the game ID can be retrieved properly.
     */
    @Test
    public void getGameID(){

        assertEquals(CuT.getGameID(),1);

    }


}
