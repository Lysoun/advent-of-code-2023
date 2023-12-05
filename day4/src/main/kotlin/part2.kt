package day4

import java.io.File

fun main() {
    val inputFileName = "day4/input.txt"
    val regex = Regex("Card +(\\d+):([\\d ]+)\\|([\\d ]+)")
    val scratchcardsCountsByNumbers = mutableMapOf<Int, Int>()
    var totalScratchCardsNumber = 0

    File(inputFileName).forEachLine { line ->
        val matchedGroups = regex.find(line)?.groups
        if (matchedGroups != null) {
            val cardNumber = matchedGroups[1]!!.value.toInt()
            val cardCount = scratchcardsCountsByNumbers[cardNumber]?: 1
            val winningNumber = matchedGroups[2]!!.value.split(" ").filter { it != "" }.map { it.toInt() }.toSet()
            val myCard = matchedGroups[3]!!.value.split(" ").filter { it != "" }.map { it.toInt() }.toSet()
            val numberOfWinningNumbers = myCard.intersect(winningNumber).size

            for(i in 1..numberOfWinningNumbers) {
                scratchcardsCountsByNumbers[cardNumber + i] = (scratchcardsCountsByNumbers[cardNumber + i]?: 1) + cardCount
            }

            totalScratchCardsNumber+= cardCount
        }
    }

    println(totalScratchCardsNumber)
}