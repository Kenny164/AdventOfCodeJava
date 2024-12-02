package com.peeekay.aoc2021.kotlin

import kotlin.math.abs

class Day13 : AOCPuzzle(13) {
    private val rawInput = resourceAsList()
    private val paper = rawInput
        .takeWhile { it.isNotEmpty() }
        .map { it.split(",").map(String::toInt) }
        .mapNotNull { it[0] to it[1] }.toSet()
    private val folds = rawInput
        .takeLastWhile { it.isNotEmpty()  }
        .map { s -> s.split(" ").last().let {it[0] to it.drop(2).toInt()}}

    private fun Set<Pair<Int,Int>>.foldBy(fold: Pair<Char,Int>): Set<Pair<Int, Int>> =
        this.toSet()
            .map { (x, y) ->
                if (fold.first == "x".first()) fold.second - abs(x - fold.second) to y
                else x to fold.second - abs(y - fold.second)
            }
            .toSet()

    private fun Set<Pair<Int, Int>>.pprint() {
        val (x, y) = this.maxOf { it.first } to this.maxOf { it.second }
        (0..y).forEach { j -> println((0..x).joinToString(" ") { i -> if (i to j in this) "#" else "." }) }
    }
    override fun partOne(): Any? {
        //paper.toSet().pprint()
        //println("--------------")
        val ans = paper.foldBy(folds[0])
        //ans.pprint()
        return ans.size
    }

    override fun partTwo(): Any? {
        val ans = folds.fold(paper) { p, f -> p.foldBy(f) }
        ans.pprint()
        return ans.size
    }

}
