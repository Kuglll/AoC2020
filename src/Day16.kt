import java.io.File
import java.lang.Exception

fun main() {

    //Read a file
    val input = File("src/Inputs/Day16.txt").readLines()
    val ranges = mutableListOf<String>()
    var yourTicket = false
    var nearbyTicket = false
    val incorrentFields = mutableListOf<Int>()

    //part 2 vars
    val tickets = mutableListOf<String>()
    val fields = mutableListOf<Field>()

    for(line in input){
        if (line == "") {
            //skip
        }else if(line.contains("your ticket")  || yourTicket){
            if(yourTicket){
                tickets.add(line)
            }
            yourTicket = !yourTicket
        } else if(line.contains("nearby") || nearbyTicket){
            if(nearbyTicket){
                incorrentFields.add(validateNearbyTicket(line, ranges))
                tickets.add(line)
            }
            nearbyTicket = true
        } else{
            val firstRange = line.split(":").get(1).split("or").get(0).trim()
            ranges.add(firstRange)
            val secondRange = line.split(":").get(1).split("or").get(1).trim()
            ranges.add(secondRange)

            fields.add(Field(line.split(":").get(0), firstRange, secondRange))

        }
    }

    println("Ticket error scanning rate is: ${incorrentFields.reduce { acc, i -> acc + i }}")

    //Part2
    val valuesOnTicket = tickets.get(0).split(",").size
    val map = hashMapOf<Int, MutableList<Field>>() //key is field position and value is list of all possible lists

    for(currentValue in 0..valuesOnTicket-1){ //go field by field
        val allFields = fields.toMutableList()
        for(ticket in tickets){
            if(validateNearbyTicketPart2(ticket, ranges)){
                //Ticket is valid
                val currentValueOnTicket = ticket.split(",").get(currentValue).toInt() //validate this in all ranges
                var fieldToRemove: Field? = null
                for(field in allFields){
                    if(currentValueOnTicket !in field.firstRange.split("-").get(0).toInt()..field.firstRange.split("-").get(1).toInt()
                    && currentValueOnTicket !in field.secondRange.split("-").get(0).toInt()..field.secondRange.split("-").get(1).toInt()){
                        fieldToRemove = field
                        break
                    }
                }
                fieldToRemove?.let {
                    allFields.remove(it)
                }
            }
        }
        map[currentValue] = allFields
    }

    val fieldPositions = hashMapOf<String, Int>()

    var x = 0
    while(x < 20){
        val currentItem = map.entries.sortedBy { it.value.size }.get(x)
        val itemToRemvoe = currentItem.value.get(0)
        fieldPositions[currentItem.value.get(0).name] = currentItem.key
        for(mapList in map.values.sortedBy { it.size }){
            try {
                if(itemToRemvoe in mapList){
                    mapList.remove(itemToRemvoe)
                }
            }catch (e: Exception){
                //skip
            }
        }
        x++
    }

    var finalResult: Long = 1
    val myTICket = tickets.get(0).split(",")
    for(key in fieldPositions.keys){
        if(key.contains("departure")){
            finalResult *= myTICket.get(fieldPositions[key]!!).toLong()
        }
    }

    println("Multiply values that start with departue: $finalResult")

}

class Field(val name: String, val firstRange: String, val secondRange: String)

fun validateNearbyTicket(ticket: String, ranges: List<String>): Int{
    ticket.split(",").map {
        var validForAtLeastOneRange = false
        for(range in ranges){
            if(it.toInt() in range.split("-").get(0).toInt()..range.split("-").get(1).toInt()){
                validForAtLeastOneRange = true
                break
            }
        }
        if(!validForAtLeastOneRange) return it.toInt()
    }
    return 0
}

fun validateNearbyTicketPart2(ticket: String, ranges: List<String>): Boolean{
    ticket.split(",").map {
        var validForAtLeastOneRange = false
        for(range in ranges){
            if(it.toInt() in range.split("-").get(0).toInt()..range.split("-").get(1).toInt()){
                validForAtLeastOneRange = true
                break
            }
        }
        if(!validForAtLeastOneRange) return false
    }
    return true
}