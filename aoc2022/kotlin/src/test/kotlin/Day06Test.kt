package com.peeekay.aoc2022.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 06")
internal class Day06Test {

    @Test
    fun `Part1 with test data`() {
        val day = Day06(isTest = true)
        val answer = day.partOne()

        assertEquals(7, answer)
    }

    @Test
    fun `Part2 with test data`() {
        val day = Day06(isTest = true)
        val answer = day.partTwo()

        assertEquals(19, answer)
    }
}