package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Class to test the BoardView
 *
 * @author An Chang (Mark).
 */

@Tag("Model-tier")
public class BoardViewTest {

    /**
     * Fields used to test board view.
     */
    private BoardView CuT1;
    private BoardView CuT2;
    private Board board;
    private Player redPlayer;
    private Player whitePlayer;

    /**
     * Setup before each test.
     */
    @BeforeEach
    private void Setup(){

        redPlayer = mock(Player.class);
        whitePlayer = mock(Player.class);
        board = mock(Board.class);
        CuT1 = new BoardView(board, redPlayer);
        CuT2 = new BoardView(board, whitePlayer);

    }

    /**
     * Test the player retrival.
     */
    @Test
    private void testPlayerRetrival(){

        assertEquals(CuT1.getActivePlayer(), redPlayer);
        assertEquals(CuT2.getActivePlayer(), whitePlayer);


    }


}
