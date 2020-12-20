import java.io.File
import kotlin.math.pow

fun main() {

    //Read a file
    val input = File("src/Inputs/Day14.txt").readLines()

    val map = hashMapOf<Int, Long>()
    var currentMask = "opa"

    for(instruction in input){
        if(instruction.split(" ").get(0) == "mask"){
            currentMask = instruction.split(" ").get(2)
        } else {
            val address = instruction.split("=").get(0).split("[").get(1)
            val realAddres = address.take(address.length-2)
            val value = instruction.split("=").get(1).trim().toInt()
            val valueBinary = Integer.toBinaryString(value)

            var realValueBinary = valueBinary
            for (number in 0..36){
                if(realValueBinary.length != 36){
                    realValueBinary = "0".repeat(number) + valueBinary
                }
            }

            var valueToBeWritten = ""
            realValueBinary.zip(currentMask) { valuee, mask ->
                if(mask.equals('X')){
                    valueToBeWritten += valuee
                } else if(mask.equals('1')){
                    valueToBeWritten += '1'
                } else if(mask.equals('0')){
                    valueToBeWritten += '0'
                }
            }

            map[realAddres.toInt()] = valueToBeWritten.toLong(2)

            //add something to memory
        }
    }

    println("Total sum at the end is: ${map.values.reduce { acc, i -> acc + i }}")


    //part 2

    val mapPart2 = hashMapOf<Long, Long>()
    var currentMaskPart2 = "opa"

    for(instruction in input){
        if(instruction.split(" ").get(0) == "mask"){
            currentMaskPart2 = instruction.split(" ").get(2)
        } else {
            val address = instruction.split("=").get(0).split("[").get(1)
            val realAddres = address.take(address.length-2)
            val value = instruction.split("=").get(1).trim().toLong()

            val addressBinary = Integer.toBinaryString(realAddres.toInt())

            var realAddressBinary = addressBinary
            for (number in 0..36){
                if(realAddressBinary.length != 36){
                    realAddressBinary = "0".repeat(number) + addressBinary
                }
            }

            var adressFinale = ""
            realAddressBinary.zip(currentMaskPart2) { valuee, mask ->
                if(mask.equals('X')){
                    adressFinale += 'X'
                } else if(mask.equals('1')){
                    adressFinale += '1'
                } else if(mask.equals('0')){
                    adressFinale += valuee
                }
            }

            val addressList = mutableListOf<String>()
            //obtain all addresses and write the damn value
            //address example 000000000000000000000000000000X1101X
            val numberOfXiinAdress = adressFinale.count{ "X".contains(it) }
            //there will be 2^numberOfXiinAdress adresses to write to
            for(number in 0..2.toDouble().pow(numberOfXiinAdress).toInt()-1){
                val bitsToFillin = String.format("%${numberOfXiinAdress}s", Integer.toBinaryString(number)).replace(' ', '0')

                var currentAddressToAdd = adressFinale
                var indexOfBits = 0

                currentAddressToAdd.mapIndexed { index, c ->
                    if(c.equals('X')){
                        currentAddressToAdd = currentAddressToAdd.substring(0, index) + bitsToFillin.get(indexOfBits++) + currentAddressToAdd.substring(index + 1)
                    }
                }

                addressList.add(currentAddressToAdd)
            }



            for(addresss in addressList){
                mapPart2[addresss.toLong(2)] = value
            }
        }
    }

    println("Total sum at the end is: ${mapPart2.values.reduce { acc, i -> acc + i }}")


}













