package com.peeekay.aoc2022.kotlin

class Day06(val isTest: Boolean = false) : AOCPuzzle(6, isTest) {
    private val inp = resourceAsText()

    override fun partOne(): Any? {
        return inp.windowedSequence(4, 1).withIndex().first { it.value.toSet().size == 4 }.let { it.index + 4 }
    }

    override fun partTwo(): Any? {
        return inp.windowedSequence(14, 1).withIndex().first { it.value.toSet().size == 14 }.let { it.index + 14 }
    }
}
