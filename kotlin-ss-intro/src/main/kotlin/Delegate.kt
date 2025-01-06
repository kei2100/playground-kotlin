interface Printable {
    fun print()
}

class Printer : Printable {
    override fun print() {
        println("print by Printer")
    }
}

class PrinterDelegate(private val printer: Printer) : Printable by printer

fun main() {
    val pd = PrinterDelegate(Printer())
    pd.print()
    // print by Printer
}