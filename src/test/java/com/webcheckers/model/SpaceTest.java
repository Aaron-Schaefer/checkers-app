package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
/**
 * Class to test the spaces.
 *
 * @author Aaron, Gavin, Mark, Ries, Zach
 */
@Tag("Model-tier")
public class SpaceTest {
    /**
     * Parameters used in the testing: Boolean for if the space is viable, the index of the space, the space under
     * test, as well as a test piece.
     */
    private boolean isViable;
    private int cellIdx;
    private Space CuT;
    protected Piece testPiece;

    /**
     * Tests the setup of the space.
     */
    @BeforeEach
    public void Setup(){
        cellIdx = 3;
        isViable = true;
        CuT = new Space(cellIdx, isViable);
        testPiece = new Piece(Piece.Type.SINGLE, Piece.Color.RED);
        CuT.addPiece(testPiece);
    }

    /**
     * Tests if the field is valid.
     */
    @Test
    public void ctor_valid_fields(){
        assertNotNull(CuT);
        assertNotNull(CuT.getCellIdx());
        assertNotNull(CuT.isValid());
    }

    /**
     * Tests if the piece is placed on the space correctly.
     */
    @Test
    public void ctor_valid_piece() {
        assert(CuT.getPiece() == testPiece);
    }
}
