package com.peeekay.aoc2021.kotlin

import kotlin.math.absoluteValue
import kotlin.math.roundToInt

class Day07 : AOCPuzzle(7) {
    private val crabs = resourceAsString()
        .split(",")
        .map { it.toInt() }

    private val crabCounter = crabs
        .groupingBy { it }
        .eachCount()

    private fun List<Int>.median() =
        this.sorted().let { (it[it.size/2] + it[(it.size - 1)/2])/2 }

    override fun partOne(): Any? {
        val target = crabs.median()
        return crabCounter
            .map { (crab, crabCount) ->
                ((target - crab) * crabCount).absoluteValue
            }.sum()
    }

    override fun partTwo(): Any? {
        val target = crabs.average().roundToInt() - 1
        return crabCounter
            .map { (crab, crabCount) ->
                val dist = (target - crab).absoluteValue
                (dist * (dist + 1) / 2) * crabCount
            }.sum()
    }
}
