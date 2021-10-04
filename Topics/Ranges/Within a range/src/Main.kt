fun main() = IntArray(5) { readLine()!!.toInt() }.run {
    println(when (this[4]) {
        in this[0]..this[1], in this[2]..this[3] -> "true"
        else -> "false"
    })
}
