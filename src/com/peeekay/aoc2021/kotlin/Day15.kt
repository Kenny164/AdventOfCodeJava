package com.peeekay.aoc2021.kotlin

import java.util.*

class Day15 : AOCPuzzle(15){
    private val rawInput = resourceAsList()
    private val cave = rawInput
        .map { row ->
            row.toCharArray()
                .map{it.digitToInt()}
        }

    override fun partOne(): Any? {
        val pq = PriorityQueue<Triple<Int, Int, Int>> {a, b ->
            a.first - b.first
        }
        val dest = Pair(cave.size-1,cave[0].size-1)
        pq.add(Triple(0,0,0))
        var visited = mutableSetOf<Pair<Int,Int>>()
        while (pq.isNotEmpty()) {
            val (d, x, y) = pq.poll()
            if (Pair(x, y) == dest)
                return d
            listOf(x to y + 1, x to y - 1, x + 1 to y, x - 1 to y)
                .forEach { (X, Y) ->
                    if (X in cave.indices && Y in cave[0].indices && Pair(X,Y) !in visited){
                        visited.add(Pair(X,Y))
                        pq.offer(Triple(cave[X][Y] + d, X, Y))
                    }
                }
        }

        return 0 // will never be reached
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }
}
