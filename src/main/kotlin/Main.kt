/* Amboy, Nino Andrey H.
   CS 3202 */

import java.math.BigDecimal
import kotlin.math.pow
import kotlin.math.sqrt
import kotlin.system.exitProcess

fun main() {
    var operation: String? = null
    var result: Double? = null

    while (true) {
        print("> ")
        val userInput = readlnOrNull()

        if (userInput == "q") {
            println("\nExiting calculator...")
            exitProcess(0)
        }

        val parts = userInput?.split(Regex("(?<=[+\\-*/%$^!\\s])|(?=[+\\-*/%$^!\\s])"))

        if (parts == null || parts.size < 3) {
            operation = null
            result = null
            println("Invalid Input\n")
            continue
        }

        var num1 = 0.0
        var num2 = 0.0
        var i = 0

        try {
            num1 = parts[i].toDouble()
            i++
        } catch (e: NumberFormatException) {
            operation = null
            result = null
            println("Invalid Input\n")
            continue
        }

        while (i < parts.size - 1) {
            operation = parts[i]

            if (operation !in listOf("+", "-", "*", "/", "%", "$", "!", "^")) {
                operation = null
                result = null
                println("Invalid Input\n")
                break
            }
            if (operation == "%" || operation == "$" || operation == "!") {
                num2 = 0.0
                } else {
                try {
                    num2 = parts[i + 1].toDouble()
                } catch (e: NumberFormatException) {
                    operation = null
                    result = null
                    println("Invalid Input\n")
                    break
                }
            }
            
            result = when (operation) {
                "+" -> num1 + num2
                "-" -> num1 - num2
                "*" -> num1 * num2
                "/" -> num1 / num2
                "%" -> num1 * 0.01
                "$" -> sqrt(num1)
                "!" -> factorial(num1)
                "^" -> num1.pow(num2)
                else -> throw IllegalArgumentException("Invalid operation")
            }

            num1 = result
            i += 2
        }

        val formattedResult = formatResult(result)
        println("$formattedResult\n")
    }
}

fun factorial(n: Double): Double {
    var result = 1.0
    for (i in 2..n.toInt()) {
        result *= i
    }
    return result
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

