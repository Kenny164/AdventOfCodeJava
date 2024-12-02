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
                    ?.let { (a, b, c, d) ->
                        Target(a.toInt()..b.toInt(), c.toInt()..d.toInt())
                    }
        }
    }

    data class Point2d(val x: Int, val y: Int)

    data class Probe(val position: Point2d = Point2d(0, 0), val velocity: Point2d) {
        companion object {
            fun step(from: Probe): Probe = Probe(
                Point2d(
                    from.position.x + from.velocity.x,
                    from.position.y + from.velocity.y
                ),
                Point2d(
                    (if (from.velocity.x > 0) from.velocity.x - 1 else 0),
                    from.velocity.y - 1
                )
            )
        }
    }


    override fun partOne(): Any? {
        val target = Target.of("target area: x=20..30, y=-10..-5")
        val probe = Probe(Point2d(0, 0), Point2d(6, 9))

        return Probe.step(probe)
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }

}
