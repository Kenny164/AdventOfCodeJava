package com.peeekay.aoc2025.java;

import com.peeekay.aocCommon.AOCPuzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class Day02Test {
    final AOCPuzzle day = new Day02(true);

    @Test
    void part1() {
        Assertions.assertEquals(1227775554L, day.part1());
    }

    @Test
    void part2() {
        Assertions.assertEquals(4174379265L, day.part2());
    }
}