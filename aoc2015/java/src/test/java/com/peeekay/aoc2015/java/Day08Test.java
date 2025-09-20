package com.peeekay.aoc2015.java;

import com.peeekay.aocCommon.AOCPuzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day08Test {
    final AOCPuzzle day = new Day08(true);

    @Test
    void part1() {
        Assertions.assertEquals(12, day.part1());
    }

    @Test
    void part2() {
        Assertions.assertEquals(19, day.part2());
    }
}