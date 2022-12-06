package com.peeekay.aoc2022.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 05")
internal class Day05Test {

    @Test
    fun `Part1 with test data`() {
        val day = Day05(isTest = true)
        val answer = day.partOne()

        assertEquals("CMZ", answer)
    }

    @Test
    fun `Part2 with test data`() {
        val day = Day05(isTest = true)
        val answer = day.partTwo()

        assertEquals(4, answer)
    }
}