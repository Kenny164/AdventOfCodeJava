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

    private val completionPoints = mapOf(
        ')' to 1,
        ']' to 2,
        '}' to 3,
        '>' to 4
    )

    override fun partOne(): Any? {
        return input.map { it.firstBadCharOrNull() }
            .groupingBy { it }.eachCount()
            .map { (char, count) ->
                errorPoints.getOrDefault(char, 0).toLong() * count }
            .sum()
    }

    private fun String.firstBadCharOrNull(): Char? {
        val stack = ArrayDeque<Char>()
        this.forEach { c ->
            if (c in openToCloseMap.keys) stack.addLast(c)
            else if (stack.isNotEmpty() && openToCloseMap[stack.removeLast()] != c) return c
        }
        return null
    }

    private fun String.incompleteStackOrNull(): ArrayDeque<Char>? {
        val stack = ArrayDeque<Char>()
        this.forEach { c ->
            if (c in openToCloseMap.keys) stack.addLast(c)
            else if (stack.isNotEmpty() && openToCloseMap[stack.removeLast()] != c) return null
        }
        return stack
    }
    override fun partTwo(): Any? {
        return input.mapNotNull { it.incompleteStackOrNull() }
            .map { stack ->
                stack.reversed()
                    .mapNotNull { c ->
                        completionPoints[openToCloseMap[c]]
                    }
                    .fold(0L) { a, b ->
                        (a * 5) + b }
                    }
            .sorted()
            .let { it[it.lastIndex/2] }
    }
}
