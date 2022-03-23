package com.peeekay.aoc2021.kotlin

class Day14 : AOCPuzzle(14) {
    private val rawInput = resourceAsList_Test()
    private val template = rawInput.first()
    private val rules = rawInput
        .takeLastWhile { it.isNotEmpty() }
        .map { s -> s.split(" -> ")}
        .associate {it[0] to it[1].first()}

    override fun partOne(): Any? {
        var tempMap = template.windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }.toMutableMap()
        repeat(2) {
            tempMap.forEach { (pair, count) ->
                val newPolyResult = rules.getValue(pair)
                tempMap[(pair[0].toString() + newPolyResult)] = tempMap.getOrDefault((pair[0].toString() + newPolyResult), 0) + 1
                tempMap[(newPolyResult + pair[0].toString())] = tempMap.getOrDefault(newPolyResult + pair[0].toString(), 0) + 1
            }
        }
        return 0
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }

}
