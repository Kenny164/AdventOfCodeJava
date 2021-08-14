package com.peeekay.adventOfCodeKotlin

class Day04 : AOCPuzzle(4) {
    private val newLine = System.lineSeparator()
    private val passports = resourceAsText()
        .split("$newLine$newLine")
        .map {
            it.split(" ", newLine)
                .associate { x ->
                    val y = x.split(":")
                    y[0] to y[1]}
        }

    override fun partOne(): Any? {
        val requiredFields = listOf("byr", "iyr", "eyr", "hgt", "hcl", "ecl", "pid")

        return passports.count { it.keys.containsAll(requiredFields) }
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }

}
