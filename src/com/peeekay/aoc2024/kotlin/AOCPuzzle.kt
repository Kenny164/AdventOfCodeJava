package com.peeekay.aoc2024.kotlin

import java.io.File
import java.net.URI

abstract class AOCPuzzle(private val dayNumber: Int, private val isTest: Boolean = false) {

    private fun resourceURI(): URI =
        when (this.isTest) {
            true -> javaClass.classLoader.getResource("input_2024/day" + dayNumber.toString().padStart(2, '0') + "_test.txt")
                ?.toURI() ?: throw IllegalArgumentException("Cannot find Resource: $this")
            false -> javaClass.classLoader.getResource("input_2024/day" + dayNumber.toString().padStart(2, '0') + ".txt")
                ?.toURI() ?: throw IllegalArgumentException("Cannot find Resource: $this")
        }

    fun resourceAsText(): String =
        File(resourceURI()).readText()

    fun resourceAsSplitText(splitOn: String): List<String> =
        resourceAsText().split(splitOn)

    fun resourceAsList(): List<String> =
        File(resourceURI()).readLines()

    fun resourceAsString(delimiter: String = ""): String =
        resourceAsList().reduce { a, b -> "$a$delimiter$b" }

    fun resourceAsListOfInt(): List<Int> =
        resourceAsList().map { it.toInt() }

    abstract fun partOne(): Any?
    abstract fun partTwo(): Any?
}