import java.util.*

fun main() {
    var nMax = Int.MIN_VALUE
    val scanner = Scanner(System.`in`)
    while (scanner.hasNext()) {
        val n = scanner.nextInt()
        if (n == 0) break else if (n > nMax) nMax = n
    }
    println(nMax)
}