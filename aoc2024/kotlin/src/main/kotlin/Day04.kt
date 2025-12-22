package com.peeekay.aoc2024.kotlin

class Day04(val isTest: Boolean = false) : AOCPuzzle(4, isTest) {
    private val pInput = resourceAsList().map {
        it.toCharArray().toList()
    }.toList()

    private val directions = listOf(Pair(1,0), Pair(-1,0), Pair(0,1), Pair(0,-1),
        Pair(1,1), Pair(-1,-1), Pair(1,-1), Pair(-1,1))

    override fun partOne(): Any {
        var result = 0
        pInput.mapIndexed { r, row ->
            row.mapIndexed { c, colValue ->
                val matches = directions.count { d ->
                    var xR = r
                    var xC = c
                    for (letter in listOf('X','M','A','S')) {
                        if (letter != pInput.getOrNull(xR)?.getOrNull(xC)) {
                            return false
                        }
                        xR += d.first
                        xC += d.second
                    }
                    return true
                }
                result += matches
            }
        }

        return result
    }

    override fun partTwo(): Any {
        return 0
    }

}