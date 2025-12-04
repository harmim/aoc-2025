package com.harmim.aoc2025

abstract class BaseDay(private val input: String) {
    abstract fun part1(): Any

    abstract fun part2(): Any

    protected fun raw() = input

    protected fun lines() = input.trim().lines()
}
