import java.util.*

fun main() {
    val scanner = Scanner(System.`in`)
    var i = 0
    while (scanner.hasNext()) {
        if (0 < scanner.nextInt()) i++ else break
    }
    println(i)
}