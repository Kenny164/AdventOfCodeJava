package com.peeekay.aoc2024.kotlin

import kotlin.math.abs

class Day01(val isTest: Boolean = false) : AOCPuzzle(1, isTest) {
    private val pInput = resourceAsList().fold(Pair(mutableListOf<Int>(), mutableListOf<Int>())) { acc, str ->
        val (first, second) = str.trim().split("\\s+".toRegex()).map { it.toInt() }
        acc.first.add(first)
        acc.second.add(second)
        acc
    }

    override fun partOne(): Any {
        return pInput.first.sorted()
            .zip(pInput.second.sorted())
            .sumOf { abs(it.second - it.first) }
    }

    override fun partTwo(): Any {
        val similarities = pInput.second.groupingBy { it }.eachCount()
        return pInput.first.sumOf { it * similarities.getOrDefault(it, 0) }
    }

}