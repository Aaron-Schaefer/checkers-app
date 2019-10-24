package com.webcheckers.model;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("Model-tier")
public class PlayerTest {
    @Test
    public void ctor(){
        Player CuT = new Player("test");
        assertEquals("test", CuT.getName());
    }

    @Test
    public void ctor_nameNull(){
        Player CuT = new Player(null);
        assertNull(CuT.getName());
    }

    @Test
    public void equalsPlayer(){
        Player test1 = new Player("test");
        Player test2 = new Player("test");
        assertTrue(test1.equals(test2));
    }
}
