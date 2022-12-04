package com.peeekay.aoc2022.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 02")
internal class Day02Test {

    @Test
    fun `Part1 with test data`() {
        val day = Day02(isTest = true)
        val answer = day.partOne()

        assertEquals(15, answer)
    }

    @Test
    fun `Part2 with test data`() {
        val day = Day02(isTest = true)
        val answer = day.partTwo()

        assertEquals(12, answer)
    }
}