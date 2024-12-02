package com.peeekay.aoc2022.kotlin

class Day02(val isTest: Boolean = false) : AOCPuzzle(2, isTest) {
    private val inp = resourceAsList()

    private val p1ScoreLookup: Map<String, Int> = mapOf(
        "A X" to 1 + 3,
        "A Y" to 2 + 6,
        "A Z" to 3 + 0,
        "B X" to 1 + 0,
        "B Y" to 2 + 3,
        "B Z" to 3 + 6,
        "C X" to 1 + 6,
        "C Y" to 2 + 0,
        "C Z" to 3 + 3,
    )
    private val p2ScoreLookup: Map<String, Int> = mapOf(
        "A X" to 3 + 0,
        "A Y" to 1 + 3,
        "A Z" to 2 + 6,
        "B X" to 1 + 0,
        "B Y" to 2 + 3,
        "B Z" to 3 + 6,
        "C X" to 2 + 0,
        "C Y" to 3 + 3,
        "C Z" to 1 + 6,
    )

    override fun partOne(): Any? {
        return inp.sumOf { p1ScoreLookup.getOrDefault(it, 0) }
    }

    override fun partTwo(): Any? {
        return inp.sumOf { p2ScoreLookup.getOrDefault(it, 0) }
    }

}
