package com.harmim.aoc2025

class Day03(input: String) : BaseDay(input) {
    private val banks = lines().map { bank -> bank.map { it.digitToInt() } }

    private fun outputJoltage(batteries: Int) = banks.sumOf { bank ->
        buildString {
            var cursor = 0
            for (i in batteries downTo 1) {
                val maxIndex = (cursor..bank.size - i).maxBy { bank[it] }
                append(bank[maxIndex])
                cursor = maxIndex + 1
            }
        }.toLong()
    }

    override fun part1() = outputJoltage(2)

    override fun part2() = outputJoltage(12)
}
