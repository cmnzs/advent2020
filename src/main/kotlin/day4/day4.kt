package day4

import files.loadFile
import files.splitFile


fun main() {

    val sp = splitFile(loadFile("day4/day4.txt"))
    val valid = validatePassports(sp)
    val (t, f) = valid.partition { it }
    println("T: ${t.size}, F: ${f.size}. $valid")
}

fun validatePassports(s: List<List<String>>): List<Boolean> {
    return s
        .filter { hasRequiredKeys(it) } // filter out passports that don't have required keys
        .map { tokenize(it) }
        .map { validatePassportValues(it) }
}

val rules = listOf(
    BYR(),
    IYR(),
    EYR(),
    HGT(),
    HCL(),
    ECL(),
    PID(),
)

fun validatePassportValues(tokens: List<Pair<String, String>>): Boolean {
    return tokens.map { pair: Pair<String, String> ->
        val (k, v) = pair
        rules.map rule@{ rule ->
            if (rule.isApplicable(k)) {
                return@rule rule.isValid(v)
            } else {
                return@rule true
            }
        }.all { it }
    }.all { it }
}

interface ValidationRule {
    fun isApplicable(key: String): Boolean
    fun isValid(value: String): Boolean
}

class BYR : ValidationRule {
    override fun isApplicable(key: String): Boolean {
        return key == "byr"
    }

    override fun isValid(value: String): Boolean {
        return value.length == 4 && value.toInt() in 1920..2002
    }

}

class IYR : ValidationRule {
    override fun isApplicable(key: String): Boolean {
        return key == "iyr"
    }

    override fun isValid(value: String): Boolean {
        return value.length == 4 && value.toInt() in 2010..2020
    }

}

class EYR : ValidationRule {
    override fun isApplicable(key: String): Boolean {
        return key == "eyr"
    }

    override fun isValid(value: String): Boolean {
        return value.length == 4 && value.toInt() in 2020..2030
    }

}

class HGT : ValidationRule {
    override fun isApplicable(key: String): Boolean {
        return key == "hgt"
    }

    override fun isValid(value: String): Boolean {
        val r = Regex("""(\d+)(cm|in)""")
        val result = r.matchEntire(value)
        if (result != null) {
            val values = result.groupValues
            val num = values[1].toInt()
            val unit = values.last()

            if (unit == "cm") {
                return num in 150..193
            }

            if (unit == "in") {
                return num in 59..76
            }

            return false

        } else {
            return false
        }
    }

}

class HCL : ValidationRule {
    override fun isApplicable(key: String): Boolean {
        return key == "hcl"
    }

    override fun isValid(value: String): Boolean {
        val r = Regex("""#[\d|abcdef]{6}""")

        return r.matches(value)
    }
}

class ECL : ValidationRule {
    override fun isApplicable(key: String): Boolean {
        return key == "ecl"
    }

    override fun isValid(value: String): Boolean {
        val r = Regex("""amb|blu|brn|gry|grn|hzl|oth""")
        return r.matches(value)
    }

}

class PID : ValidationRule {
    override fun isApplicable(key: String): Boolean {
        return key == "pid"
    }

    override fun isValid(value: String): Boolean {
        val r = Regex("""\d{9}""")

        return r.matches(value)
    }

}

fun hasRequiredKeys(passportString: List<String>): Boolean {
    val REQUIRED_TOKENS: Set<String> = setOf(
        "byr",
        "iyr",
        "eyr",
        "hgt",
        "hcl",
        "ecl",
        "pid"
    )
    val tokens = tokenize(passportString)
    val keysPresent = tokens.map { it.first }.toSet()

    return keysPresent.containsAll(REQUIRED_TOKENS)
}

fun tokenize(passportString: List<String>): List<Pair<String, String>> {
    return passportString.map {
        val tokenized = it.split(":").map { it.trim() }
        return@map Pair(
            tokenized.first(),
            tokenized.last()
        )
    }
}
