package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
/**
 * Class to test the piece.
 *
 * @author Aaron, Gavin, Mark, Ries, Zach
 */
@Tag("Model-tier")
public class PieceTest {
    private Piece.Type type;
    private Piece.Color color;
    private Piece CuT;

    @BeforeEach
    public void setup() {
        type = Piece.Type.KING;
        color = Piece.Color.RED;
        CuT = new Piece(type, color);
    }

    /**
     * Tests if the field is valid.
     */
    @Test
    public void ctor_valid_fields(){
        assertNotNull(CuT);
        assertNotNull(CuT.getColor());
        assertNotNull(CuT.getType());
    }

    /**
     * Tests if the string of the piece is formatted correctly.
     */
    @Test
    public void ctor_valid_toString() {
        assert(CuT.toString() == ("" + color + ""));
    }
}