package com.peeekay.aoc2020.kotlin

class Day07 : AOCPuzzle(7) {
    private val rawInput = resourceAsList()
/*private val rawInput = """light red bags contain 1 bright white bag, 2 muted yellow bags.
dark orange bags contain 3 bright white bags, 4 muted yellow bags.
bright white bags contain 1 shiny gold bag.
muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
dark olive bags contain 3 faded blue bags, 4 dotted black bags.
vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
faded blue bags contain no other bags.
dotted black bags contain no other bags.""".lines()*/

    private val bags = rawInput.map(Bag.Companion::parse)

    data class Bag(val name: String, val children: List<Pair<Int,String>>){

        companion object {
            private val regexLine = Regex("""(\d+ )?([^no other]\w+ \w+) bag""")

            fun parse(inputLine: String): Bag {
                val matches = regexLine.findAll(inputLine)
                val nodeName = matches.first().groupValues[2]
                val children = matches.drop(1)
                    .map { Pair( it.groupValues[1].trim().toInt(), it.groupValues[2] ) }
                    .toList()
                return Bag(nodeName, children)
            }
        }
    }

    override fun partOne(): Any? {
        val outerBags = mutableSetOf<String>()
        val bagQueue = ArrayDeque<String>()
        bagQueue.add("shiny gold")

        while (bagQueue.isNotEmpty()) {
            val innerBag = bagQueue.removeLast()
            bags
                .filter { bag -> bag.children.any { it.second == innerBag } }
                .forEach { bag -> if (outerBags.add(bag.name)) bagQueue.add(bag.name) }

        }
        return outerBags.size
    }

    override fun partTwo(): Any? {
        fun bagsContainedIn(bag: String): Int {
            val selectedBag = bags.first{ it.name == bag }
            val sum = selectedBag.children.sumOf { (n, innerBag) ->
                n + (n * bagsContainedIn(innerBag))
            }
            return sum
        }
        return bagsContainedIn("shiny gold")
    }


}
