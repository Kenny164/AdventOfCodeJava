package com.peeekay.aoc2021.kotlin

import kotlin.math.absoluteValue
import kotlin.math.sign

class Day05 : AOCPuzzle(5) {
    private val input: List<String> = resourceAsList()
    private val input_test: List<String> = resourceAsList_Test()

    data class Point2d (val x: Int, val y: Int) {
        fun isStraightLine(that: Point2d): Boolean {
            return x == that.x || y == that.y
        }

        fun lineTo(that: Point2d): List<Point2d>{
            val xDelta = (that.x - x).sign
            val yDelta = (that.y - y).sign
            val steps = maxOf((x - that.x).absoluteValue, (y - that.y).absoluteValue)
            return (1 .. steps).scan(this) { last, _ -> Point2d(last.x + xDelta, last.y + yDelta) }
        }
    }

    private fun List<String>.parseLines(): List<Pair<Point2d,Point2d>> {
        val regexLine = """(\d+),(\d+) -> (\d+),(\d+)""".toRegex()
        return this.mapNotNull { line ->
            regexLine.matchEntire(line)
                ?.destructured
                ?.let { (x1, y1, x2, y2) ->
                    Pair(Point2d(x1.toInt(), y1.toInt()), Point2d(x2.toInt(), y2.toInt()))
                }
        }
    }


    override fun partOne(): Any? {
        val instructions = input.parseLines()
        return instructions
            .filter { it.first.isStraightLine(it.second) }
            .flatMap {it.first.lineTo(it.second)}
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1}
    }

    override fun partTwo(): Any? {
        val instructions = input.parseLines()
        return instructions
            //.filter { it.first.isStraightLine(it.second) }
            .flatMap {it.first.lineTo(it.second)}
            .groupingBy { it }
            .eachCount()
            .count { it.value > 1}
    }

}
