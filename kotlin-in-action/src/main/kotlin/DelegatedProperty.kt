import kotlin.reflect.KProperty

class PropertyAccessLogger<T>(private var propValue: T) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        println("getValue: ${property.name}")
        return propValue
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        println("setValue: ${property.name} = $value")
        propValue = value
    }
}

class MyPerson(
    name: String,
    age: Int
) {
    var name: String by PropertyAccessLogger(name)
    var age: Int by PropertyAccessLogger(age)
}

fun main() {
    val person = MyPerson("Alice", 29)
    println(person.name)
    // getValue: name
    // Alice

    person.age = 30
    // setValue: age = 30
    println(person.age)
    // getValue: age
    // 30
}
