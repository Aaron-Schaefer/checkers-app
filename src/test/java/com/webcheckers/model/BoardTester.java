package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("Model-tier")
public class BoardTester {
    private final int BOARD_SIZE = 8;

    private Player whitePlayer;
    private Player redPlayer;
    private Board CuT;

    @BeforeEach
    public void Setup(){
         whitePlayer = mock(Player.class);
         redPlayer = mock(Player.class);
         CuT = new Board(whitePlayer, redPlayer);

    }


    @Test
    public void ctor_valid_players(){

        assertNotNull(CuT);
        assertNotNull(CuT.getWhitePlayer());
        assertNotNull(CuT.getRedPlayer());
    }

    @Test
    public void test_board_setup(){

        for(int i = 0; i< BOARD_SIZE; i++) {
            assertNotNull(CuT.getRow(1));
            assertEquals(CuT.getRow(1).length, BOARD_SIZE);
        }

        for(int r = 0; r < BOARD_SIZE; r++){
            for(int c = 0; c< BOARD_SIZE; c++){

                if(((c+r)%2) != 0){
                    assertTrue(CuT.getSpace(r,c).isValid());
                    if(r<3){

                        assertSame(CuT.getSpace(r,c).getPiece().getType(), Piece.Type.SINGLE);
                        assertSame(CuT.getSpace(r,c).getPiece().getColor(), Piece.Color.WHITE);
                    }
                    else if(r>4) {
                        assertSame(CuT.getSpace(r,c).getPiece().getType(), Piece.Type.SINGLE);
                        assertSame(CuT.getSpace(r,c).getPiece().getColor(), Piece.Color.RED);
                    }
                }
                else{
                    assertFalse(CuT.getSpace(r,c).isValid());
                }

            }
        }
    }






}
