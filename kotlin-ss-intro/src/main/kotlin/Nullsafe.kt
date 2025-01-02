package org.example

fun main() {
    printMessageLength1("Hello")
    printMessageLength1(null)
//    Message length: 5

    printMessageLength2("Hello")
    printMessageLength2(null)
//    Message length: 5

    printMessageLength3("Hello")
    printMessageLength3(null)
//    Message length: 5
//    Message length: null

    printMessageLength4("Hello")
//    Message length: 5
    try {
        printMessageLength4(null)
    } catch (e: NullPointerException) {
        println("Caught KotlinNullPointerException")
        // Caught KotlinNullPointerException
    }
}

fun printMessageLength1(message: String?) {
    if (message == null) {
        return
    }
    println("Message length: ${message.length}")
}

fun printMessageLength2(message: String?) {
    message ?: return // ?: is the Elvis operator
    println("Message length: ${message.length}")
}

fun printMessageLength3(message: String?) {
    println("Message length: ${message?.length}")
}

fun printMessageLength4(message: String?) {
    println("Message length: ${message!!.length}") // 強制アンラップ。nullの場合は実行時エラーが発生する
}
