package com.peeekay.aoc2022.kotlin

class Day03(isTest: Boolean) : AOCPuzzle(3, isTest) {
    private val inp = resourceAsList().map{ rucksack ->
        listOf(
        rucksack.substring(0 until rucksack.length / 2 ).toSet(),
        rucksack.substring(rucksack.length / 2 ).toSet(),
        )
    }//.also { println((it)) }

    private fun Char.getPriority() =
            when(this.isUpperCase()) {
                true -> (this - 'A') + 27
                false -> (this - 'a') + 1
            }

    override fun partOne(): Any? {
        return inp.flatMap { it[0] intersect it[1] }.sumOf { it.getPriority() }
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }

}
