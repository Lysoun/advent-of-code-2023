package day3

import java.io.File

fun computeGearRatio(maybeGearPosition: Pair<Int, Int>, numbers: MutableMap<Pair<Int, Int>, Int>): Int {
    val neighbourNumbers = listOf(
        maybeGearPosition.first to maybeGearPosition.second -1,
        maybeGearPosition.first + 1 to maybeGearPosition.second -1,
        maybeGearPosition.first - 1 to maybeGearPosition.second -1,
        maybeGearPosition.first to maybeGearPosition.second +1,
        maybeGearPosition.first + 1 to maybeGearPosition.second +1,
        maybeGearPosition.first - 1 to maybeGearPosition.second +1,
        maybeGearPosition.first + 1 to maybeGearPosition.second,
        maybeGearPosition.first - 1 to maybeGearPosition.second
    ).mapNotNull { numbers[it] }.toSet()

    return if (neighbourNumbers.size == 2) {
        neighbourNumbers.reduce(Int::times)
    } else {
        0
    }
}

fun main() {
    val inputFileName = "day3/input.txt"
    val numbers: MutableMap<Pair<Int, Int>, Int> = mutableMapOf()
    val hopefullyGears: MutableList<Pair<Int, Int>> = mutableListOf()
    var lineNumber = 0

    File(inputFileName).forEachLine {
        var number = ""
        var digitsIndices = mutableListOf<Pair<Int, Int>>()
        it.forEachIndexed { index, c ->
            if (c.isDigit()) {
                number += c.toString()
                digitsIndices.add(lineNumber to index)
            } else if(number != "") {
                digitsIndices.forEach {digitIndex ->
                    numbers[digitIndex] = number.toInt()
                }
                number = ""
                digitsIndices = mutableListOf()
            }

            if (c == '*') {
                hopefullyGears.add(lineNumber to index)
            }
        }

        if(number != "") {
            digitsIndices.forEach {digitIndex ->
                numbers[digitIndex] = number.toInt()
            }
            number = ""
            digitsIndices = mutableListOf()
        }

        ++lineNumber
    }

    println(hopefullyGears.sumOf { computeGearRatio(it, numbers) })
}