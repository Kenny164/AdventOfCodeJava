package com.peeekay.aoc2021.kotlin

class Day09 : AOCPuzzle(9) {
    private val caves: List<IntArray> =
        resourceAsList().map { row ->
            row.map { it.digitToInt() }.toIntArray()
        }

    data class Point2d (val x: Int, val y: Int) {
        fun neighbors(): List<Point2d> =
            listOf(
                Point2d(x , y + 1),
                Point2d(x , y - 1),
                Point2d(x + 1 , y),
                Point2d(x - 1 , y)
            )
    }

    private operator fun List<IntArray>.get(point: Point2d): Int =
        this[point.y][point.x]

    private operator fun List<IntArray>.contains(point: Point2d): Boolean =
        point.y in this.indices && point.x in this[point.y].indices

    private fun List<IntArray>.getLowPoints() =
        this.flatMapIndexed {r, row ->
            row.mapIndexed {c, col ->
                Point2d(c, r).takeIf { point ->
                    point
                        .neighbors()
                        .filter { it in caves }
                        .map { caves[it] }
                        .all { col < it }
                }
            }
        }.filterNotNull()

    override fun partOne(): Any? {
        return caves.getLowPoints().sumOf { caves[it] + 1 }
    }

    override fun partTwo(): Any? {
        return caves.getLowPoints()
            .map { getBasin(it).size }
            .sorted()
            .takeLast(3)
            .reduce { a, b -> a * b}
    }

    private fun getBasin(point: Point2d): List<Point2d> {
        val visited = mutableSetOf(point)
        val queue = mutableListOf(point)

        while (queue.isNotEmpty()) queue.removeFirst()
            .neighbors()
            .filter { it in caves }
            .filterNot { it in visited }
            .filterNot { caves[it] == 9 }
            .let {
                visited.addAll(it)
                queue.addAll(it)
            }
        return visited.toList()
    }

}
