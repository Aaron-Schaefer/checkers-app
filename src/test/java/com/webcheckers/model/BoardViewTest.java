package com.webcheckers.model;

import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

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
     * Cannot be ran with mock objects, as the mock objects will create null pointer exceptions.
     */
    @BeforeEach
    public void Setup(){

        redPlayer = new Player("1");
        whitePlayer = new Player("2");
        board = new Board(redPlayer,whitePlayer);
        CuT1 = new BoardView(board, redPlayer);
        CuT2 = new BoardView(board, whitePlayer);

    }

    /**
     * Test the iterator.
     */
    @Test
    public void testIterator(){

        assertNotNull(CuT1.iterator());
        assertNotNull(CuT2.iterator());

    }


}
