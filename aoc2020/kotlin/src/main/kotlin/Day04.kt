package com.peeekay.aoc2020.kotlin

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
        val rules: Map<String, (String) -> Boolean> = mapOf(
            "eyr" to { it.toInt() in 2020..2030 },
            "byr" to { it.toInt() in 1920..2002 },
            "iyr" to { it.toInt() in 2010..2020 },
            "hgt" to {
                when ( it.takeLast(2)) {
                    "cm" -> it.removeSuffix("cm").toInt() in 150..193
                    "in" -> it.removeSuffix("in").toInt() in 59..76
                    else -> false
                }
            },
            "hcl" to { it matches """#[0-9a-f]{6}""".toRegex() },
            "ecl" to { it in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth") },
            "pid" to { it.length == 9 }
        )

        return passports
            .count {
                rules.all { (k, validateFn) ->
                    it[k]?.let { it1 -> validateFn(it1) } ?: false
                }
        }
    }

}


