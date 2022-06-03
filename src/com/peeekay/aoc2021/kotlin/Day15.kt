package com.peeekay.aoc2021.kotlin

import java.util.*

class Day15 : AOCPuzzle(15){
    private val rawInput = resourceAsList()
    private val cave = rawInput
        .map { row ->
            row.toCharArray()
                .map{it.digitToInt()}
        }

    private fun calcRisk(multiplier: Int): Int {
        val pq = PriorityQueue<Triple<Int, Int, Int>> { a, b ->
            a.first - b.first
        }
        val maxX = cave[0].size * multiplier
        val maxY = cave.size * multiplier
        val dest = Pair(maxY - 1, maxX - 1)
        pq.add(Triple(0, 0, 0))
        var visited = mutableSetOf<Pair<Int, Int>>()
        while (pq.isNotEmpty()) {
            val (d, x, y) = pq.poll()
            if (Pair(x, y) == dest)
                return d
            listOf(x to y + 1, x to y - 1, x + 1 to y, x - 1 to y)
                .forEach { (X, Y) ->
                    if (X in 0 until maxX &&
                        Y in 0 until maxY &&
                        Pair(X, Y) !in visited) {
                        val dx = X / cave[0].size
                        val dy = Y / cave.size
                        //println(Pair(X, Y))
                        val originalRisk = cave[Y % cave.size][X % cave[0].size]
                        val newRisk = (originalRisk + dx + dy)
                        val newerRisk = newRisk.takeIf { it < 10 } ?: (newRisk - 9)
                        visited.add(Pair(X, Y))

                        pq.offer(Triple(newerRisk + d, X, Y))
                    }
                }
        }
        return 0 // will never be reached
    }

    override fun partOne(): Any? {
        return calcRisk(1)
    }

    override fun partTwo(): Any? {
        return calcRisk(5)
    }
}
