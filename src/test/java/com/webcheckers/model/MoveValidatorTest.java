package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

/**
 * Class to test the MoveValidator.
 *
 * @author An Chang (Mark).
 */
public class MoveValidatorTest {

    private Position p1;
    private Position p2;
    private Player redPlayer;
    private Player whitePlayer;
    private Game game;
    private Move move;

    /**
     * Setup before each test.
     */
    @BeforeEach
    public void Setup(){

        redPlayer = new Player("1");
        whitePlayer = new Player("2");
        p1 = new Position(1,2);
        p2 = new Position(1,2);
        game = new Game(redPlayer,whitePlayer,1);
        move = new Move(p1,p2);

    }

    /**
     * Test if the move was made correctly.
     */
    @Test
    public void testMove(){

        assertEquals(MoveValidator.validateMove(game,move), MoveValidator.MoveValidation.OCCUPIED);

    }

}
