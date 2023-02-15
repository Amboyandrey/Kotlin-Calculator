import java.math.BigDecimal
import java.util.Scanner

fun main() {
    val input = Scanner(System.`in`)
    var num1 = 0.0
    var num2 = 0.0
    var operation: String? = null
    var result: Double? = null

    println("Press r to Reset\nPress q to Quit")
    while (true) {
        print("\nEnter the first number: ")
        val userInput = input.next()

        if (userInput == "q") {
            println("\nExiting calculator...")
            break
        } else if (userInput == "r") {
            num1 = 0.0
            num2 = 0.0
            operation = null
            result = null
            println("\nCalculator reset")
            continue
        }

        num1 = userInput.toDouble()

        print("Enter the operation (+, -, *, /): ")
        operation = input.next()

        if (operation == "q") {
            println("\nExiting calculator...")
            break
        } else if (operation == "r") {
            num1 = 0.0
            num2 = 0.0
            operation = null
            result = null
            println("\nCalculator reset")
            continue
        }

        print("Enter the second number: ")
        val userInput2 = input.next()

        if (userInput2 == "q") {
            println("\nExiting calculator...")
            break
        } else if (userInput2 == "r") {
            num1 = 0.0
            num2 = 0.0
            operation = null
            result = null
            println("\nCalculator reset")
            continue
        }

        num2 = userInput2.toDouble()

        result = when (operation) {
            "+" -> num1 + num2
            "-" -> num1 - num2
            "*" -> num1 * num2
            "/" -> num1 / num2
            else -> throw IllegalArgumentException("Invalid operation")
        }

        val formattedResult = formatResult(result)
        println("Result: $formattedResult")
    }
}

fun formatResult(result: Double?): String {
    return result?.let {
        if (it % 1 == 0.0) {
            it.toInt().toString()
        } else {
            BigDecimal(it.toString()).setScale(3, BigDecimal.ROUND_HALF_UP)
                .stripTrailingZeros().toPlainString()
        }
    } ?: "undefined"
}
