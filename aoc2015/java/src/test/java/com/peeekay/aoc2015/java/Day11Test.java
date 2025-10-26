package com.peeekay.aoc2015.java;

import com.peeekay.aocCommon.AOCPuzzle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class Day11Test {
    final AOCPuzzle day = new Day11(true);

    @Test
    void test_hasThreeStraight() {
        Assertions.assertTrue(Day11.hasThreeStraight("hijklmmn"));
        Assertions.assertTrue(Day11.hasThreeStraight("asdfabct"));
        Assertions.assertFalse(Day11.hasThreeStraight("hiajkbcn"));
    }

    @Test
    void test_hasConfusingLetters() {
        Assertions.assertTrue(Day11.hasConfusingLetters("hijklmmn"));
        Assertions.assertFalse(Day11.hasConfusingLetters("asdfabct"));
        Assertions.assertTrue(Day11.hasConfusingLetters("hlajkbcn"));
    }

    @Test
    void test_matchingPairs() {
        Assertions.assertEquals(1, Day11.matchingPairs("hijklmmn"));
        Assertions.assertEquals(2, Day11.matchingPairs("asddabbt"));
        Assertions.assertEquals(3, Day11.matchingPairs("hhabbkcc"));
    }

    @Test
    void test_incrementString() {
        Assertions.assertEquals("xy", Day11.incrementString("xx"));
        Assertions.assertEquals("ya", Day11.incrementString("xz"));
        Assertions.assertEquals("hijklmmo", Day11.incrementString("hijklmmn"));
        Assertions.assertEquals("hijklmna", Day11.incrementString("hijklmmz"));
    }

    @Test
    void part1() {
        Assertions.assertEquals("abcdffaa", day.part1());
    }

    @Test
    void part2() {
        Assertions.assertEquals("abcdffbb", day.part2());
    }
}