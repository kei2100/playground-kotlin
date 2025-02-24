package basics.idioms

import java.io.StringReader

fun main() {
    println("Hello World!!")
}

// -----
fun safeConvertToInt(s: String): Int? {
    return s.toIntOrNull()
}

// -----
fun iterateRangeCloseEnded(): Int {
    var sum = 0
    for (i in 1..10) {
        sum += i
    }
    return sum
}

fun iterateRangeOpenEnded(): Int {
    var sum = 0
    for (i in 1..<10) {
        sum += i
    }
    return sum
}

// -----
class LazyProperty {
    val lazy: String by lazy {
        "Lazy property"
    }
}

// -----
@JvmInline
value class EmployeeId(private val id: String)

// @JvmInline は JVM Backend のときのみ必要で、 Kotlin/JS では不要
@JvmInline
value class CustomerId(private val id: String)

// -----
fun tryWithResource(): String {
    // .use で try-with-resources ができる
    StringReader("Hello, World!").use {
        return it.readText()
    }
}

// -----
fun swapTwoVariables(): Pair<Int, Int> {
    var a = 1
    var b = 2
    a = b.also { b = a }
    return Pair(a, b)
}

// -----
fun useTODO() {
    // TODO は NotImplementedError をスローする
    TODO("will throws NotImplementedError")
}
