package com.peeekay.aoc2022.java;

import com.peeekay.aoc2022.kotlin.AOCPuzzle;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Day07Test {

    @Test
    void partOne() {
        Day07 day = new Day07(true);

        assertEquals(95437L, day.partOne());
    }

    @Test
    void partTwo() {
        Day07 day = new Day07(true);

        assertEquals(24933642L, day.partTwo());
    }
}