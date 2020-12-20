import java.io.File
import kotlin.test.currentStackTrace

fun main() {

    val input = mutableListOf(6, 3, 15, 13, 1, 0)

    val map = hashMapOf<Int, Int>() //key is a number and value is when that number was last said
    var numbersSaidForTheFirstTime = mutableListOf<Int>()

    input.mapIndexed { index, i ->
        map[i] = index + 1
        numbersSaidForTheFirstTime.add(i)
    }

    var turn = input.size+1

    while (true){
        val lastNumberSpoken = input.get(input.lastIndex)
        if(lastNumberSpoken in numbersSaidForTheFirstTime){
            numbersSaidForTheFirstTime.remove(lastNumberSpoken)
            map[lastNumberSpoken] = turn - 1
            input.add(0)
            if(0 in numbersSaidForTheFirstTime){
                numbersSaidForTheFirstTime.remove(0)
            }
        } else if(lastNumberSpoken !in map){
            numbersSaidForTheFirstTime.add(lastNumberSpoken)
            input.add(lastNumberSpoken)
        } else if(lastNumberSpoken in map){
            val currentNumberSpoken = (turn - 1) - map[lastNumberSpoken]!!
            input.add(currentNumberSpoken)
            if(currentNumberSpoken in numbersSaidForTheFirstTime){
                numbersSaidForTheFirstTime.remove(currentNumberSpoken)
            } else if(currentNumberSpoken !in map){
                numbersSaidForTheFirstTime.add(currentNumberSpoken)
            }
            map[lastNumberSpoken] = turn-1
        }
        if(turn == 2020){
            println("2020 number spoken is: ${input.get(input.lastIndex)}")
        }
        if(turn == 30000000){
            println("30000000 number spoken is: ${input.get(input.lastIndex)}")
            break
        }
        turn++
    }



}