package com.peeekay.adventOfCodeKotlin

class Day05 : AOCPuzzle(5) {
    private val rawInput = resourceAsList()

    private fun strToSeatId(boardingNum: String): Int {
        return boardingNum
            .map {
            when (it) {
                'F', 'L' -> '0'
                'B', 'R' -> '1'
                else -> it
            }}
            .joinToString("").toInt(2)
    }

    private val seats = rawInput.map { strToSeatId(it) }

    override fun partOne(): Any? {
        //return strToSeatId("BFFFBBFRRR")
        return seats.maxOrNull()
    }

    override fun partTwo(): Any? {
        return seats
            .sorted()
            .zipWithNext()
            .filter {it.first + 1 != it.second}
            .first().first + 1
    }


}
