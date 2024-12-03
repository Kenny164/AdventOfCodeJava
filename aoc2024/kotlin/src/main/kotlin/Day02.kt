package com.peeekay.aoc2024.kotlin

import kotlin.math.absoluteValue

class Day02(val isTest: Boolean = false) : AOCPuzzle(2, isTest) {
    private val pInput = resourceAsList().map { line -> line.split(Regex("\\s+")).map { it.toInt() } }

    override fun partOne(): Any {
        return pInput.count { isSafe(it) }
    }

    override fun partTwo(): Any {
        return pInput.count { report ->
            report.indices.any { toRemove ->
                isSafe(report.filterIndexed { index, _ -> index != toRemove })
            }
        }
    }

    private fun isSafe(report: List<Int>): Boolean = report
        .windowed(2)
        .all { (it[0] - it[1]).absoluteValue in 1..3 }
            && report
        .windowed(2)
        .all { report.windowed(2).map { it[1] - it[0] }.let { d ->
            d.all { it >= 0 } || d.all { it < 0 }
        }
    }

}