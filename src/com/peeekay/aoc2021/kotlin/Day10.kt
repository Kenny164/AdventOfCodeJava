package com.peeekay.aoc2021.kotlin

class Day10 : AOCPuzzle(10) {
    val input = resourceAsList()

    private val openToCloseMap = "()[]{}<>".chunked(2).associate { it[0] to it[1] }

    private val errorPoints = mapOf(
        ')' to 3,
        ']' to 57,
        '}' to 1197,
        '>' to 25137
    )

    override fun partOne(): Any? {
        return input.map { it.firstBadCharOrNull() }
            .groupingBy { it }.eachCount()
            .map { (char, count) ->
            errorPoints.getOrDefault(char, 0).toLong() * count }.sum()
    }

    private fun String.firstBadCharOrNull(): Char? {
        val stack = ArrayDeque<Char>()
        this.forEach { c ->
            if (c in openToCloseMap.keys) stack.addLast(c)
            else if (stack.isNotEmpty() && openToCloseMap[stack.removeLast()] != c) return c
        }
        return null
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }

}
