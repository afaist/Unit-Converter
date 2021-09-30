package converter

fun main() {
    print("Enter a number and a measure of length: ")
    val (n, measure) = readLine()!!.split(" ")
    val number = n.toDouble()
    val unit =
        when (measure.lowercase()) {
            in "m", "meter", "meters" -> Meters(number)
            in "km", "kilometer", "kilometers" -> Kilometers(number)
            in "cm", "centimeter", "centimeters" -> Centimeters(number)
            in "mm", "millimeter", "millimeters" -> Millimeters(number)
            in "mi", "mile", "miles" -> Miles(number)
            in "yd", "yard", "yards" -> Yards(number)
            in "ft", "foot", "feet" -> Feet(number)
            in "in", "inch", "inches" -> Inches(number)
            else -> Unit(measure)
        }
    println(unit)
}

open class Unit(private val measure: String) {

    override fun toString(): String {
        return "Wrong input. Unknown unit $measure"
    }
}

open class Meters(number: Double) : Unit("") {
    open val numberIn = number
    private val inMeters = number
    open val measure = "meter"
    open val measureIn: String by lazy { "$measure${if (numberIn == 1.0) "" else "s"}" }
    private val measureOut by lazy { "meter${if (number == 1.0) "" else "s"}" }
    override fun toString(): String {
        return "$numberIn $measureIn is $inMeters $measureOut"
    }
}

class Kilometers(number: Double) : Meters(number * 1000) {
    override val numberIn = number
    override val measure = "kilometer"
}

class Centimeters(number: Double) : Meters(number / 100) {
    override val numberIn = number
    override var measure = "centimeter"
}

class Millimeters(number: Double) : Meters(number / 1000) {
    override val numberIn = number
    override var measure = "millimeter"
}

class Miles(number: Double) : Meters(number * 1609.35) {
    override val numberIn = number
    override var measure = "mile"
}

class Yards(number: Double) : Meters(number * 0.9144) {
    override val numberIn = number
    override var measure = "yard"
}

class Feet(number: Double) : Meters(number * 0.3048) {
    override val numberIn = number
    override var measureIn = if (numberIn == 1.0) "foot" else "feet"
}

class Inches(number: Double) : Meters(number * 0.0254) {
    override val numberIn = number
    override var measureIn = if (numberIn == 1.0) "inch" else "inches"
}