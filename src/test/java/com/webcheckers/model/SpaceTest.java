package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

@Tag("Model-tier")
public class SpaceTest {
    private boolean isViable;
    private Piece piece;
    private int cellIdx;
    private Space CuT;
    protected Piece testPiece;

    @BeforeEach
    public void Setup(){
        cellIdx = 3;
        isViable = true;
        CuT = new Space(cellIdx, isViable);
        testPiece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        CuT.addPiece(testPiece);
    }


    @Test
    public void ctor_valid_fields(){
        assertNotNull(CuT);
        assertNotNull(CuT.getCellIdx());
        assertNotNull(CuT.isValid());
    }

    @Test
    public void ctor_valid_piece() {
        assert(CuT.getPiece() == testPiece);
    }
}
