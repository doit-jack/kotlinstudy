package chapter3

import atomictest.eq
import atomictest.trace


val numbers = mapOf(
    1 to "one", 2 to "two", 3 to "three"
)

fun ordinal(i: Int): String =
    when (i) {
        1 -> "1st"
        2 -> "2st"
        else -> numbers.getValue(i)
    }

fun main() {
    ordinal(2) eq "2st"
    ordinal(3) eq "three"
}

class Coordinates {
    var x: Int = 0
        set(value) {
            trace("x gets $value")
            field = value
        }
    var y: Int = 0
        set(value) {
            trace("y gets $value")
            field = value
        }
    override fun toString() = "($x, $y)"
}

fun processInputs(inputs: List<String>) {
    val coordinates = Coordinates()
    for (input in inputs) {
        when (input) {
            "up", "u" -> coordinates.y--
            "down", "d" -> coordinates.y++
            "left", "l" -> coordinates.x--
            "right", "r" -> {
                trace("Moving right")
                coordinates.x++
            }
            "nowhere" -> {}
            "exit" -> return
            else -> trace("bad input: $input")
        }
    }
}

fun bmiMetricWithWhen(
    kg: Double,
    heightM: Double
): String {
    val bmi = kg / (heightM * heightM)
    return when {
        bmi < 18.5 -> "Underweight"
        else -> "overWeight"
    }

}
