package com.webcheckers.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.mockito.internal.verification.MockAwareVerificationMode;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
/**
 * Class to test the Move.
 *
 * @author Aaron, Gavin, Mark, Ries, Zach
 */
@Tag("Model-tier")
public class MoveTest {
    private Position start;
    private Position end;
    private MoveValidator.MoveValidation validState;
    private MoveValidator.MoveValidation invalidState;
    private Move CuT;

    /**
     * Tests the setup of Move
     */
    @BeforeEach
    public void Setup() {
        start = new Position(2,5);
        end = new Position(3,6);
        CuT = new Move(start, end);
        validState = MoveValidator.MoveValidation.VALID;
        invalidState = MoveValidator.MoveValidation.OCCUPIED;
    }

    /**
     * Tests if fields are valid
     */
    @Test
    public void ctor_valid_fields() {
        assertNotNull(CuT);
        assertNotNull(CuT.getStart());
        assertNotNull(CuT.getEnd());
    }

    @Test
    public void ctor_valid_state_change() {
        CuT.setValidState(MoveValidator.MoveValidation.OCCUPIED);
        assert(CuT.getValidState() == invalidState);
    }
}