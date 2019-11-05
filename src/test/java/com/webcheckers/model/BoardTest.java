package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
/**
 * Class to test the board..
 *
 * @author Aaron, Gavin, Mark, Ries, Zach
 */
@Tag("Model-tier")
public class BoardTest {

    /**
     * Parameters used in testing: board size, player objects, and the board (the unit under test).
     */
    private final int BOARD_SIZE = 8;
    private Player whitePlayer;
    private Player redPlayer;
    private Board CuT;

    /**
     * Create the mock player objects.
     */
    @BeforeEach
    public void Setup(){
         whitePlayer = mock(Player.class);
         redPlayer = mock(Player.class);
         CuT = new Board(redPlayer, whitePlayer);

    }

    /**
     * Check to validate that the players have been successfully added.
     */
    @Test
    public void ctor_valid_players(){

        assertNotNull(CuT);
        assertNotNull(CuT.getWhitePlayer());
        assertNotNull(CuT.getRedPlayer());
    }

    /**
     * Tests the setup of the board.
     */
    @Test
    public void test_board_setup(){

        for(int i = 0; i< BOARD_SIZE; i++) {
            assertNotNull(CuT.getRow(i), "row does not exist");
            assertEquals(CuT.getRow(i).length, BOARD_SIZE, "row length is wrong");
        }

        for(int r = 0; r < BOARD_SIZE; r++){
            for(int c = 0; c< BOARD_SIZE; c++){

                if(((c+r)%2) != 0){
                    assertTrue(CuT.getSpace(r,c).isValid(), "White Space mismatch");
                    if(r<3){

                        assertSame(CuT.getSpace(r,c).getPiece().getType(), Piece.Type.SINGLE, "Piece type mismatch");
                        assertSame(CuT.getSpace(r,c).getPiece().getColor(), Piece.Color.WHITE, "Piece color mismatch");
                    }
                    else if(r>4) {
                        assertSame(CuT.getSpace(r,c).getPiece().getType(), Piece.Type.SINGLE, "Piece type mismatch");
                        assertSame(CuT.getSpace(r,c).getPiece().getColor(), Piece.Color.RED, "Piece color mismatch");
                    }
                }
                else{
                    assertFalse(CuT.getSpace(r,c).isValid());
                }

            }
        }
    }






}
