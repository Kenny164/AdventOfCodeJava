package com.peeekay.aoc2024.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day09Test {
    final Day09 day = new Day09(true);

    @Test
    void part1() {
        Assertions.assertEquals(1928L, day.part1());
    }

    @Test
    void part2() {
        Assertions.assertEquals(0, day.part2());
    }
}