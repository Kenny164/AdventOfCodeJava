package com.peeekay.aoc2022.kotlin

class Day06(val isTest: Boolean = false) : AOCPuzzle(6, isTest) {
    private val inp = resourceAsText()

    private fun String.findUniqueWindow(windowSize: Int) =
        this.windowedSequence(windowSize, 1)
            .withIndex()
            .first { it.value.toSet().size == windowSize }
            .let { it.index + windowSize }

    override fun partOne(): Any? = inp.findUniqueWindow(4)

    override fun partTwo(): Any? = inp.findUniqueWindow(14)

}
