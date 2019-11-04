package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
/**
 * Class to test the player.
 *
 * @author Aaron, Gavin, Mark, Ries, Zach
 */
@Tag("Model-tier")
public class PlayerTest {
    /**
     * Tests if the player object successfully recieved the name.
     */
    @Test
    public void ctor(){
        Player CuT = new Player("test");
        assertEquals("test", CuT.getName());
    }

    /**
     * Tetsts if the name is null when assigned null.
     */
    @Test
    public void ctor_nameNull(){
        Player CuT = new Player(null);
        assertNull(CuT.getName());
    }

    /**
     * Tests if two players are equal (when they should be).
     */
    @Test
    public void equalsPlayer(){
        Player test1 = new Player("test");
        Player test2 = new Player("test");
        assertTrue(test1.equals(test2));
    }
}
