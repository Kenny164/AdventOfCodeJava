package com.peeekay.aoc2021.kotlin

import java.io.File
import java.net.URI

abstract class AOCPuzzle(private val dayNumber: Int) {

    private fun resourceURI(isTest: Boolean = false): URI =
        when (isTest) {
            true -> javaClass.classLoader.getResource("input_2021/day" + dayNumber.toString().padStart(2, '0') + "_test.txt")
                ?.toURI() ?: throw IllegalArgumentException("Cannot find Resource: $this")
            false -> javaClass.classLoader.getResource("input_2021/day" + dayNumber.toString().padStart(2, '0') + ".txt")
                ?.toURI() ?: throw IllegalArgumentException("Cannot find Resource: $this")
        }

    fun resourceAsString(delimiter: String = ""): String =
            resourceAsList().reduce { a, b -> "$a$delimiter$b" }

    fun resourceAsText(): String =
            File(resourceURI()).readText()

    fun resourceAsList(): List<String> =
            File(resourceURI()).readLines()

    fun resourceAsListOfInt(): List<Int> =
            resourceAsList().map { it.toInt() }

    fun resourceAsList_Test(): List<String> =
        File(resourceURI(true)).readLines()

    abstract fun partOne(): Any?
    abstract fun partTwo(): Any?
}