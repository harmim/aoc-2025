package com.harmim.aoc2025

class Day01(input: String) : BaseDay(input) {
    companion object {
        private const val START_POSITION = 50
        private const val DIAL_MAX = 99 + 1
    }

    private enum class Direction {
        LEFT, RIGHT;

        companion object {
            fun fromStr(str: String): Direction = when (str) {
                "L" -> LEFT
                "R" -> RIGHT
                else -> error("Unknown direction '$str'.")
            }
        }
    }

    private data class Rotation(val direction: Direction, val clicks: Int)

    private val rotations: List<Rotation> = lines().map {
        val (direction, clicks) = """([LR])(\d+)""".toRegex().matchEntire(it)!!.destructured
        Rotation(Direction.fromStr(direction), clicks.toInt())
    }

    private fun makeMove(position: Int, rotation: Rotation): Int =
        Math.floorMod(position + rotation.clicks * if (rotation.direction == Direction.RIGHT) 1 else -1, DIAL_MAX)

    override fun part1(): Any = rotations.fold(START_POSITION to 0) { (position, zeros), rotation ->
        val newPosition = makeMove(position, rotation)
        newPosition to zeros + if (newPosition == 0) 1 else 0
    }.second

    override fun part2(): Any = rotations.fold(START_POSITION to 0) { (position, zeros), rotation ->
        val remainder = rotation.clicks % DIAL_MAX
        val remainderCrossed = when (rotation.direction) {
            Direction.LEFT -> if (position != 0 && position - remainder <= 0) 1 else 0
            Direction.RIGHT -> if (position + remainder >= DIAL_MAX) 1 else 0
        }
        makeMove(position, rotation) to zeros + rotation.clicks / DIAL_MAX + remainderCrossed
    }.second
}
