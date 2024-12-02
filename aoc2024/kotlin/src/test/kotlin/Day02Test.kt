package com.peeekay.aoc2024.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 02")
internal class Day02Test {

    @Test
    fun `part1 test data solve gives correct answer`() {
        val day = Day02(isTest = true)
        val answer = day.partOne()

        assertEquals(2, answer)
    }

    @Test
    fun `part2 test data solve gives correct answer`() {
        val day = Day02(isTest = true)
        val answer = day.partTwo()

        assertEquals(0, answer)
    }

}
