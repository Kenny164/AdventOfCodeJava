package com.peeekay.adventOfCodeKotlin

class Day02 : AOCPuzzle(2) {
    private val passwords = resourceAsList().map(Password.Companion::parse)

    data class Password(val range: IntRange, val letter: Char, val password: String){

        val validateOne: Boolean = password.count { it == letter } in range

        val validateTwo: Boolean = (password[range.first -1] == letter) xor (password[range.last -1] == letter)

        companion object {
            private val regexPattern = Regex("""^(\d+)-(\d+) (\w): (\w+)""")

            fun parse(line: String): Password? =
                regexPattern.matchEntire(line)
                        ?.destructured
                        ?.let {(start, end, letter, password) ->
                            Password(start.toInt()..end.toInt(), letter.single(), password)
                        }
        }
    }

    override fun partOne(): Any? {
        return passwords.count { it?.validateOne == true }
    }

    override fun partTwo(): Any? {
        return passwords.count { it?.validateTwo == true }
    }

}
