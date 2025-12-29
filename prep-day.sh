#!/usr/bin/env bash

set -e

YEAR=2025
SESSION_FILE=.session
MAIN_SRC_DIR=src/main
SRC_DIR="$MAIN_SRC_DIR/kotlin/com/harmim/aoc$YEAR"
RES_DIR="$MAIN_SRC_DIR/resources"
INPUT_DIR="$RES_DIR/input"
INPUT_TEST_DIR="$RES_DIR/input-test"

if [ -z "$1" ]; then
    echo "Must provide a day number." >&2
    exit 1
fi

DAY=$(printf %d "$1")
MIN_DAY=1
MAX_DAY=12
if [[ $DAY -lt $MIN_DAY || $DAY -gt $MAX_DAY ]]; then
    echo "The day must be between $MIN_DAY and $MAX_DAY, inclusive." >&2
    exit 1
fi

if [ ! -f "$SESSION_FILE" ]; then
    echo "Missing the session file: '$SESSION_FILE'." >&2
    exit 1
fi

SESSION="$(cat "$SESSION_FILE")"
if [ -z "$SESSION" ]; then
    echo "Must set the session from the Advent of Code website." >&2
    exit 1
fi

DAY_FILE=$(printf %02d $DAY)

mkdir -p "$INPUT_DIR"
INPUT_FILE="$INPUT_DIR/$DAY_FILE.txt"
if [ ! -f "$INPUT_FILE" ]; then
    echo "Downloading input data for day $DAY to '$INPUT_FILE'..."
    curl "https://adventofcode.com/$YEAR/day/$DAY/input" \
        -s -m 10 -b "session=$SESSION" > "$INPUT_FILE"
else
    echo "Input data already exists for day $DAY, skipping download..."
fi

mkdir -p "$INPUT_TEST_DIR"
TEST_FILE="$INPUT_TEST_DIR/$DAY_FILE.txt"
if [ ! -f "$TEST_FILE" ]; then
    echo "Creating an empty input test file '$TEST_FILE' for day $DAY..."
    touch "$TEST_FILE"
else
    echo "An input test file already exists for day $DAY, skipping..."
fi

mkdir -p "$SRC_DIR"
DAY_CLASS=Day$DAY_FILE
SRC_FILE="$SRC_DIR/$DAY_CLASS.kt"
if [ ! -f "$SRC_FILE" ]; then
    echo "Creating $DAY_CLASS solver at '$SRC_FILE'..."
    cat <<-EOF > "$SRC_FILE"
package com.harmim.aoc$YEAR

class $DAY_CLASS(input: String) : BaseDay(input) {
    override fun part1() = 0

    override fun part2() = 0
}
EOF
else
    echo "'$SRC_FILE' already exists, skipping..."
fi

echo "Happy coding!"
