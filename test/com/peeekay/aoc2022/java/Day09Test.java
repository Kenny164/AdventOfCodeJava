package com.peeekay.aoc2022.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day09Test {

    final Day09 day = new Day09(true);
    @Test
    void partOne() {
        assertEquals(13, day.partOne());
    }

    @Test
    void partTwo() {
        assertEquals(0, day.partTwo());
    }
}