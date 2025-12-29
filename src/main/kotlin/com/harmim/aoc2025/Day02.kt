package com.harmim.aoc2025

class Day02(input: String) : BaseDay(input) {
    private val ranges: List<LongRange> = raw().split(',').map {
        val parts = it.split('-')
        parts[0].toLong()..parts[1].toLong()
    }

    override fun part1(): Any = ranges.sumOf { range ->
        range.filter {
            val idStr = it.toString()
            val idLen = idStr.length
            val idLenHalf = idLen / 2
            idLen % 2 == 0 && idStr.take(idLenHalf) == idStr.takeLast(idLenHalf)
        }.sum()
    }

    override fun part2(): Any = ranges.sumOf { range ->
        range.filter { id ->
            val idStr = id.toString()
            val idLen = idStr.length
            (1..idLen / 2).any { idLen % it == 0 && idStr.chunked(it).distinct().size <= 1 }
        }.sum()
    }
}
