package com.peeekay.aoc2023.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day09Test {
    final AOCPuzzle day = new Day09(true);

    @Test
    void part1() {
        day.solve();
        assertEquals(114, day.part1());
    }

    @Test
    void part2() {
        day.solve();
        assertEquals(2, day.part2());
    }

}