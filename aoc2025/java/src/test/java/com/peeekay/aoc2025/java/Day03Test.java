package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day03Test {
    final AOCPuzzle day = new Day03(true);

    @Test
    void part1() {
        Assertions.assertEquals(357L, day.part1());
    }

    @Test
    void part2() {
        Assertions.assertEquals(3121910778619L, day.part2());
    }
}