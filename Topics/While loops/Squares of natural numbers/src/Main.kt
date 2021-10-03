fun main() {
    val n = readLine()!!.toInt()
    var i = 1
    var k = i * i++
    while (k <= n) {
        println(k)
        k = i * i++
    }
}