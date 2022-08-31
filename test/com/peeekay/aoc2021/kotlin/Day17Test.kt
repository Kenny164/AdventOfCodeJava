package com.peeekay.aoc2021.kotlin

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day17Test {

    @Test
    fun `can parse Target from String`() {
        val testString = "target area: x=20..30, y=-10..-5"
        val testTarget = Day17.Target(x=20..30, y=-10..-5)

        assertEquals(testTarget, Day17.Target.of(testString))
    }

    @Test
    fun `can detect when in target area`() {
        val testTarget = Day17.Target(x=20..30, y=-10..-5)

        assertTrue(testTarget.inTargetArea(21,-8))
        assertFalse(testTarget.inTargetArea(18,-8))
        assertFalse(testTarget.inTargetArea(21,3))
    }

    @Test
    fun `can detect when beyond target area`() {
        val testTarget = Day17.Target(x=20..30, y=-10..-5)

        assertTrue(testTarget.beyondTargetArea(21,-12))
        assertTrue(testTarget.beyondTargetArea(35,-8))
        assertTrue(testTarget.beyondTargetArea(37,-22))
        assertFalse(testTarget.inTargetArea(18,-8))
        assertFalse(testTarget.inTargetArea(21,3))
    }

}