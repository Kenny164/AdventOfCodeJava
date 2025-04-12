package com.peeekay.aoc2024.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day06Test {
    final Day06 day = new Day06(true);

    @Test
    void part1() {
        Assertions.assertEquals(41L, day.part1());
    }

    @Test
    void part2() {
        Assertions.assertEquals(6L, day.part2());
    }
}