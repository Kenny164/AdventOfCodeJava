package com.peeekay.aoc2022.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 04")
internal class Day04Test {

    @Test
    fun `Part1 with test data`() {
        val day = Day04(isTest = true)
        val answer = day.partOne()

        assertEquals(2, answer)
    }

    @Test
    fun `Part2 with test data`() {
        val day = Day04(isTest = true)
        val answer = day.partTwo()

        assertEquals(4, answer)
    }
}