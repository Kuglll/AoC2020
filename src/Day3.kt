import java.io.File

fun main() {
    //Read a file
    val input = File("src/Inputs/Day3.txt").readLines()

    var indexToBeChecked1 = 0
    val SIDE_STEP1 = 3
    var trees1: Long = 0

    var indexToBeChecked2 = 0
    val SIDE_STEP2 = 1
    var trees2: Long = 0

    var indexToBeChecked3 = 0
    val SIDE_STEP3 = 5
    var trees3: Long = 0

    var indexToBeChecked4 = 0
    val SIDE_STEP4 = 7
    var trees4: Long = 0

    var indexToBeChecked5 = 0
    val SIDE_STEP5 = 1
    var trees5: Long = 0

    var lineCounter = 0
    for (x in input.drop(1)){

        indexToBeChecked1 += SIDE_STEP1
        if (x.get(indexToBeChecked1 % x.length) == '#'){
            trees1++
        }

        indexToBeChecked2 += SIDE_STEP2
        if (x.get(indexToBeChecked2 % x.length) == '#'){
            trees2++
        }

        indexToBeChecked3 += SIDE_STEP3
        if (x.get(indexToBeChecked3 % x.length) == '#'){
            trees3++
        }

        indexToBeChecked4 += SIDE_STEP4
        if (x.get(indexToBeChecked4 % x.length) == '#'){
            trees4++
        }


        lineCounter++
        if(lineCounter % 2 == 0){
            indexToBeChecked5 += SIDE_STEP5
            if (x.get(indexToBeChecked5 % x.length) == '#'){
                trees5++
            }
        }
    }

    println("Number of trees right 3 down 1: $trees1")
    println("Number of each trees encountered from all slopes multiplied together: ${(trees1 * trees2 * trees3 * trees4 * trees5)}")

}