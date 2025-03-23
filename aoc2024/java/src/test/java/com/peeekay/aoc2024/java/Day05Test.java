package com.peeekay.aoc2024.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day05Test {
    final Day05 day = new Day05(true);

    @Test
    void part1() {
        Assertions.assertEquals(143, day.part1());
    }

    @Test
    void part2() {
        Assertions.assertEquals(123, day.part2());
    }
}