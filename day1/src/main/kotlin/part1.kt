import java.io.File

const val inputFileName = "day1/input.txt"

fun main() {
    var result = 0

    File(inputFileName).forEachLine {
        var firstDigit: Char? = null
        var lastDigit: Char? = null
        for(c in it) {
            if (c.isDigit()) {
                if (firstDigit == null) {
                    firstDigit = c
                }

                lastDigit = c
            }
        }
        result += (firstDigit.toString() + lastDigit.toString()).toInt()
    }

    println(result)
}