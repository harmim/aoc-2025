package com.harmim.aoc2025

class Day04(input: String) : BaseDay(input) {
    companion object {
        private val MOVES = listOf(
            -1 to -1, 0 to -1, 1 to -1,
            -1 to  0,          1 to  0,
            -1 to  1, 0 to  1, 1 to  1,
        )
    }

    private val initialGrid = lines().map { it.toList() }

    private fun rollsToRemove(grid: List<List<Char>>) = buildList {
        for (row in grid.indices) {
            for (col in grid[row].indices) {
                if (grid[row][col] == '@') {
                    val adjacentRolls = MOVES.count { (x, y) -> grid.getOrNull(row + x)?.getOrNull(col + y) == '@' }
                    if (adjacentRolls < 4) add(row to col)
                }
            }
        }
    }

    override fun part1() = rollsToRemove(initialGrid).size

    override fun part2(): Int {
        val grid = initialGrid.map { it.toMutableList() }
        var totalRemoved = 0

        while (true) {
            val toRemove = rollsToRemove(grid)
            if (toRemove.isEmpty()) break
            totalRemoved += toRemove.size
            toRemove.forEach { (row, col) -> grid[row][col] = '.' }
        }

        return totalRemoved
    }
}
