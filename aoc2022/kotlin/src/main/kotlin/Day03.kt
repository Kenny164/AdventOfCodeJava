package com.peeekay.aoc2022.kotlin

class Day03(isTest: Boolean) : AOCPuzzle(3, isTest) {
    private val inp = resourceAsList()

    private fun List<String>.getHalves() = this.map { rucksack ->
        listOf(
            rucksack.substring(0 until rucksack.length / 2).toSet(), rucksack.substring(rucksack.length / 2).toSet()
        )
    }

    private fun Char.getPriority() = when (this.isUpperCase()) {
        true -> (this - 'A') + 27
        false -> (this - 'a') + 1
    }

    override fun partOne(): Any? {
        return inp.getHalves().flatMap { it[0] intersect it[1] }.sumOf { it.getPriority() }
    }

    override fun partTwo(): Any? {
        return inp.chunked(3).map {
            val (a, b, c) = it
            (a.toSet() intersect b.toSet() intersect c.toSet()).single()
        }.sumOf { it.getPriority() }
    }

}
