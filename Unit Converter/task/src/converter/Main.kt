package converter

const val LENGTH = 'L'
const val WEIGHT = 'W'
const val TEMPERATURE = 'T'

enum class Units(
    val shortName: String, val singular: String, val plural: String,
    val multiplier: Double, val difference: Double = 0.0,
) {
    L1("m", "meter", "meters", 1.0),
    L2("km", "kilometer", "kilometers", 1000.0),
    L3("cm", "centimeter", "centimeters", 0.01),
    L4("mm", "millimeter", "millimeters", 0.001),
    L5("mi", "mile", "miles", 1609.35),
    L6("yd", "yard", "yards", 0.9144),
    L7("ft", "foot", "feet", 0.3048),
    L8("in", "inch", "inches", 0.0254),

    W1("g", "gram", "grams", 1.0),
    W2("kg", "kilogram", "kilograms", 1000.0),
    W3("mg", "milligram", "milligrams", 0.001),
    W4("lb", "pound", "pounds", 453.592),
    W5("oz", "ounce", "ounces", 28.3495),

    T1("k", "kelvin", "kelvins", 1.0),
    T2("c", "degree Celsius", "degrees Celsius", 1.0, 273.15),
    T3("dc", "degree Celsius", "degrees Celsius", 1.0, 273.15),
    T4("celsius", "degree Celsius", "degrees Celsius", 1.0, 273.15),
    T5("f", "degree Fahrenheit", "degrees Fahrenheit", 5 / 9.0, 459.67),
    T6("df", "degree Fahrenheit", "degrees Fahrenheit", 5 / 9.0, 459.67),
    T7("fahrenheit", "degree Fahrenheit", "degrees Fahrenheit", 5 / 9.0, 459.67),
    UNKNOWN("???", "???", "???", 1.0);

    companion object {
        fun getUnit(unit: String): Units {
            return values().find { it.shortName == unit || it.singular == unit || it.plural == unit }
                ?: UNKNOWN
        }
    }
}

fun main() {
    do {
        print("Enter what you want to convert (or exit): ")
        val inString = readLine()!!.lowercase()
        if (inString == "exit") {
            break
        }
        val triple = parseInput(inString) ?: continue
        val number = triple.first
        val unitIn = Units.getUnit(triple.second)
        val unitOut = Units.getUnit(triple.third)

        if ((unitIn == Units.UNKNOWN && unitOut == Units.UNKNOWN) || unitIn.name.first() != unitOut.name.first()) {
            println("Conversion from ${unitIn.plural} to ${unitOut.plural} is impossible")
        } else if (number < 0 && unitIn.name.first() != TEMPERATURE) {
            print(if (unitIn.name.first() == WEIGHT) {
                "Weight"
            } else if (unitIn.name.first() == LENGTH) {
                "Length"
            } else {
                "???"
            })
            println(" shouldn't be negative")
        } else {
            val numberOut = if (unitIn == unitOut) {
                number
            } else {
                (number + unitIn.difference) * unitIn.multiplier / unitOut.multiplier - unitOut.difference
            }
            val nameIn = if (number == 1.0) unitIn.singular else unitIn.plural
            val nameOut = if (numberOut == 1.0) unitOut.singular else unitOut.plural
            println("$number $nameIn is $numberOut $nameOut")
        }
    } while (true)
}

/**
 * parsing the command line
 */
fun parseInput(inString: String): Triple<Double, String, String>? {
    try {
        val query = inString.lowercase().split(" ")
        var i = 0
        val number = query[i++].toDouble() // an interruption may occur if the number format is incorrect
        // first unit name
        var measureIn = query[i++]
        if (measureIn.contains("degree")) {
            measureIn = "$measureIn ${query[i++].run { this[0].uppercaseChar() + this.drop(1) }}"
        }
        // second unit name
        var measureOut = query[++i]
        if (measureOut.contains("degree")) {
            measureOut = "$measureOut ${query[++i].run { this[0].uppercaseChar() + this.drop(1) }}"
        }
        return Triple(number, measureIn, measureOut)
    } catch (e: NumberFormatException) {
        println("Parse error")
        return null
    }
}