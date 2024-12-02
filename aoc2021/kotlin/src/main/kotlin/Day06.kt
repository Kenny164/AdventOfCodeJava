package com.peeekay.aoc2021.kotlin

class Day06 : AOCPuzzle(6) {
    private val input: String = resourceAsString()
    private val input_test: String = resourceAsString_Test()

    private fun parseInput(input: String): LongArray =
        LongArray(9).apply {
            input.split(",").map { it.toInt() }.forEach { this[it] += 1L }
        }

    private fun cycleDays(days: Int): LongArray {
        val fishyCounter = parseInput(input).toCollection(ArrayDeque<Long>())
        repeat(days) {
            val expired = fishyCounter.removeFirst()
            fishyCounter.add(expired)
            fishyCounter[6] += expired
        }
        return fishyCounter.toLongArray()
    }

    override fun partOne(): Any? {
        return cycleDays(80).sum()
    }

    override fun partTwo(): Any? {
        return cycleDays(256).sum()
    }

}
