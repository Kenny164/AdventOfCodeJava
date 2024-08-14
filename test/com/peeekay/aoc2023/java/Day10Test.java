package com.peeekay.aoc2023.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day10Test {
    final AOCPuzzle day = new Day10(true);

    @Test
    void part1() {
        day.solve();
        assertEquals(4, day.part1());
    }

    @Test
    void part2() {
        day.solve();
        assertEquals(0L, day.part2());
    }

}