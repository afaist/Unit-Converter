fun main() {
    val (a, b, c) = IntArray(3) { readLine()!!.toInt() }
    println(a in b..c)
}