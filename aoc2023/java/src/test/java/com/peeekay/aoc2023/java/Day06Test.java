package com.peeekay.aoc2023.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day06Test {
    final AOCPuzzle day = new Day06(true);

    @Test
    void part1() {
        day.solve();
        assertEquals(288L, day.part1());
    }

    @Test
    void part2() {
        day.solve();
        assertEquals(71503L, day.part2());
    }

}