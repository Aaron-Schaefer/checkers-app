package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
/**
 * Class to test the Position.
 *
 * @author Aaron, Gavin, Mark, Ries, Zach
 */
@Tag("Model-tier")
public class PositionTest {
    private int row;
    private int cell;
    private Position CuT;

    /**
     * Test the setup of Position
     */
    @BeforeEach
    public void setup() {
        row = 3;
        cell = 4;
        CuT = new Position(3, 4);
    }

    /**
     * Tests if the field is valid.
     */
    @Test
    public void ctor_valid_fields(){
        assertNotNull(CuT);
        assertNotNull(CuT.getRow());
        assertNotNull(CuT.getCell());
    }
}