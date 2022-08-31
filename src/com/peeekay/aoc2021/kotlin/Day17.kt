package com.peeekay.aoc2021.kotlin

class Day17 : AOCPuzzle(17) {
    val rawTarget = resourceAsText()

    data class Target(val x: IntRange, val y: IntRange) {
        fun inTargetArea(x: Int, y: Int) =
            x in this.x && y in this.y

        fun beyondTargetArea(x: Int, y: Int) =
            x > this.x.last || y < this.y.first

        companion object {
            private val targetRegex = """target area: x=(-?\d+)\.\.(-?\d+), y=(-?\d+)\.\.(-?\d+)""".toRegex()

            fun of(inp: String): Target? =
                targetRegex.matchEntire(inp)
                    ?.destructured
                    ?.let{ (a,b,c,d) ->
                        Target(a.toInt()..b.toInt(),c.toInt()..d.toInt())
                    }
        }
    }



    override fun partOne(): Any? {
        return Target.of("target area: x=20..30, y=-10..-5")
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }

}
