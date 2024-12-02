package com.peeekay.aoc2022.kotlin

class Day04(isTest: Boolean) : AOCPuzzle(4, isTest) {
    private val inp = resourceAsList().map { elfPair ->
        elfPair.split(",").flatMap { eachElf -> eachElf.split("-").map { it.toInt() } }
    }

    override fun partOne(): Any? {
        return inp.count {
            val (a, b, c, d) = it
            (a <= c && b >= d) || (c <= a && d >= b)
        }
    }

    override fun partTwo(): Any? {
        return inp.count {
            val (a, b, c, d) = it
            ((a..b).intersect((c .. d))).any()
        }
    }

}
