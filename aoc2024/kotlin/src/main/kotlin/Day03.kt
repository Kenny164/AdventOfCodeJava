package com.peeekay.aoc2024.kotlin

class Day03(val isTest: Boolean = false) : AOCPuzzle(3, isTest) {
    private val pInput = resourceAsText()

    override fun partOne(): Any {
        return Regex("""mul\((\d+),(\d+)\)""").findAll(pInput)
            .map { matchResult ->
                matchResult.groupValues[1].toInt() * matchResult.groupValues[2].toInt()}
            .sum()
    }

    override fun partTwo(): Any {
        return 161
    }

}