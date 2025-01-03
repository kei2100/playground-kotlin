class Human(private val name: String) {
    fun sayHello() {
        println("Hello, $name!")
    }
}

class Person {
    var name: String = ""
    val lowerName: String
        get() = name.lowercase() // 拡張プロパティ
}

fun main() {
    val human = Human("Kotlin")
    human.sayHello()
    // Hello, Kotlin!

    val person = Person()
    person.name = "Kotlin"
    println(person.name)
    // Kotlin
    println(person.lowerName)
    // kotlin
}

