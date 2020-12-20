import java.io.File

fun main() {

    //Read a file
    val input = File("src/Inputs/Day13.txt").readLines()

    val arrivalTime = input.get(0).toInt()
    val busses = mutableListOf<Int>()
    for(bus in input.get(1).split(",")){
        if(bus != "x"){
            busses.add(bus.toInt())
        }
    }

    val possibleDepartueTimes = mutableListOf<Bus>()
    for(bus in busses){
        var factor = 1
        while (true){
            if(bus * factor > arrivalTime){
                possibleDepartueTimes.add(Bus(bus, bus*factor))
                break
            }
            factor++
        }
    }

    var minimumBus = possibleDepartueTimes.get(0)
    for(bus in possibleDepartueTimes){
        if(bus.waitTime < minimumBus.waitTime){
            minimumBus = bus
        }
    }

    println("Result part 1: ${minimumBus.id * (minimumBus.waitTime - arrivalTime)}")

}

class Bus(val id: Int, val waitTime: Int)