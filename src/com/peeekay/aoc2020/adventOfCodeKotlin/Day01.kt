package com.peeekay.aoc2020.adventOfCodeKotlin

class Day01 : AOCPuzzle(1){
    private val numbers = resourceAsListOfInt()
    private val goal = 2020

    private fun List<Int>.findPairOfSum(goal: Int): Pair<Int, Int>? {
        val complements = associateBy { goal - it }
        return firstNotNullOfOrNull { number ->
            complements[number]?.let { Pair(number, it) }
        }
    }

    private fun List<Int>.findTripleOfSum(goal: Int): Triple<Int, Int, Int>? =
            firstNotNullOfOrNull { x ->
                findPairOfSum(goal - x)?.let { Triple(x, it.first, it.second) }
            }

    override fun partOne(): Any? {
        return numbers.findPairOfSum(goal)?.let { (x , y) -> x * y }
    }

    override fun partTwo(): Any? {
        return numbers.findTripleOfSum(goal)?.let { (x , y, z) -> x * y * z }
    }

}