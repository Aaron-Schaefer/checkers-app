package com.webcheckers.model;

import com.webcheckers.appl.PlayerLobby;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.junit.jupiter.api.Assertions.*;

@Tag("Model-tier")
public class BoardViewTest {

    private BoardView CuT1;
    private BoardView CuT2;
    private Board board;
    private Player redPlayer;
    private Player whitePlayer;

    @BeforeEach
    private void setup(){

        redPlayer = mock(Player.class);
        whitePlayer = mock(Player.class);
        board = mock(Board.class);
        CuT1 = new BoardView(board, redPlayer);
        CuT2 = new BoardView(board, whitePlayer);

    }

    @Test
    private void testPlayerRetrival(){

        assertEquals(CuT1.getActivePlayer(), redPlayer);
        assertEquals(CuT2.getActivePlayer(), whitePlayer);


    }

}
