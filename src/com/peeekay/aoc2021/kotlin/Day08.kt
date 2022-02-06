package com.peeekay.aoc2021.kotlin

class Day08 : AOCPuzzle(8) {
    private val inputs = resourceAsList()
        .map { line -> line.split(" ").filterNot { it == "|" }.map{it.toSet()} }

    override fun partOne(): Any? {
        val simpleDigits = setOf(2, 3, 4, 7)
        return inputs.sumOf { line ->
            line.takeLast(4).count { it.size in simpleDigits }}
    }

    override fun partTwo(): Any? {
        return inputs.sumOf{ line ->
            line.groupBy() { it.size }
                .let {
                    val number = Array(10) { setOf<Char>() }
                    number[1] = it[2]!!.first()
                    number[4] = it[4]!!.first()
                    number[7] = it[3]!!.first()
                    number[8] = it[7]!!.first()
                    number[6] = it[6]!!.first { number[8] == number[1] + it }
                    number[5] = it[5]!!.first { number[8] != number[6] + it }
                    number[2] = it[5]!!.first { number[8] == number[5] + it }
                    number[9] = it[6]!!.first { number[8] != number[4] + it }
                    number[0] = it[6]!!.first { number[8] == number[5] + it }
                    number[3] = it[5]!!.first { it !in number }
                    line.takeLast(4).map { number.indexOf(it) }.joinToString("").toInt()
                }
        }

    }

}
