package com.peeekay.aoc2024.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 01")
internal class Day01Test {

    @Test
    fun `part1 test data solve gives correct answer`() {
        val day = Day01(isTest = true)
        val answer = day.partOne()

        assertEquals(11, answer)
    }

    @Test
    fun `part2 test data solve gives correct answer`() {
        val day = Day01(isTest = true)
        val answer = day.partTwo()

        assertEquals(31, answer)
    }

}
