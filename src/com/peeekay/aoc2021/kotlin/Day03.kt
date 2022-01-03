package com.peeekay.aoc2021.kotlin

class Day03 : AOCPuzzle(3) {
    private val input: List<String> = resourceAsList()

    private val input_test: List<String> = resourceAsList_Test()

    enum class RatingType {
        OXYGEN,
        CO2
    }

    private fun getDiagGreeks(input: List<String>): Pair<Int, Int> {
        val gamma = input.first().indices.map { column ->
            if (input.count { it[column] == '1' } > input.size / 2) '1' else '0'
        }.joinToString("")

        val epsilon = gamma.map { if(it == '1') '0' else '1' }.joinToString("")

        return Pair(gamma.toInt(2), epsilon.toInt(2))
    }

    private fun getRating(input: List<String>, type: RatingType): Int{
        return input.first().indices.fold(input) { inputs, column ->
            if (inputs.size == 1) inputs else {
                val (ones, zeros) = inputs.partition { it[column] == '1' }
                when (type){
                    RatingType.OXYGEN -> if (zeros.count() > ones.count()) zeros else ones
                    RatingType.CO2 -> if (zeros.count() <= ones.count()) zeros else ones
                }
            }
        }.single().toInt(2)
    }

    override fun partOne(): Any? {
        val (gamma, epsilon) = getDiagGreeks(input)
        return gamma * epsilon
    }

    override fun partTwo(): Any? {
        return getRating(input, RatingType.OXYGEN) * getRating(input, RatingType.CO2)
    }
}
