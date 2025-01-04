data class Num(val value: Int) {
    operator fun plus(num: Num): Num {
        return Num(value + num.value)
    }
}

fun main() {
    val num1 = Num(1)
    val num2 = Num(2)
    val num3 = num1 + num2
    println(num3.value)
    // 3
}