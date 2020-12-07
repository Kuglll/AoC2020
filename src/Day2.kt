import java.io.File

fun main() {
    //Read a file
    val input = File("src/Inputs/Day2.txt")
    var validPasswords = 0
    var validPasswordsPart2 = 0
    input.forEachLine { line ->
        val split = line.split(":")
        val password = split.get(1).trim()
        val rules = split.get(0).split(" ")
        val letterToBeChecked = rules.get(1).toCharArray().get(0)
        val span = rules.get(0).split("-")
        val minOccurrences = span.get(0).toInt()
        val maxOccurrences = span.get(1).toInt()

        var actualOccurrences = 0
        for(char in password){
            if(char == letterToBeChecked){
                actualOccurrences++
            }
        }
        if(actualOccurrences in minOccurrences..maxOccurrences){
            validPasswords++
        }

        // Part 2
        val firstIndex = minOccurrences
        val secondIndex = maxOccurrences
        if (password.get(firstIndex-1) == letterToBeChecked && password.get(secondIndex-1) == letterToBeChecked){
            validPasswordsPart2--
        }
        if (password.get(firstIndex-1) == letterToBeChecked || password.get(secondIndex-1) == letterToBeChecked){
            validPasswordsPart2++
        }

    }
    println("Valid password part 1: " + validPasswords)
    println("Valid passwords part 2: " + validPasswordsPart2)
}