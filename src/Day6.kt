import java.io.File

fun main() {
    //Read a file
    val input = File("src/Inputs/Day6.txt").readText()

    val list = mutableListOf<Int>()
    for (group in input.split("\n\n")){
        val set = mutableSetOf<Char>()
        for (person in group.split("\n")){
            for (answer in person){
                set.add(answer)
            }
        }
        list.add(set.size)
    }

    println("Sum of counts: ${list.reduce{ a, b -> a + b}}")

    // Part 2
    val list2 = mutableListOf<Int>()
    for (group in input.split("\n\n")){
        val set = mutableSetOf<Char>()
        var firstPerson = true
        for (person in group.split("\n")){
            if(firstPerson){
                for (answer in person) {
                    set.add(answer)
                }
                firstPerson = false
            } else {
                val temp = mutableListOf<Char>()
                for (answer in set){
                    if(answer !in person){
                        temp.add(answer)
                    }
                }
                for(char in temp){
                    set.remove(char)
                }
                if (set.size == 0){
                    break
                }
            }
        }
        list2.add(set.size)
    }

    println("Sum of counts part 2: ${list2.reduce{ a, b -> a + b}}")

}