import java.io.File
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager


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

fun howManyBagsDoesBagContain(bag: String, dict: HashMap<String, List<String>?>){
    val list = mutableListOf<String>()
    val map = hashMapOf<String, Int>()
    map[bag] = 1
    list.add(bag)
    var stringus = ""
    var plusAdded = false
    var currentParentMultiplier = 1
    val multipliers = mutableListOf<Int>()

    while (!list.isEmpty()){
        if (list.get(0).toCharArray().get(0) == '#'){
            stringus = stringus.take(stringus.length-1)
            stringus += ")+"
            list.removeAt(0)
        } else {

            val currentBag = list[0]
            val temp = list.removeAt(0)

            if (plusAdded) {
                //currentParentMultiplier *= map[temp]!!.toInt()
                //multipliers.add(map[temp]!!.toInt())
                stringus += map[temp]
            } else {
                stringus += "*(" + map[temp]
            }

            var indexShift = 0
            if (dict[currentBag] == null) {
                stringus += "+"
                plusAdded = true
            } else {
                plusAdded = false
                for (bage in dict[currentBag]!!) {
                    val numberOfBags = bage.take(1).toInt()
                    val bagunja = bage.split(" ").takeLast(2).joinToString(" ")
                    map[bagunja] = numberOfBags
                    list.add(indexShift++, bagunja)
                }
                list.add(indexShift, "#")
            }
        }
    }
    val tempst = stringus.takeLast(stringus.length-4)
    println(tempst.take(tempst.length-1))
    val script = ScriptEngineManager()
    val eng: ScriptEngine = script.getEngineByName("JavaScript")
    println(eng.eval(tempst.take(tempst.length-1)))
}