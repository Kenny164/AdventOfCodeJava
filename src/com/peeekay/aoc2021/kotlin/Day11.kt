package com.peeekay.aoc2021.kotlin

class Day11 : AOCPuzzle(11) {
    private val cave: Map<Point2d, Int> =
        resourceAsList().flatMapIndexed { r, row ->
            row.mapIndexed { c, col ->
                Point2d(c, r) to col.digitToInt()
            }
        }.toMap()

    data class Point2d (val x: Int, val y: Int) {
        fun neighbors(): List<Point2d> =
            listOf(
                Point2d(x , y + 1),
                Point2d(x , y - 1),
                Point2d(x + 1 , y),
                Point2d(x - 1 , y),
                Point2d(x + 1, y + 1),
                Point2d(x + 1, y - 1),
                Point2d(x - 1 , y + 1),
                Point2d(x - 1 , y - 1)
            )
    }

    private fun Map<Point2d, Int>.steps(): Sequence<Int> = sequence {
        val cave = this@steps.toMutableMap()

        while (true) {
            cave.forEach { (point, energy) -> cave[point] = energy + 1 }

            do {
                val flashersThisRound = cave.filterValues { it > 9 }.keys
                flashersThisRound.forEach { cave[it] = 0 }

                flashersThisRound
                    .flatMap { it.neighbors() }
                    .filter { it in cave && cave[it] != 0 }
                    .forEach { cave[it] = cave.getValue(it) + 1 }
            } while (flashersThisRound.isNotEmpty())

            yield(cave.count { it.value == 0 })
        }
    }

    override fun partOne(): Any? {
        return cave
            .steps()
            .take(100)
            .sum()
    }

    override fun partTwo(): Any? {
        return cave
            .steps()
            .indexOfFirst { it == cave.size } + 1
    }
}
