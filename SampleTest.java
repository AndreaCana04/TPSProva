package com.example.githubtest;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class SampleTest {

    @Test
    public void testAlwaysPasses() {
        assertTrue(true, "Questo test deve sempre passare.");
    }
}
