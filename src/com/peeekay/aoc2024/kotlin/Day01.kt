package com.peeekay.aoc2024.kotlin

import kotlin.math.abs

class Day01(val isTest: Boolean = false) : AOCPuzzle(1, isTest) {
    private val pInput = splitStringsToLists(resourceAsList(), 2)

    private fun splitStringsToLists(stringList: List<String>, numLists: Int): List<List<Int>> {
        val outputLists = List(numLists) { mutableListOf<Int>() }
        for (str in stringList) {
            val parts = str.trim().split("\\s+".toRegex())
                for (i in 0 until numLists) {
                    outputLists[i].add(parts[i].toInt())
            }
        }
        return outputLists
    }

    override fun partOne(): Any {
        return pInput[0].sorted()
            .zip(pInput[1].sorted())
            .sumOf { abs(it.second - it.first) }
    }

    override fun partTwo(): Any {
        val similarities = pInput[1].groupingBy { it }.eachCount()
        return pInput[0].sumOf { it * similarities.getOrDefault(it, 0) }
    }

}