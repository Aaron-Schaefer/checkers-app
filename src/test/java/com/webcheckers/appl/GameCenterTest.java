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
        Cut.makeGame(redPlayer,whitePlayer);

    }

    /**
     * Test if makeGame makes the game correctly.
     */
    @Test
    public void testMakeGame(){

        assertEquals(Cut.getNumOfGames(), 1);
        assertTrue(Cut.containsKey(redPlayer));
        assertTrue(Cut.containsKey(whitePlayer));

    }

    /**
     * Test if addGameOver removes the game correctly and see if it gets it into the list of gamesOver.
     */
    @Test
    public void testAddGameOver(){

        Cut.addGameOver(Cut.getGame(redPlayer));
        assertEquals(Cut.getNumOfGames(),0);
        assertEquals(Cut.getGamesOver().size(),1);

    }


}
