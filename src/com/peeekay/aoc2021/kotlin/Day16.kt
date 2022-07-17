package com.peeekay.aoc2021.kotlin

class Day16 : AOCPuzzle(16){
    private val rawInput = resourceAsString_Test()
    private val cave = rawInput
        .map { it.digitToInt(16).toString(2).padStart(4,'0') }
        .flatMap { it.toList() }
        .iterator()

    private fun <T> Iterator<T>.takeNext(i: Int): String {
        return (1..i).map { this.next() }.joinToString("")
    }

    override fun partOne(): Any? {
        val version = cave.takeNext(3).toInt(2)
        val packetType = cave.takeNext(3).toInt(2)
        return packetType
    }

    override fun partTwo(): Any? {
        return 0
    }
}
