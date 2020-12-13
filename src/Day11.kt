import java.io.File
import java.lang.Exception
import kotlin.math.sign

fun main() {

    //Read a file
    val input = File("src/Inputs/Day11.txt").readLines()

    var grid = mutableListOf<List<String>>()

    for(line in input){
        val column = mutableListOf<String>()
        for(char in line){
            column.add(char.toString())
        }
        grid.add(column)
    }

    var seatWasChanged = true

    while (seatWasChanged){
        seatWasChanged = false
        val tempGrid = mutableListOf<List<String>>()
        for(row in 0..grid.size-1){
            val tempColumn = mutableListOf<String>()
            for(column in 0..grid.get(row).size-1){
                if(grid.get(row).get(column) == "."){
                    //Write .
                    tempColumn.add(".")
                }
                else if(grid.get(row).get(column) == "L"){
                    //empty might become occupied
                    if(shouldBecomeOccupiedPart2(row, column, grid)){
                        //Write #
                        tempColumn.add("#")
                        seatWasChanged = true
                    } else {
                        //Leave L
                        tempColumn.add("L")
                    }
                } else if(grid.get(row).get(column) == "#"){
                    //occupied might become empty
                    if(shouldBecomeFreePart2(row, column, grid)){
                        //Write L
                        tempColumn.add("L")
                        seatWasChanged = true
                    }else{
                        //Leave #
                        tempColumn.add("#")
                    }
                }
            }
            tempGrid.add(tempColumn)
        }
        grid = tempGrid
    }

    var occupiedSeats = 0
    for(row in grid){
        for(column in row){
            if (column == "#"){
                occupiedSeats++
            }
        }
    }

    println("Number of occupied seats at the end is: $occupiedSeats")



}

fun shouldBecomeOccupied(x: Int, y: Int, grid: List<List<String>>) = getNumberOfAdjecentOccupiedSeats(x, y, grid) == 0

fun shouldBecomeFree(x: Int, y: Int, grid: List<List<String>>) = getNumberOfAdjecentOccupiedSeats(x, y, grid) >= 4

fun getNumberOfAdjecentOccupiedSeats(x: Int, y: Int, grid: List<List<String>>): Int{
    var occupiedSeats = 0

    try {
        if(grid.get(x-1).get(y)=="#") occupiedSeats++
    }catch (e:Exception){}
    try {
        if(grid.get(x+1).get(y)=="#") occupiedSeats++
    }catch (e:Exception){}
    try {
        if(grid.get(x).get(y-1)=="#") occupiedSeats++
    }catch (e:Exception){}
    try {
        if(grid.get(x).get(y+1)=="#") occupiedSeats++
    }catch (e:Exception){}
    try {
        if(grid.get(x-1).get(y-1)=="#") occupiedSeats++
    }catch (e:Exception){}
    try {
        if(grid.get(x-1).get(y+1)=="#") occupiedSeats++
    }catch (e:Exception){}
    try {
        if(grid.get(x+1).get(y-1)=="#") occupiedSeats++
    }catch (e:Exception){}
    try {
        if(grid.get(x+1).get(y+1)=="#") occupiedSeats++
    }catch (e:Exception){}

    return occupiedSeats
}

fun shouldBecomeFreePart2(x: Int, y: Int, grid: List<List<String>>) = getNumberOfAdjecentOccupiedSeatsPart2(x, y, grid) >= 5

fun shouldBecomeOccupiedPart2(x: Int, y: Int, grid: List<List<String>>) = getNumberOfAdjecentOccupiedSeatsPart2(x, y, grid) == 0

fun getNumberOfAdjecentOccupiedSeatsPart2(x: Int, y: Int, grid: List<List<String>>): Int{
    var occupiedSeats = 0

    try {
        for(index in y-1 downTo 0){
            if(grid.get(x).get(index)=="#"){
                occupiedSeats++
                break
            } else if(grid.get(x).get(index)=="L"){
                break
            }
        }
    }catch (e:Exception){}
    try {
        for(index in y+1..grid.get(x).size){
            if(grid.get(x).get(index)=="#"){
                occupiedSeats++
                break
            } else if(grid.get(x).get(index)=="L"){
                break
            }
        }
    }catch (e:Exception){}
    try {
        for(index in x-1 downTo 0){
            if(grid.get(index).get(y)=="#"){
                occupiedSeats++
                break
            } else if(grid.get(index).get(y)=="L"){
                break
            }
        }
    }catch (e:Exception){}
    try {
        for(index in x+1..grid.size){
            if(grid.get(index).get(y)=="#"){
                occupiedSeats++
                break
            } else if(grid.get(index).get(y)=="L"){
                break
            }
        }
    }catch (e:Exception){}
    try {
        for(index in 1..grid.size){
            if(grid.get(x-index).get(y-index)=="#"){
                occupiedSeats++
                break
            } else if(grid.get(x-index).get(y-index)=="L"){
                break
            }
        }
    }catch (e:Exception){}
    try {
        for(index in 1..grid.size){
            if(grid.get(x+index).get(y+index)=="#"){
                occupiedSeats++
                break
            } else if(grid.get(x+index).get(y+index)=="L"){
                break
            }
        }
    }catch (e:Exception){}
    try {
        for(index in 1..grid.size){
            if(grid.get(x-index).get(y+index)=="#"){
                occupiedSeats++
                break
            } else if(grid.get(x-index).get(y+index)=="L"){
                break
            }
        }
    }catch (e:Exception){}
    try {
        for(index in 1..grid.size){
            if(grid.get(x+index).get(y-index)=="#"){
                occupiedSeats++
                break
            } else if(grid.get(x+index).get(y-index)=="L"){
                break
            }
        }
    }catch (e:Exception){}

    return occupiedSeats
}