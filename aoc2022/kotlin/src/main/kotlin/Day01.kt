package com.peeekay.aoc2022.kotlin

class Day01(val isTest: Boolean = false) : AOCPuzzle(1, isTest) {
    private val inp: List<List<Int>> = resourceAsSplitText("\r\n\r\n").map { elfGroup ->
        elfGroup.lines().map { calories ->
            calories.toInt()
        }
    }

    override fun partOne(): Any {
        return inp.maxOf { it.sum() }
    }

    override fun partTwo(): Any {
        return inp.map { it.sum() }.sortedDescending().take(3).sum()
    }

}
