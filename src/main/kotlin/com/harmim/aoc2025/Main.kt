package com.harmim.aoc2025

import java.nio.file.Files
import java.nio.file.Path
import kotlin.system.exitProcess
import kotlin.system.measureTimeMillis

private class InputException(msg: String) : Exception(msg)

fun main(args: Array<String>) {
    if (args.isEmpty()) {
        System.err.println("Must provide a day number.")
        exitProcess(1)
    }

    val day = args[0].toIntOrNull()
    if (day == null) {
        System.err.println("Invalid day: '${args[0]}'.")
        exitProcess(1)
    }
    val test = args.getOrNull(1) == "test"

    try {
        val solver = loadDay(day, loadInput(day, test))

        fun run(partName: String, part: () -> Any) {
            val result: Any
            val time = measureTimeMillis { result = part() }
            println("$partName: $result (${time / 1_000.0} seconds)")
        }

        println("Solving day $day${if (test) " (test input)" else ""}...")
        run("Part 1") { solver.part1() }
        run("Part 2") { solver.part2() }
    } catch (e: InputException) {
        System.err.println(e.message)
        exitProcess(1)
    }
}

private fun loadInput(day: Int, test: Boolean): String {
    val path = Path.of("src/main/resources/input${if (test) "-test" else ""}/%02d.txt".format(day))
    if (!Files.exists(path)) {
        throw InputException("Missing an input file: '$path'.")
    }

    return Files.readString(path)
}

private fun loadDay(day: Int, input: String): BaseDay {
    val className = "com.harmim.aoc2025.Day%02d".format(day)

    return try {
        val cls = Class.forName(className)
        val constructor = cls.getConstructor(String::class.java)
        constructor.newInstance(input) as BaseDay
    } catch (_: ClassNotFoundException) {
        throw InputException("Day $day not implemented.")
    }
}
