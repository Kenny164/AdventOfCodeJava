package com.peeekay.aoc2021.kotlin

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Day 16")
internal class Day16Test {

    @Test
    fun `able parse hex to padded binary`() {
        assertEquals("110100101111111000101000",
            Day16().inputToBinIterator("D2FE28").asSequence().toList().joinToString("") )

    }

    @Test
    fun `Part1 example of a simple literal packet`() {
        val answer = Day16().parsePacketsRecurse(Day16().inputToBinIterator("D2FE28"))

        assertEquals(2021L, answer)
    }

    @Test
    fun `Part2 example of a simple sum of 1 + 2`() {
        val answer = Day16().parsePacketsRecurse(Day16().inputToBinIterator("C200B40A82"))

        assertEquals(3L, answer)
    }

    @Test
    fun `Part2 example of a more complex op 6 * 9`() {
        val answer = Day16().parsePacketsRecurse(Day16().inputToBinIterator("04005AC33890"))

        assertEquals(54L, answer)
    }
    @Test
    fun `Part2 example of a more complex op min of 7 8 9`() {
        val answer = Day16().parsePacketsRecurse(Day16().inputToBinIterator("880086C3E88112"))

        assertEquals(7L, answer)
    }
    @Test
    fun `Part2 example of a more complex op max of 7 8 9`() {
        val answer = Day16().parsePacketsRecurse(Day16().inputToBinIterator("CE00C43D881120"))

        assertEquals(9L, answer)
    }

    @Test
    fun `Part2 example of a more complex op 5 lt 15`() {
        val answer = Day16().parsePacketsRecurse(Day16().inputToBinIterator("D8005AC2A8F0"))

        assertEquals(1L, answer)
    }

    @Test
    fun `Part2 example of a more complex op 5 not gt 15`() {
        val answer = Day16().parsePacketsRecurse(Day16().inputToBinIterator("F600BC2D8F"))

        assertEquals(0L, answer)
    }

    @Test
    fun `Part2 example of a more complex op 5 ne 15`() {
        val answer = Day16().parsePacketsRecurse(Day16().inputToBinIterator("9C005AC2F8F0"))

        assertEquals(0L, answer)
    }

    @Test
    fun `Part2 example of a more complex op max of 1+3=2*2`() {
        val answer = Day16().parsePacketsRecurse(Day16().inputToBinIterator("9C0141080250320F1802104A08"))

        assertEquals(1L, answer)
    }
}