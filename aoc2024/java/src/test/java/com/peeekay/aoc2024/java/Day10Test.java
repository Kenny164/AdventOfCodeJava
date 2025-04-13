package com.peeekay.aoc2024.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day10Test {
    final Day10 day = new Day10(true);

    @Test
    void part1() {
        Assertions.assertEquals(14, day.part1());
    }

    @Test
    void part2() {
        Assertions.assertEquals(0, day.part2());
    }
}