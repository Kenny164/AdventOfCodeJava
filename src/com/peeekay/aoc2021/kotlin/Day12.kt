package com.peeekay.aoc2021.kotlin

class Day12 : AOCPuzzle(12) {
    private val cave: Map<String, List<String>> =
        resourceAsList()
            .flatMap { line ->
            val l = line.split("-")
            listOf(l.first() to l.last(),
                   l.last() to l.first())
        }.groupBy ({it.first}, {it.second})

    private fun Map<String, List<String>>.steps(): MutableSet<List<String>> {
        var caveQ = mutableListOf(listOf("start"))
        var allPaths = mutableSetOf<List<String>>()

        while (caveQ.isNotEmpty()){
            val path = caveQ.removeFirst()
            val curParent = path.last()

            if (curParent == "end") {
                allPaths.add(path)
                continue
            }

            cave.getOrDefault(curParent, listOf()).forEach { c ->
                if (c.all { !it.isLowerCase() } || c !in path)
                    caveQ.add(path + c)
            }
        }

        return allPaths
    }

    override fun partOne(): Any? {
        return cave.steps().size
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }

}
