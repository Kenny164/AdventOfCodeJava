package com.peeekay.aoc2023.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day02Test {
    final AOCPuzzle day = new Day02(true);

    @Test
    void part1() {
        day.solve();
        assertEquals(8, day.part1());
    }

    @Test
    void part2() {
        day.solve();
        assertEquals(2286, day.part2());
    }

}