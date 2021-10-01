fun main() = Array(2) { readLine()!! }.run {
    println("Symbol # ${this[1]} of the string \"${this[0]}\" is '${this[0][this[1].toInt() - 1]}'")
}