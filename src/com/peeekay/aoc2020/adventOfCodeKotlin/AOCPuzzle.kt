package com.peeekay.aoc2020.adventOfCodeKotlin

import java.io.File
import java.net.URI

abstract class AOCPuzzle(private val dayNumber: Int) {

    private fun resourceURI(): URI =
            javaClass.classLoader.getResource("input_2020/day"+dayNumber.toString().padStart(2, '0')+".txt")?.toURI() ?: throw IllegalArgumentException("Cannot find Resource: $this")

    fun resourceAsString(delimiter: String = ""): String =
            resourceAsList().reduce { a, b -> "$a$delimiter$b" }

    fun resourceAsText(): String =
            File(resourceURI()).readText()

    fun resourceAsList(): List<String> =
            File(resourceURI()).readLines()

    fun resourceAsListOfInt(): List<Int> =
            resourceAsList().map { it.toInt() }

    abstract fun partOne(): Any?
    abstract fun partTwo(): Any?
}