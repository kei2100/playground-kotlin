package ext

fun String.lastChar(): Char = this[this.length - 1]

fun String.lastCharUpper(): Char = this.lastChar().uppercaseChar()

open class View
class Button : View()

fun View.showOff() = println("I'm a view!")
fun Button.showOff() = println("I'm a button!")

fun main() {
    println("Kotlin".lastChar())
    // n
    println("Kotlin".lastCharUpper())
    // N

    println("-----")

    val view: View = Button()
    view.showOff()
    // I'm a view!
}