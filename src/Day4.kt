import java.io.File

fun main() {
    //Read a file
    val input = File("src/Inputs/Day4.txt").readText()

    val passports = mutableListOf<Passport>()

    for (pp in input.split("\n\n")){
        val passport = Passport()
        for (field in pp.replace("\n", " ").split(" ")){
            val key = field.split(":").get(0)
            val value = field.split(":").get(1)
            when (key){
                "byr" -> passport.byr = value
                "iyr" -> passport.iyr = value
                "eyr" -> passport.eyr = value
                "hgt" -> passport.hgt = value
                "hcl" -> passport.hcl = value
                "ecl" -> passport.ecl = value
                "pid" -> passport.pid = value
                "cid" -> passport.cid = value
            }
        }
        passports.add(passport)
    }

    val validPassports = passports.count {
        it.isPassportValid()
    }

    println("Valid passports: $validPassports")

    val trulyValidPassports = passports.count{
        it.isPassportTrulyValid()
    }
    println("Truly valid passports: $trulyValidPassports")
}

fun Passport.isPassportValid(): Boolean{
    return byr != null && iyr != null && eyr != null && hgt != null && hcl != null && ecl != null && pid != null
}

fun Passport.isPassportTrulyValid(): Boolean{
    return isByrValid(byr) &&
            isIyrValid(iyr) &&
            isEyrValid(eyr) &&
            isHgtValid(hgt) &&
            isHclValid(hcl) &&
            isEclValid(ecl) &&
            isPidValid(pid)
}

fun isByrValid(byr: String?): Boolean{
    //at least 1920 and at most 2002
    byr?.let {
        return it.toInt() in (1920..2020)
    }
    return false
}

fun isIyrValid(iyr: String?): Boolean{
    //at least 2010 and at most 2020
    iyr?.let {
        return it.toInt() in (2010..2020)
    }
    return false
}

fun isEyrValid(eyr: String?): Boolean{
    //at least 2020 and at most 2030
    eyr?.let {
        return it.toInt() in (2020..2030)
    }
    return false
}

fun isHgtValid(hgt: String?): Boolean{
    /*
    a number followed by either cm or in:
    If cm, the number must be at least 150 and at most 193.
    If in, the number must be at least 59 and at most 76.
     */

    hgt?.let {
        if(it.contains("in")){
            return it.dropLast(2).toInt() in (59..76)
        } else if(it.contains("cm")){
            return it.dropLast(2).toInt() in (150..193)
        }
    }
    return false
}

fun isHclValid(hcl: String?): Boolean{
    //# followed by exactly six characters 0-9 or a-f.
    hcl?.let {
        return it.get(0) == '#' && checkHex(it)
    }
    return false
}

fun checkHex(hcl: String): Boolean{
    val temp = mutableListOf<Boolean>()
    for (char in hcl.drop(1)){
        temp.add(char in ('a'..'f') || char.toString().toInt() in (0..9))
    }
    return !temp.any{ !it }
}

fun isEclValid(ecl: String?): Boolean{
    //exactly one of: amb blu brn gry grn hzl oth
    ecl?.let{
        return ecl in listOf("amb", "blu", "brn", "gry", "grn", "hzl", "oth")
    }
    return false
}

fun isPidValid(pid: String?): Boolean{
    //a nine-digit number, including leading zeroes
    pid?.let {
        return it.length == 9
    }
    return false
}

/*
byr (Birth Year)
iyr (Issue Year)
eyr (Expiration Year)
hgt (Height)
hcl (Hair Color)
ecl (Eye Color)
pid (Passport ID)
cid (Country ID)
*/
class Passport(
        var byr: String? = null,
        var iyr: String? = null,
        var eyr: String? = null,
        var hgt: String? = null,
        var hcl: String? = null,
        var ecl: String? = null,
        var pid: String? = null,
        var cid: String? = null
)