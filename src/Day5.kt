import java.io.File

fun main() {
    //Read a file
    val input = File("src/Inputs/Day5.txt").readLines()

    var highestSeatId = 0
    val seatIds = mutableListOf<Int>()
    for (boardinPass in input){
        val row = boardinPass.take(7).replace("B", "1").replace("F", "0")
        val column = boardinPass.takeLast(3).replace("R", "1").replace("L", "0")

        val currentSeatId = computeSeatId(row.toInt(2), column.toInt(2))
        seatIds.add(currentSeatId)
        if (currentSeatId > highestSeatId){
            highestSeatId = currentSeatId
        }
    }

    println("Highest seat id: $highestSeatId")

    //Part 2
    seatIds.sort()
    var previousSeatId = seatIds.get(0)
    for(currentSeatId in seatIds){
        if(currentSeatId - previousSeatId >= 2){
            println("Your seat is in between $previousSeatId and $currentSeatId")
        }
        previousSeatId = currentSeatId
    }
}

val MAGIC_NUMBER = 8

fun computeSeatId(row: Int, column: Int) = row * MAGIC_NUMBER + column