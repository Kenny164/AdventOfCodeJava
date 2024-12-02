package com.peeekay.aoc2023.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day03Test {
    final AOCPuzzle day = new Day03(true);

    @Test
    void part1() {
        day.solve();
        assertEquals(4361, day.part1());
    }

    @Test
    void part2() {
        day.solve();
        assertEquals(467835L, day.part2());
    }

}