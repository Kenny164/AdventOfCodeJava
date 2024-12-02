package com.peeekay.aoc2021.kotlin

class Day12 : AOCPuzzle(12) {
    private val cave: Map<String, List<String>> =
        resourceAsList()
            .flatMap { line ->
            val l = line.split("-")
            listOf(l.first() to l.last(),
                   l.last() to l.first())
        }.groupBy ({it.first}, {it.second})

    private fun Map<String, List<String>>.steps(ruleSet:(String, List<String>)-> Boolean): MutableSet<List<String>> {
        val caveQ = mutableListOf(listOf("start"))
        val allPaths = mutableSetOf<List<String>>()

        while (caveQ.isNotEmpty()){
            val path = caveQ.removeFirst()
            val curParent = path.last()

            if (curParent == "end") {
                allPaths.add(path)
                continue
            }

            this.getOrDefault(curParent, listOf()).forEach { c ->
                if (ruleSet(c, path))
                    caveQ.add(path + c)
            }
        }

        return allPaths
    }

    private fun partOneRules(c: String, path: List<String>) =
        c.all { !it.isLowerCase() } || c !in path

    private fun partTwoRules(c: String, path: List<String>) =
        when {
            c == "start" -> false
            c.all { !it.isLowerCase() } -> true
            c !in path -> true
            else -> path
                .filterNot { w -> w.all { !it.isLowerCase() }}
                .groupBy { it }
                .none { it.value.size == 2 }
        }

    override fun partOne(): Any? {
        return cave.steps(::partOneRules).size
    }

    override fun partTwo(): Any? {
        return cave.steps(::partTwoRules).size
    }

}
