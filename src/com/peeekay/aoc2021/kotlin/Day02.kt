package com.peeekay.aoc2021.kotlin

class Day02 : AOCPuzzle(2) {
    private val commands = resourceAsList().map { Command.of(it) }

    private class Command(val name: String, val amount: Int) {
        companion object {
            fun of(input: String) = input.split(" ").let { Command(it.first(), it.last().toInt()) }
        }
    }

    private val ops = commands
        .map {
            val cmdAmt = it.amount
            when (it.name){
                "forward" -> Pair(+cmdAmt, 0)
                "up" -> Pair(0,-cmdAmt)
                "down" -> Pair(0, +cmdAmt)
                else -> throw error("no command type")
            }
        }

    override fun partOne(): Any? {
        return ops.reduce { (h, d), (x, y) -> Pair(h + x, d + y) }.let {it.first * it.second}
    }

    override fun partTwo(): Any? {
        val (h, d, _) = ops
            .fold(Triple(0, 0, 0)
            ) { (h, d, a), (x, y) -> Triple(h + x, d + a * x, a + y) }
        return h * d
    }

}