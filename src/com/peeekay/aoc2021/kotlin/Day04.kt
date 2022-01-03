package com.peeekay.aoc2021.kotlin

class Day04 : AOCPuzzle(4) {
    private val input: List<String> = resourceAsList()
    private val input_test: List<String> = resourceAsList_Test()

    private fun parsedInput(input: List<String>): Pair<List<Int>,Set<BingoBoard>> {
        val draws = input.first().split(",").map { it.toInt() }
        val boards: Set<BingoBoard> = input.drop(1)
            .chunked(6)
            .map {board ->
                board.filter {lines ->
                    lines.isNotBlank()}.map { line ->
                    line.trim()
                        .split(Regex("\\W+"))
                        .map { it.toInt() }
                }
            }.toSet()
        return Pair(draws, boards)
    }

    override fun partOne(): Any? {
        val ( draws, boards ) = parsedInput(input)
        val drawn = draws.take(4).toMutableSet()
        return draws.drop(4).firstNotNullOf { draw ->
            drawn += draw
            boards.firstOrNull { it.isWinner(drawn) }?.let { winner ->
                draw * winner.sumUnmarked(drawn)
            }
        }
    }

    override fun partTwo(): Any? {
        val ( draws, boards ) = parsedInput(input)
        val drawn = draws.toMutableSet()
        return draws.reversed().firstNotNullOf { draw ->
            drawn -= draw
            boards.firstOrNull { !it.isWinner(drawn) }?.let { winner ->
                draw * (winner.sumUnmarked(drawn) - draw)
            }
        }
    }

    private fun BingoBoard.isWinner(draws: Set<Int>) =
        this.any { row -> row.all { it in draws } } ||
                (0..4).any { col -> this.all { row -> row[col] in draws } }

    private fun BingoBoard.sumUnmarked(draws: Set<Int>): Int =
        this.sumOf { row ->
            row.filterNot { it in draws }.sum()
        }
}

typealias BingoBoard = List<List<Int>>
