package day2

import java.io.File
import java.lang.Integer.max

fun computeGamePower(game: String): Int {
    val rounds = game.split(';')
    var redsNecessary = 0
    var greensNecessary = 0
    var bluesNecessary = 0

    rounds.forEach {
        val reds = Regex("(\\d+) red").find(it)?.groups?.get(1)?.value?.toInt()?: 0
        val greens = Regex("(\\d+) green").find(it)?.groups?.get(1)?.value?.toInt()?: 0
        val blues = Regex("(\\d+) blue").find(it)?.groups?.get(1)?.value?.toInt()?: 0

        redsNecessary = max(redsNecessary, reds)
        bluesNecessary = max(bluesNecessary, blues)
        greensNecessary = max(greensNecessary, greens)
    }

    return redsNecessary * greensNecessary * bluesNecessary
}

fun main() {
    val inputFileName = "day2/input.txt"
    var totalGamesPower = 0

    File(inputFileName).forEachLine {
        totalGamesPower += computeGamePower(it)
    }

    println(totalGamesPower)
}