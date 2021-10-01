package converter

import java.util.*

fun main() {
    do {
        print("Enter what you want to convert (or exit): ")
        val inString = readLine()!!.lowercase()
        if (inString == "exit") {
            break
        }
        val (n, measureIn, _, measureOut) = inString.split(" ")
        val number = n.toDouble()
        Dispatcher(number, measureIn.lowercase(), measureOut.lowercase())
    } while (true)
}

class Dispatcher(number: Double, from: String, to: String) {
    private fun getMetrics(measure: String) = when (measure) {
        "m", "meter", "meters" -> Triple("M", "meter", "meters")
        "km", "kilometer", "kilometers" -> Triple("M", "kilometer", "kilometers")
        "cm", "centimeter", "centimeters" -> Triple("M", "centimeter", "centimeters")
        "mm", "millimeter", "millimeters"  -> Triple("M", "millimeter", "millimeters")
        "mi", "mile", "miles" -> Triple("M", "mile", "miles")
        "yd", "yard", "yards" -> Triple("M", "yard", "yards")
        "ft", "foot", "feet" -> Triple("M", "foot", "feet")
        "in", "inch", "inches" -> Triple("M", "inch", "inches")
        "g", "gram", "grams" -> Triple("W", "gram", "grams")
        "kg", "kilogram", "kilograms" -> Triple("W", "kilogram", "kilograms")
        "mg", "milligram", "milligrams" -> Triple("W", "milligram", "milligrams")
        "lb", "pound", "pounds" -> Triple("W", "pound", "pounds")
        "oz", "ounce", "ounces" -> Triple("W", "ounce", "ounces")
        else -> null
    }

    init {
        val measureIn = getMetrics(from)
        val measureOut = getMetrics(to)

        if (measureIn == null || measureOut == null) {
            println("Conversion from ${measureIn?.third ?: "???"} to ${measureOut?.third ?: "???"} is impossible")
        } else if (measureIn.first != measureOut.first) {
            println("Conversion from ${measureIn.third} to ${measureOut.third} is impossible")
        } else {
            try {
                val unit =
                    Units(number, Pair(measureIn.second, measureIn.third), Pair(measureOut.second, measureOut.third))
                println(unit)
            } catch (e: IllegalFormatException) {
                println("Conversion from ${measureIn.third} to ${measureOut.third} is impossible")
            }

        }
    }
}

class Units(private val numberIn: Double, measureIn: Pair<String, String>, measureOut: Pair<String, String>) {
    private val numberOut = if (measureIn == measureOut) {
        numberIn
    } else if (convert(measureOut.first) == 0.0) {
        -1.0
    } else {
        numberIn * convert(measureIn.first) / convert(measureOut.first)
    }
    private val nameIn = if (numberIn == 1.0) measureIn.first else measureIn.second
    private val nameOut = if (numberOut == 1.0) measureOut.first else measureOut.second

    /**
     * we count all distances in meters and weight in grams
     */
    private fun convert(measure: String): Double = when (measure) {
        "meter" -> 1.0
        "kilometer" -> 1000.0 // 1 km == 1000 m
        "centimeter" -> 1 / 100.0
        "millimeter" -> 1 / 1000.0
        "mile" -> 1609.35
        "yard" -> 0.9144
        "foot" -> 0.3048
        "inch" -> 0.0254
        "gram" -> 1.0
        "kilogram" -> 1000.0 // 1 kg == 1000 g
        "milligram" -> 1 / 1000.0
        "pound" -> 453.592
        "ounce" -> 28.3495
        else -> 0.0
    }

    override fun toString(): String {
        return "$numberIn $nameIn is $numberOut $nameOut"
    }
}
