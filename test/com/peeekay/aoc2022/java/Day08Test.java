package com.peeekay.aoc2022.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day08Test {
    final Day08 day = new Day08(true);

    @Test
    void partOne() {
        assertEquals(21L, day.partOne());
    }

    @Test
    void partTwo() {
        assertEquals(8, day.partTwo());
    }
}