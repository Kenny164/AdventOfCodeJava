package com.peeekay.aoc2024.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day07Test {
    final Day07 day = new Day07(true);

    @Test
    void part1() {
        Assertions.assertEquals(3749L, day.part1());
    }

    @Test
    void part2() {
        Assertions.assertEquals(11387L, day.part2());
    }
}