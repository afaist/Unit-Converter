fun main() {
    var cnt = 0
    var n: Int
    do {
        n = readLine()!!.toInt()
        cnt += n
    } while (n != 0)
    println(cnt)
}