package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day01Test {
    final AOCPuzzle day = new Day01(true);

    @Test
    void part1() {
        Assertions.assertEquals(3L, day.part1());
    }

    @Test
    void part2() {
        Assertions.assertEquals(2, day.part2());
    }
}