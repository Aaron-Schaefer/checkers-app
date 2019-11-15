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
public class GameCenterTest {

    private GameCenter Cut;
    private Player redPlayer;
    private Player whitePlayer;

    /**
     * Initialization before each test.
     */
    @BeforeEach
    private void setup(){

        redPlayer = mock(Player.class);
        whitePlayer = mock(Player.class);
        Cut = new GameCenter();


    }

    /**
     * Test if MakeGame makes the game correctly.
     */
    @Test
    public void testMakeGame(){

        Cut.makeGame(redPlayer,whitePlayer);
        assertEquals(Cut.getNumOfGames(), 1);
        assertTrue(Cut.containsKey(redPlayer));
        assertTrue(Cut.containsKey(whitePlayer));

    }
}
