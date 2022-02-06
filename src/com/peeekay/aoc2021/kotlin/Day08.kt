package com.peeekay.aoc2021.kotlin

class Day08 : AOCPuzzle(8) {
    private val inputs = resourceAsList()
        .map { it.split(" ").filterNot { it == "|" }.map{it.toSet()} }

    override fun partOne(): Any? {
        val simpleDigits = setOf(2, 3, 4, 7)
        return inputs
            .sumOf { it.takeLast(4).count {it.size in simpleDigits }}
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }

}
