package com.harmim.aoc2025

class Day05(input: String) : BaseDay(input) {
    private val freshIds: List<LongRange>
    private val availableIds: List<Long>

    init {
        val (freshIdsStr, availableIdsStr) = raw().split("\n\n")
        freshIds = freshIdsStr.lines().map {
            val (start, end) = it.split('-')
            start.toLong()..end.toLong()
        }.sortedBy { it.first }
        availableIds = availableIdsStr.lines().map { it.toLong() }
    }

    override fun part1() = availableIds.count { id -> freshIds.any { id in it } }

    private val LongRange.size get() = last - first + 1

    override fun part2(): Long {
        var totalIds = 0L
        var currentIds = freshIds.first()

        for (i in 1 until freshIds.size) {
            val nextIds = freshIds[i]
            if (nextIds.first <= currentIds.last + 1) {
                currentIds = currentIds.first..maxOf(currentIds.last, nextIds.last)
            } else {
                totalIds += currentIds.size
                currentIds = nextIds
            }
        }

        return totalIds + currentIds.size
    }
}
