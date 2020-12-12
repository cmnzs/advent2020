package day2

fun main() {

    val lines = ClassLoader.getSystemResource("day2/day2.txt").readText().trim().split("\n")
//    val lines = listOf(
//        "1-3 a: abcde",
//        "1-3 b: cdefg",
//        "2-9 c: ccccccccc",
//        "1-2 n: nntnnn"
//    )

    val parsed = lines.map { lineParser(it) }

    val validPasswords = parsed.filter { it.isValid() }
    val validPasswords2 = parsed.filter { it.isValid2() }

    println("Count: ${lines.size}, validCount: ${validPasswords.size}")
    println("Count: ${lines.size}, validCount: ${validPasswords2.size}")
//    println(validPasswords2)
}

data class LineData(
    val policy: Policy,
    val password: String
) {
    fun isValid(): Boolean {
        return policy.validate(password)
    }

    fun isValid2(): Boolean {
        return policy.part2Validate(password)
    }
}

data class Policy(
    val lower: Int,
    val upper: Int,
    val character: Char
) {
    fun validate(pw: String): Boolean {
        var counter = 0
        pw.forEach {
            if (it == character) {
                counter += 1
            }
        }
        return counter in lower..upper
    }

    fun part2Validate(pw: String): Boolean {
        println(this)
        var counter = 0
        pw.forEachIndexed { i, c ->
            println("$pw: ${i+1},  ${ (i+1)  in lower..upper}, ${c == character}, $counter")
            if (c == character && ((i +1) == lower || (i+1) == upper)) {
                counter += 1
            }

        }
        return counter == 1
    }
}

fun lineParser(line: String): LineData {
    val split = line.split(":")

    val policyString  = split[0].trim()
    val passwordString = split[1].trim()

    return LineData(
        parsePolicy(policyString),
        passwordString
    )
}

fun parsePolicy(s: String): Policy {
    val sps = s.split(" ")

    val range = sps[0].trim()
    val char = sps[1].trim()

    val ranges = range.split("-")
    val lower = ranges[0].toInt()
    val upper = ranges[1].toInt()

    return Policy(
        lower,
        upper,
        char.first()
    )
}
