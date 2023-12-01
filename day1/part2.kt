import java.io.File

fun main() {
    val digitsNamesToValues = mapOf(
        "one" to 1,
        "two" to 2,
        "three" to 3,
        "four" to 4,
        "five" to 5,
        "six" to 6,
        "seven" to 7,
        "eight" to 8,
        "nine" to 9
    )
    val digitsLengths = digitsNamesToValues.keys.map { it.length }

    var result = 0

    File(inputFileName).forEachLine { line ->
        var firstDigit: Int = -1
        var lastDigit: Int = -1
        for(i in line.indices) {
            if (line[i].isDigit()) {
                if (firstDigit == -1) {
                    firstDigit = line[i].digitToInt()
                }

                lastDigit = line[i].digitToInt()
            } else {
                val subStrings = digitsLengths.mapNotNull {
                    val lastIndex = i + it
                    if (lastIndex <= line.length) {
                        line.substring(i, lastIndex)
                    } else {
                        null
                    }
                }
                subStrings.forEach {
                    if (digitsNamesToValues.containsKey(it)) {
                        val digit = digitsNamesToValues[it]!!

                        if (firstDigit == -1) {
                            firstDigit = digit
                        }

                        lastDigit = digit
                    }
                }
            }
        }
        result += firstDigit * 10 + lastDigit
    }

    println(result)
}