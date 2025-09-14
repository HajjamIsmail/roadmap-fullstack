package com.example;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class MainTest {

    @Test
    public void testAdd() {
        assertEquals(5, Main.add(2, 3));
    }
}
