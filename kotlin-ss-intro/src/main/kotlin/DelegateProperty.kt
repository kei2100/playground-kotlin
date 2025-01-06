import kotlin.reflect.KProperty

class DelegateProperty<T> {
    private var value: T? = null

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        println("getValue: ${property.name}")
        return value!!
    }
    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        println("setValue: ${property.name}")
        this.value = value
    }
}

class Person2 {
    var name: String by DelegateProperty()
    var age: Int by DelegateProperty()
}

fun main() {
    val person = Person2()

    person.name = "Kotlin"
    // setValue: name

    println(person.name)
    // getValue: name
    // Kotlin

    person.age = 20
    // setValue: age

    println(person.age)
    // getValue: age
    // 20
}