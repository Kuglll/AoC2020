import java.io.File

fun main() {
    //Read a file
    val input = File("src/Inputs/Day1.txt")

    input.forEachLine { number1 ->
        input.forEachLine { number2 ->
            // Part 1
            if (number1.toInt() + number2.toInt() == 2020) {
                println(number1.toInt() * number2.toInt())
            }

            // Part 2
            input.forEachLine { number3 ->
                if (number1.toInt() + number2.toInt() + number3.toInt() == 2020){
                    println(number1.toInt() * number2.toInt() * number3.toInt())
                }
            }
        }
    }
}