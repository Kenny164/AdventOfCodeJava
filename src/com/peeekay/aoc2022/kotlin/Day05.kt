package com.peeekay.aoc2022.kotlin

class Day05(isTest: Boolean) : AOCPuzzle(5, isTest) {
    private val inp = resourceAsSplitText("\r\n\r\n").take(2)

    private val inpStacks = inp[0].lines().map { line ->
        line.mapIndexedNotNull { index, c ->
            if (index % 4 == 1) c else null
        }
    }.reversed().drop(1).let { rawStacks ->
        val stacks = List(rawStacks.maxOf { it.size }) { ArrayDeque<Char>() }
        for (row in rawStacks) {
            for ((index, c) in row.withIndex()) {
                if (c.isLetter()) stacks[index].addLast(c)
            }
        }
        stacks
    }

    private fun List<ArrayDeque<Char>>.getTops() = this.map { it.last() }.joinToString("")

    private val inpMoves = inp[1].lines().map { line ->
        line.split(" ").let { Triple<Int, Int, Int>(it[1].toInt(), it[3].toInt(), it[5].toInt()) }
    }

    private fun List<ArrayDeque<Char>>.applyMoves(moves: List<Triple<Int, Int, Int>>): List<ArrayDeque<Char>> {
        moves.forEach { move ->
            repeat(move.first) { this[move.third - 1].addLast(this[move.second - 1].removeLast()) }
        }
        return this
    }

    override fun partOne(): Any? {
        return inpStacks.applyMoves(inpMoves).getTops()
    }

    override fun partTwo(): Any? {
        return inpMoves
    }

}
