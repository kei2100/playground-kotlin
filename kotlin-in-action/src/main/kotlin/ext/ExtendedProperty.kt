package ext

val String.lastChar: Char
    get() = this[length - 1]

var StringBuilder.lastChar: Char
    get() = get(length - 1)
    set(value) {
        setCharAt(length - 1, value)
    }

fun main() {
    println("Kotlin".lastChar)
    // n

    val sb = StringBuilder("Kotlin?")
    sb.lastChar = '!'
    println(sb)
    // Kotlin!
}