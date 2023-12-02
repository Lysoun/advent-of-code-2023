package day2

import java.io.File

data class Bag(val redNumbers: Int, val greenNumbers: Int, val blueNumbers: Int)

fun isRoundPossible(round: String, bag: Bag): Boolean {
    val reds = Regex("(\\d+) red").find(round)?.groups?.get(1)?.value?.toInt()?: 0
    val greens = Regex("(\\d+) green").find(round)?.groups?.get(1)?.value?.toInt()?: 0
    val blues = Regex("(\\d+) blue").find(round)?.groups?.get(1)?.value?.toInt()?: 0

    return bag.redNumbers >= reds && bag.greenNumbers >= greens && bag.blueNumbers >= blues
}

fun isGamePossible(game: String, bag: Bag): Boolean {
    val rounds = game.split(';')
    return rounds.all { isRoundPossible(it, bag) }
}

fun getGameId(game: String): Int {
    return Regex("Game (\\d+):").find(game)!!.groups[1]!!.value.toInt()
}

fun main() {
    val inputFileName = "day2/input.txt"
    var numberOfGamesPossible = 0
    val bag = Bag(12, 13, 14)

    File(inputFileName).forEachLine {
        if (isGamePossible(it, bag)) {
            numberOfGamesPossible += getGameId(it)
        }
    }

    println(numberOfGamesPossible)
}