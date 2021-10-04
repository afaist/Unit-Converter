fun main() {
    val (a, b, c, d, e) = IntArray(5) { readLine()!!.toInt() }
    println(e in a..b && e in c..d)
}