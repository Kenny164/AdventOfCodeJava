package com.peeekay.aoc2022.kotlin

class Day01(val isTest: Boolean = false) : AOCPuzzle(1, isTest){
    private val inp: List<List<Int>> by lazy {
        resourceAsSplitText("\r\n\r\n")
            .map { elf_group ->
                elf_group.lines().map { calorie ->
                    calorie.toInt() } } }

    override fun partOne(): Any? {
        return inp.maxOf { it.sum() }
    }

    override fun partTwo(): Any? {
        return inp.map { it.sum() }.sortedDescending().take(3).sum()
    }

}
