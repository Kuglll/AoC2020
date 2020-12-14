import java.io.File
import kotlin.math.abs

fun main() {

    //Read a file
    val input = File("src/Inputs/Day12.txt").readLines()

    val compass = listOf('E', 'S', 'W', 'N')

    var currentDirection = 'E'
    var x = 0
    var y = 0

    for(instruction in input){
        if(instruction.get(0) == 'F'){
             when(currentDirection){
                 'E' -> y += instruction.takeLast(instruction.length-1).toInt()
                 'S' -> x -= instruction.takeLast(instruction.length-1).toInt()
                 'N' -> x += instruction.takeLast(instruction.length-1).toInt()
                 'W' -> y -= instruction.takeLast(instruction.length-1).toInt()
             }
        } else if(instruction.get(0) == 'N'){
            x += instruction.takeLast(instruction.length-1).toInt()
        } else if(instruction.get(0) == 'S'){
            x -= instruction.takeLast(instruction.length-1).toInt()
        } else if(instruction.get(0) == 'E'){
            y += instruction.takeLast(instruction.length-1).toInt()
        } else if(instruction.get(0) == 'W'){
            y -= instruction.takeLast(instruction.length-1).toInt()
        } else if(instruction.get(0) == 'L'){
            val step = instruction.takeLast(instruction.length-1).toInt()/90
            if(step == 1){
                //3 v desno
                currentDirection = compass.get((compass.indexOf(currentDirection) + 3) % (compass.size))
            } else if(step == 2){
                //2 v desno
                currentDirection = compass.get((compass.indexOf(currentDirection) + 2) % (compass.size))
            } else if(step == 3){
                //1 v desno
                currentDirection = compass.get((compass.indexOf(currentDirection) + 1) % (compass.size))
            }
        } else if(instruction.get(0) == 'R'){
            currentDirection = compass.get((compass.indexOf(currentDirection) + instruction.takeLast(instruction.length-1).toInt()/90) % (compass.size))
        }
    }

    println("Manhattan distance between ship and ending point is: ${abs(x) + abs(y)}")

    //Part 2
    var waypointX = 10
    var waypointY = 1
    var distanceTraveledX = 0
    var distanceTraveledY = 0

    for(instruction in input){
        if(instruction.get(0) == 'F'){
            distanceTraveledX += instruction.takeLast(instruction.length-1).toInt() * waypointX
            distanceTraveledY += instruction.takeLast(instruction.length-1).toInt() * waypointY
        } else if(instruction.get(0) == 'N'){
            waypointY += instruction.takeLast(instruction.length-1).toInt()
        } else if(instruction.get(0) == 'S'){
            waypointY -= instruction.takeLast(instruction.length-1).toInt()
        } else if(instruction.get(0) == 'E'){
            waypointX += instruction.takeLast(instruction.length-1).toInt()
        } else if(instruction.get(0) == 'W'){
            waypointX -= instruction.takeLast(instruction.length-1).toInt()
        } else if(instruction.get(0) == 'L'){

            val step = instruction.takeLast(instruction.length-1).toInt()/90
            if(step == 3){
                val tmp = waypointY
                waypointY = waypointX * -1
                waypointX = tmp
            } else if(step == 2){
                waypointX *= -1
                waypointY *= -1
            } else if(step == 1){
                val tmp = waypointY
                waypointY = waypointX
                waypointX = tmp * -1
            }
        } else if(instruction.get(0) == 'R'){

            val step = instruction.takeLast(instruction.length-1).toInt()/90
            if(step == 1){
                val tmp = waypointY
                waypointY = waypointX * -1
                waypointX = tmp
            } else if(step == 2){
                waypointX *= -1
                waypointY *= -1
            } else if(step == 3){
                val tmp = waypointY
                waypointY = waypointX
                waypointX = tmp * -1
            }
        }
    }

    println("Manhattan distance between ship and ending point is: ${abs(distanceTraveledX) + abs(distanceTraveledY)}")

}
