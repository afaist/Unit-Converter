import java.lang.IllegalArgumentException

enum class Countries(val currency: String) {
    Germany("Euro"),
    Mali("CFA franc"),
    Dominica("Eastern Caribbean dollar"),
    Canada("Canadian dollar"),
    Spain("Euro"),
    Australia("Australian dollar"),
    Brazil("Brazilian real"),
    Senegal("CFA franc"),
    France("Euro"),
    Grenada("Eastern Caribbean dollar"),
    Kiribati("Australian dollar"),
    NULL("");

    companion object {
        fun compareCurrencies(countryFirst: String, countrySecond: String) =
            try {
                valueOf(countryFirst).currency == valueOf(countrySecond).currency
            } catch (e: IllegalArgumentException) {
                false
            }
    }
}

fun main() = readLine()!!.split(" ").run { println(Countries.compareCurrencies(this[0], this[1])) }
