package com.peeekay.aoc2024.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day08Test {
    final Day08 day = new Day08(true);

    @Test
    void part1() {
        Assertions.assertEquals(14, day.part1());
    }

    @Test
    void part2() {
        Assertions.assertEquals(0, day.part2());
    }
}