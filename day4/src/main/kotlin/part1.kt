package day4

import java.io.File
import kotlin.math.pow

fun main() {
    val inputFileName = "day4/input.txt"
    val regex = Regex("Card +\\d+:([\\d ]+)\\|([\\d ]+)")
    var score = 0.0

    File(inputFileName).forEachLine { line ->
        val matchedGroups = regex.find(line)?.groups
        if (matchedGroups != null) {
            val winningNumber = matchedGroups[1]!!.value.split(" ").filter { it != "" }.map { it.toInt() }.toSet()
            val myCard = matchedGroups[2]!!.value.split(" ").filter { it != "" }.map { it.toInt() }.toSet()
            val numberOfWinningNumbers = myCard.intersect(winningNumber).size
            score += 2.0.pow(numberOfWinningNumbers.toDouble() - 1).toInt()
        }
    }

    println(score)
}