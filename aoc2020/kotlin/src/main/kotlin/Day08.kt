package com.peeekay.aoc2020.kotlin

class Day08: AOCPuzzle(8) {
    private val rawInput = resourceAsList()
/*    private val rawInput = """nop +0
acc +1
jmp +4
acc +3
jmp -3
acc -99
acc +1
jmp -4
acc +6""".split("\n")*/

    data class Instruction(val name: CMD, val argument: Int) {
        enum class CMD { jmp, acc, nop }

        companion object {
            fun of(input: String): Instruction =
                input.split(" ").let { Instruction(CMD.valueOf(it.first()), it.last().toInt()) }
        }
    }

    data class Computer(val instructions: List<Instruction>) {
        enum class ExecutionState {
            HALTED,
            INFINITE_LOOP,
            RUNNING
        }

        private val executed = mutableSetOf<Int>()
        private var instructionPointer: Int = 0
        var accumulator: Int = 0

        fun runUntilTerminate(): ExecutionState =
            generateSequence { executeStep() }.first { it != ExecutionState.RUNNING }

        private fun executeStep(): ExecutionState {
            return when (instructionPointer) {
                in executed -> ExecutionState.INFINITE_LOOP
                !in instructions.indices -> ExecutionState.HALTED
                else -> {
                    val current = instructions[instructionPointer]
                    executed += instructionPointer

                    when (current.name) {
                        Instruction.CMD.acc -> {
                            accumulator += current.argument
                            instructionPointer += 1
                        }
                        Instruction.CMD.jmp -> instructionPointer += current.argument
                        Instruction.CMD.nop -> instructionPointer += 1
                    }
                    ExecutionState.RUNNING
                }
            }
        }
    }

    override fun partOne(): Any? {
        val instructions = rawInput.map { Instruction.of(it) }
        return Computer(instructions).run {
            runUntilTerminate()
            accumulator
        }
    }

    override fun partTwo(): Any? {
        TODO("Not yet implemented")
    }

}
