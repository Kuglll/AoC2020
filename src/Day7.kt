import java.io.File

fun main() {

    val dict = hashMapOf<String, List<String>?>()
    //Read a file
    val input = File("src/Inputs/Day7.txt").readLines()

    for(line in input){

        val mainBag = line.split(" ").take(2).joinToString(" ")

        if(line.contains("no other")){
            dict[mainBag] = null
        } else {
            val list = mutableListOf<String>()
            for (bag in line.split(",")){
                list.add(bag.split(" ").takeLast(4).take(3).joinToString(" "))
            }
            dict[mainBag] = list
        }
    }

    val list = mutableListOf<Int>()
    for(bag in dict.keys){
        foundGoldBag = false
        list.add(doesBagContainShinyGoldBag(bag, dict))
    }
    println("Shiny gold bags: ${list.sum()}")

    println("How much bags does shiny gold bag contain: ${howManyBagsDoesBagContain("shiny gold", dict)}")
}

var foundGoldBag = false

fun doesBagContainShinyGoldBag(bag: String, dict: HashMap<String, List<String>?>): Int{
    if(foundGoldBag){
        return 1
    }
    if (dict[bag] == null){
        return 0
    } else if(checkIfListContainsShinyGoldBag(dict[bag]!!)){
        if(!foundGoldBag){
            foundGoldBag = true
            return 1
        } else {
            return 0
        }
    } else{
        for (bage in dict[bag]!!){
            if (foundGoldBag){
                return 1
            }
            doesBagContainShinyGoldBag(bage.split(" ").takeLast(2).joinToString(" "), dict)
        }
    }
    if (foundGoldBag){
        return 1
    }
    return 0
}

fun checkIfListContainsShinyGoldBag(list: List<String>): Boolean{
    for (item in list){
        if (item.contains("shiny gold")) {
            return true
        }
    }
    return false
}

fun howManyBagsDoesBagContain(bag: String, dict: HashMap<String, List<String>?>): Int{
    val list = mutableListOf<Bagunja>()
    list.add(Bagunja(1, bag, 0, mutableListOf()))

    var sum = 0
    var currentMultipliers = mutableListOf<Int>()
    currentMultipliers.add(1)

    while (!list.isEmpty()){

        val currentBag = list.removeAt(0)
        currentMultipliers = currentMultipliers.take(currentBag.level).toMutableList()
        currentMultipliers.add(currentBag.number)
        sum += currentMultipliers.reduce { acc, i -> acc * i }

        if (dict[currentBag.name] == null) {
            //konec rekurzije
        } else {
            var shiftedIndex = 0
            for (bage in dict[currentBag.name]!!) {
                val numberOfBags = bage.take(1).toInt()
                val bagunjaName = bage.split(" ").takeLast(2).joinToString(" ")
                currentBag.otherBagunje!!.add(Bagunja(numberOfBags, bagunjaName, currentBag.level+1, mutableListOf()))
                list.add(shiftedIndex++, Bagunja(numberOfBags, bagunjaName, currentBag.level+1, mutableListOf()))
            }
        }

    }
    return sum-1
}

class Bagunja(val number: Int, val name: String, val level: Int, val otherBagunje: MutableList<Bagunja>?)