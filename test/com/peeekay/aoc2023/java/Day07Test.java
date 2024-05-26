package com.peeekay.aoc2023.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day07Test {
    final AOCPuzzle day = new Day07(true);

    @Test
    void part1() {
        day.solve();
        assertEquals(6440L, day.part1());
    }

    @Test
    void part2() {
        day.solve();
        assertEquals(5905L, day.part2());
    }

}