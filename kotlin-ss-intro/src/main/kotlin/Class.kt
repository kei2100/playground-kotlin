class Human(private val name: String) {
    fun sayHello() {
        println("Hello, $name!")
    }
}

fun main() {
    val human = Human("Kotlin")
    human.sayHello()
    // Hello, Kotlin!
}