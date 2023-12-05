package day3

import java.io.File

fun isSymbol(char: Char): Boolean = !char.isDigit() && char != '.'

fun isNextToASymbol(position: Pair<Int, Int>, engineSchematic: Map<Pair<Int, Int>, Char>): Boolean {
    val neighbourPositions = listOf(
        position.first to position.second -1,
        position.first + 1 to position.second -1,
        position.first - 1 to position.second -1,
        position.first to position.second +1,
        position.first + 1 to position.second +1,
        position.first - 1 to position.second +1,
        position.first + 1 to position.second,
        position.first - 1 to position.second
    )

    return neighbourPositions.mapNotNull { engineSchematic[it] }.any { isSymbol(it) }
}

fun main() {
    val inputFileName = "day3/input.txt"
    val engineSchematic: MutableMap<Pair<Int, Int>, Char> = mutableMapOf()
    val numbers: MutableList<Pair<Int, List<Pair<Int, Int>>>> = mutableListOf()
    var lineNumber = 0

    File(inputFileName).forEachLine {
        var number = ""
        var digitsIndices = mutableListOf<Pair<Int, Int>>()
        it.forEachIndexed { index, c ->
            engineSchematic[lineNumber to index] = c
            if (c.isDigit()) {
                number += c.toString()
                digitsIndices.add(lineNumber to index)
            } else if(number != "") {
                numbers.add(
                    number.toInt() to digitsIndices
                )
                number = ""
                digitsIndices = mutableListOf()
            }
        }

        if(number != "") {
            numbers.add(
                number.toInt() to digitsIndices
            )
            number = ""
            digitsIndices = mutableListOf()
        }

        ++lineNumber
    }

    println(numbers.filter {
        val nextToASymbol = it.second.any { digitsIndices -> isNextToASymbol(digitsIndices, engineSchematic) }
        println("number: ${it.first} indices: ${it.second} nextToASymbol: $nextToASymbol")
        nextToASymbol
    }.sumOf { it.first })
}