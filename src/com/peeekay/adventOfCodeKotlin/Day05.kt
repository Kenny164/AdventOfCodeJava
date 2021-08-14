package com.peeekay.adventOfCodeKotlin

class Day05 : AOCPuzzle(5) {
    val rawInput = resourceAsList()

    private fun strToSeatId(boardingNum: String): Int {
        val result = boardingNum.map {
            when (it) {
                'F', 'L' -> '0'
                'B', 'R' -> '1'
                else -> it
            }
        }.joinToString("").toInt(2)
        return result
    }

    override fun partOne(): Any? {
        //return strToSeatId("BFFFBBFRRR")
        return rawInput.map { strToSeatId(it) }.maxOrNull()
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }


}
