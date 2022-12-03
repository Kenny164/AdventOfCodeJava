package com.peeekay.aoc2022.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 01")
internal class Day01Test {

    @Test
    fun `Able to run against test data`() {
        val day = Day01(isTest = true)
        val answer = day.partOne()

        assertEquals("1000", answer)
    }

    @Test
    fun `Able to run against real data`() {
        val day = Day01()
        val answer = day.partOne()

        assertEquals("13399", answer)
    }

    @Test
    fun `Part1 with test data`() {
        val day = Day01()
        val answer = day.partOne()

        assertEquals("13399", answer)
    }

}