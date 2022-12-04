package com.peeekay.aoc2022.kotlin

class Day02(val isTest: Boolean = false) : AOCPuzzle(2, isTest) {
    private val inp = resourceAsList()

    private val scoreLookup: Map<String, Int> = mapOf(
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

    override fun partOne(): Any? {
        return inp.map { scoreLookup.getOrDefault(it, 0) }.sum()
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }

}
