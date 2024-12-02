package com.peeekay.aoc2021.kotlin

class Day01 : AOCPuzzle(1){
    private val numbers = resourceAsListOfInt()

    override fun partOne(): Any? {
        return numbers
            .zipWithNext()
            .count {it.first < it.second}
    }

    override fun partTwo(): Any? {
        return numbers
            .windowed(3,1) { it.sum() }
            .zipWithNext()
            .count {it.first < it.second }
    }

}