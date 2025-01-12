package ext

fun String.lastChar(): Char = this[this.length - 1]

fun String.lastCharUpper(): Char = this.lastChar().uppercaseChar()

fun main() {
    println("Kotlin".lastChar())
    // n
    println("Kotlin".lastCharUpper())
    // N
}