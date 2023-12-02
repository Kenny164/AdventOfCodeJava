package com.peeekay.aoc2022.java;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Day09Test {

    final Day09 day = new Day09(true);
    @Test
    void partOne() {
        assertEquals(13, day.partOne());
    }

    @Test
    void partTwo() {
        List<String> moreTestData = Arrays.asList("""
R 5
U 8
L 8
D 3
R 17
D 10
L 25
U 20
""".split("\n"));
        assertEquals(36, day.solve(moreTestData, 9));
    }
}