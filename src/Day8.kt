import java.io.File

fun main() {

    //Read a file
    val input = File("src/Inputs/Day8.txt").readText()

    val instructions = input.split("\n")
    println("Value of acc is ${getValueOfAcc(instructions)}")

    input.split("\n").mapIndexed { index, instruction ->
        var changedInstructions = instructions.toMutableList()
        if(instruction.split(" ").get(0) == "jmp"){
            //change to nop
            changedInstructions.add(index, "nop " + instruction.split(" ").get(1))
            changedInstructions.removeAt(index+1)
            println("Value of acc is ${getValueOfAcc(changedInstructions)}")
        } else if(instruction.split(" ").get(0) == "nop"){
            //change to jmp
            changedInstructions.add(index, "jmp " + instruction.split(" ").get(1))
            changedInstructions.removeAt(index+1)
            println("Value of acc is ${getValueOfAcc(changedInstructions)}")
        } else {
            //DO NOTHING
        }
    }

}

fun getValueOfAcc(instructions: List<String>): Int{
    var acc = 0
    var currentPointer = 0

    val alreadySeenInstructions = hashMapOf<String, Int>()

    while (true){
        if(currentPointer >= instructions.size){
            print("Correct answer part 2: ")
            break
        } else{
            val currentInstruction = instructions.get(currentPointer).split(" ").get(0)
            val currentValueOfInstruction = instructions.get(currentPointer).split(" ").get(1).toInt()
            if (instructions.get(currentPointer) in alreadySeenInstructions && alreadySeenInstructions[instructions.get(currentPointer)] == currentPointer){
                break
            }
            alreadySeenInstructions[instructions.get(currentPointer)] = currentPointer
            when (currentInstruction){
                "acc" -> {
                    acc += currentValueOfInstruction
                    currentPointer += 1
                }
                "nop" -> {
                    currentPointer += 1
                }
                "jmp" -> {
                    currentPointer += currentValueOfInstruction
                }
            }
        }
    }
    return acc
}
