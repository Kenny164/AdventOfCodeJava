package com.peeekay.aoc2023.java;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class Day01Test {
    final Day01 day = new Day01(true);

    @Test
    void part1() {
        day.solve();
        assertEquals(142, day.part1());
    }

    @Test
    void part2() {
        List<String> secondTest = List.of("""
                two1nine
                eightwothree
                abcone2threexyz
                xtwone3four
                4nineeightseven2
                zoneight234
                7pqrstsixteen
                """.split("\n"));

        day.solve(secondTest);

        assertEquals(281, day.part2());
    }
}