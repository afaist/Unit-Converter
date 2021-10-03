enum class Rainbow {
    RED, ORANGE, YELLOW, GREEN, BLUE, INDIGO, VIOLET;

    companion object {
        fun contains(color: String): Boolean = values().any { it.name == color.uppercase() }
    }
}

fun main() = println(Rainbow.contains(readLine()!!))