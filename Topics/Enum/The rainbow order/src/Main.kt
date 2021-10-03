enum class Rainbow {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET
}

fun main() = println(Rainbow.valueOf(readLine()!!.uppercase()).ordinal + 1)
