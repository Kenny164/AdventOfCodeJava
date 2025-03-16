package com.peeekay.aoc2024.java;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day04Test {
    final Day04 day = new Day04(true);

    @Test
    void part1() {
        day.solve();
        Assertions.assertEquals(18, day.part1());
    }

    @Test
    void part2() {
        day.solve();
        Assertions.assertEquals(9, day.part2());
    }
}