package com.peeekay.aoc2021.kotlin

class Day14 : AOCPuzzle(14) {
    private val rawInput = resourceAsList()
    private val template = rawInput.first()
    private val rules = rawInput
        .takeLastWhile { it.isNotEmpty() }
        .map { s -> s.split(" -> ")}
        .associate {it[0] to it[1].first()}
    private val polyDoubleCounts = template
        .windowed(2).groupingBy { it }.eachCount().mapValues { it.value.toLong() }

    private fun Map<String, Long>.processPolymerDoubles(times: Int) =
        (1 .. times).fold(this) { accPolyDoubles, _ ->
        var tmpPolyDoubleCounts = mutableMapOf<String, Long>().withDefault { 0L }
        for ((polyPair, polyCount) in accPolyDoubles.entries) {
            val newPolyResult = rules[polyPair] ?: continue
            val newPair1 = polyPair[0].toString() + newPolyResult
            val newPair2 = newPolyResult + polyPair[1].toString()
            tmpPolyDoubleCounts[newPair1] = tmpPolyDoubleCounts.getValue(newPair1) + polyCount
            tmpPolyDoubleCounts[newPair2] = tmpPolyDoubleCounts.getValue(newPair2) + polyCount
        }
        return@fold tmpPolyDoubleCounts
    }

    private fun Map<String, Long>.extractSingles() =
        this
            .map { it.key.first() to it.value }
            .groupBy({ it.first }, { it.second })
            .mapValues { it.value.sum() + if (it.key == template.last()) 1 else 0 }

    override fun partOne(): Any? {
        return polyDoubleCounts
            .processPolymerDoubles(10)
            .extractSingles()
            .values
            .sorted()
            .let { it.last() - it.first() }
    }
    override fun partTwo(): Any? {
        return polyDoubleCounts
            .processPolymerDoubles(40)
            .extractSingles()
            .values
            .sorted()
            .let { it.last() - it.first() }
    }

}
