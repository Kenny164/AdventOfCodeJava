package com.peeekay.aoc2022.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 03")
internal class Day03Test {

    @Test
    fun `Part1 with test data`() {
        val day = Day03(isTest = true)
        val answer = day.partOne()

        assertEquals(157, answer)
    }

    @Test
    fun `Part2 with test data`() {
        val day = Day03(isTest = true)
        val answer = day.partTwo()

        assertEquals(70, answer)
    }
}