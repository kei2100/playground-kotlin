data class HumanData(
    val id: String,
    val name: String,
)

fun main() {
    val humanData1 = HumanData("1", "Kotlin")
    val humanData2 = HumanData("1", "Kotlin")

    println(humanData1 == humanData2)
    // true
}