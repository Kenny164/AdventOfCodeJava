package com.peeekay.aoc2021.kotlin

class Day16 : AOCPuzzle(16){
    private val rawInput = resourceAsString()
    private val cave = inputToBinIterator(rawInput)
    var versionSum = 0

    fun inputToBinIterator(inp: String) = inp
            .map { it.digitToInt(16).toString(2).padStart(4, '0') }
            .flatMap { it.toList() }
            .iterator()

    private fun <T> Iterator<T>.takeNext(i: Int): String {
        return (1..i).map { this.next() }.joinToString("")
    }

    fun parsePacketsRecurse(inp: Iterator<Char>): Long {
        val version = inp.takeNext(3).toInt(2)
        versionSum += version
        val packetType = inp.takeNext(3).toInt(2)

        if (packetType == 4) {
            val payload = StringBuilder()
            while (true) {
                val pendingDataBit = inp.takeNext(1) == "1"
                payload.append(inp.takeNext(4))
                if (!pendingDataBit) break
            }
            return payload.toString().toLong(2)
        }

        val subPackets = mutableListOf<Long>()

        val payLoadLengthBit = inp.takeNext(1) // The reason why packetType 4 logic is above
        if (payLoadLengthBit == "0") {
            val subPacketLength = inp.takeNext(15).toInt(2)
            val payload = inp.takeNext(subPacketLength).iterator()
            while (payload.hasNext()) {
                subPackets.add(parsePacketsRecurse(payload))
            }
        } else {
            val subPacketCount = inp.takeNext(11).toInt(2)
            (1..subPacketCount).map { subPackets.add(parsePacketsRecurse(inp))}
        }

        return when (packetType){
            0 -> subPackets.sum()
            1 -> subPackets.fold(1L, Long::times)
            2 -> subPackets.minOf { it }
            3 -> subPackets.maxOf { it }
            5 -> if (subPackets[0] > subPackets[1]) 1L else 0L
            6 -> if (subPackets[0] < subPackets[1]) 1L else 0L
            7 -> if (subPackets[0] == subPackets[1]) 1L else 0L
            else -> error("unknown operation: $packetType")
        }
    }

    override fun partOne(): Any? {
        parsePacketsRecurse(cave)
        return versionSum
    }

    override fun partTwo(): Any? {
        return parsePacketsRecurse(inputToBinIterator(rawInput))
    }
}
