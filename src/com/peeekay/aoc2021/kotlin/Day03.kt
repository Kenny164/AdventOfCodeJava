package com.peeekay.aoc2021.kotlin

class Day03 : AOCPuzzle(3) {
    private fun getDiagGreeks(): Pair<Int, Int> {
        val input = resourceAsList()
        val gamma = input.first().indices.map { column ->
            if (input.count { it[column] == '1' } > input.size / 2) '1' else '0'
        }.joinToString("")

        val epsilon = gamma.map { if(it == '1') '0' else '1' }.joinToString("")

        return Pair(gamma.toInt(2), epsilon.toInt(2))
    }

    override fun partOne(): Any? {
        val (gamma, epsilon) = getDiagGreeks()
        return gamma * epsilon
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }

}
