import java.io.File

fun main() {

    //Read a file
    val input = File("src/Inputs/Day9.txt").readText()

    val window = mutableListOf<Long>()
    var firstInvalidNumber = 0

    for (number in input.split("\n")){
        if(window.size < 25) {
            window.add(number.toLong())
        } else {
            if(!checkNumber(number.toLong(), window)){
                firstInvalidNumber = number.toInt()
                break
            }
            window.removeAt(0)
            window.add(number.toLong())
        }
    }
    println("First invalid number: $firstInvalidNumber")

    val list = mutableListOf<Long>()

    for(windowSize in 2..input.split("\n").size){ //check all window sizes
        for(number in input.split("\n")){
            list.add(number.toLong())
            //List is of correct size, check if it sums up to 1398413738
            if(list.size == windowSize){
                if(list.sum() == "1398413738".toLong()){
                    printLargestAndSmallestNumberSum(list)
                    break
                }
                list.removeAt(0)
            }else if(list.size < window.size){
                //DO NOTHING
            }
        }
    }

}

fun printLargestAndSmallestNumberSum(list: List<Long>){
    var smallestNumber: Long = list.get(0)
    var largestNumber = list.get(0)

    for(number in list){
        if(number < smallestNumber){
            smallestNumber = number
        }
        if(number > largestNumber){
            largestNumber = number
        }
    }
    println("Sum of largest and smallest number is ${smallestNumber + largestNumber}")
}

fun checkNumber(number: Long, window: List<Long>): Boolean{
    var validNumber = false
    for (value1 in window){
        if(validNumber){
            break
        }
        for (value2 in window){
            if(validNumber){
                break
            }
            if (value1 != value2){
                validNumber = value1 + value2 == number
            }
        }
    }
    return validNumber
}