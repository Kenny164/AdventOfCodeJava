package com.peeekay.adventOfCodeKotlin

class Day03 : AOCPuzzle(3) {
    private val rawInput = resourceAsList()

    private fun slopePath(pair: Pair<Int,Int>, input: List<String>): List<Char> =
        (input.indices step pair.second).map { rowIdx ->
            val col = rowIdx / pair.second * pair.first
            val line = input[rowIdx]
            line[col % line.length]
        }

    override fun partOne(): Any? {
        return slopePath(Pair(3,1), rawInput).count { it == '#' }
    }

    override fun partTwo(): Any? {
        val slopes: List<Pair<Int,Int>> =
            listOf(Pair(1,1), Pair(3,1), Pair(5,1), Pair(7,1), Pair(1,2))

        return slopes
            .map { slopePath(it, rawInput)
                .count { it == '#' }
                .toLong() }
            .reduce { a, b -> a * b }
    }
}
