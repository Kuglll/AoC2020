import java.io.File
import java.lang.Exception

fun main() {

    //Read a file
    val input = File("src/Inputs/Day10.txt").readLines()

    val joltageRatings = mutableListOf<Int>()

    for(line in input){
        joltageRatings.add(line.toInt())
    }

    val sortedJoltages = joltageRatings.sorted()

    val highestJoltage = sortedJoltages.get(joltageRatings.lastIndex)

    var oneJoltDifference = 1
    var threeJoltDifference = 1
    var prevJoltage = sortedJoltages.get(0)

    for (joltage in sortedJoltages.takeLast(joltageRatings.size-1)){
        if(joltage - prevJoltage == 1){
            oneJoltDifference++
        } else if(joltage - prevJoltage == 3){
            threeJoltDifference++
        }
        prevJoltage = joltage
    }

    println("Number of 1 jolt difference multiplied with number of 3 jolt differences is: ${oneJoltDifference * threeJoltDifference}")

    val adaptersToProcess = mutableListOf<Adapter>()

    //Keep a pointer to the root
    val rootAdapter = Adapter(0, mutableListOf())
    adaptersToProcess.add(rootAdapter)

    var validChains = 0

    while(adaptersToProcess.size != 0){
        val currentAdapter = adaptersToProcess.get(0)
        adaptersToProcess.removeAt(0)

        var index = sortedJoltages.indexOf(currentAdapter.joltage)

        for(number in index+1..index+3){
            try{
                val tempAdapter = Adapter(sortedJoltages.get(number), mutableListOf())
                if(tempAdapter.joltage - currentAdapter.joltage <= 3){
                    currentAdapter.canConnectTo.add(tempAdapter)
                    adaptersToProcess.add(tempAdapter)
                } else {
                    break
                }
            } catch (e: Exception){
                if(currentAdapter.joltage == highestJoltage){
                    validChains++
                    println("Chain added")
                }
            }
        }
    }

    //countTheChains(rootAdapter)
    println("Number of valid chains is: ${validChains/3}")

}

class Adapter(val joltage: Int, val canConnectTo: MutableList<Adapter>)

//var validChains = 0

fun countTheChains(adapter: Adapter){
    if(adapter.canConnectTo.size == 0){
        //validChains++
        return
    }

    for(adapt in adapter.canConnectTo){
        countTheChains(adapt)
    }
}
















fun isChainValid(sortedJoltages: List<Int>, myDeviceJoltage: Int): Boolean{
    if(sortedJoltages.get(0) > 3) return false
    if(myDeviceJoltage - sortedJoltages.get(sortedJoltages.lastIndex) > 3) return false

    var prevJoltage = sortedJoltages.get(0)
    for(joltage in sortedJoltages.takeLast(sortedJoltages.size-1)){
        if(joltage - prevJoltage > 3){
            return false
        }
        prevJoltage = joltage
    }
    return true
}

