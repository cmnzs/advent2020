package day7

import files.loadFile
import kotlin.collections.ArrayDeque


fun main() {
    val s = loadFile("day7/day7.txt")
    println("Part 1:: ${
        parseAndSearch(s).mapNotNull { it ->
            if (it.isEmpty())
                null
            else
                it.first()
        }.toSet().size}")

    val rules = parseRules(s)
    val r2 = count("shiny gold", rules) - 1
    println("Part 2: $r2 ---")
}

fun parseAndSearch(s: String): List<List<List<Pair<String, Int>>>> {
    val rulesMap = parseRules(s)
    return rulesMap.keys.map { search(it, rulesMap, "shiny gold") }
}

fun parseRules(s: String): Map<String, BagContents> {
    val rules = parseString(s)
    return rules.associateBy({ it.colour }, { it.contents })
}

fun count(currentBagColor: String, rulesMap: Map<String, BagContents>): Int {
    val nextBags = rulesMap[currentBagColor]!!
    if (nextBags.isEmpty()) {
        return 1
    }

    return 1+ nextBags.map { it.second * count(it.first, rulesMap) }.sum()
}

fun search(startingColour: String, rulesMap: Map<String, BagContents>, destination: String?): List<List<Pair<String, Int>>> {
    val stack = ArrayDeque<Pair<String, Int>>()

    val nextColours = rulesMap[startingColour]!!

    stack.addAll(nextColours.contents)
    val paths: MutableList<List<Pair<String, Int>>> = mutableListOf()

    val currentPath: MutableList<Pair<String,Int>> = mutableListOf(Pair(startingColour,0))

    while (stack.isNotEmpty()) {
        val top = stack.removeLast()
        if (top.first == destination) {
            currentPath.add(top)
            paths.add(currentPath.toList())
            currentPath.removeAll { true }
                currentPath.add(Pair(startingColour, 0))
        } else {
            currentPath.add(top)

            val toAdd = rulesMap[top.first]!!.contents
            if (destination == null && toAdd.isEmpty()) {
                currentPath.add(Pair("none", 1))
                paths.add(currentPath)
            } else {
                stack.addAll(toAdd)
            }
        }
    }

    return paths
}

fun parseString(s: String) =
    s.split("\n")
        .map { parseLine(it.trim()) }


data class BagRule(
    val colour: String,
    val contents: BagContents
) {
    companion object {
        fun fromString(s: String): BagRule {

            val stringParts = s.split("bags contain").first().trim()

            return BagRule(
                stringParts,
                BagContents.fromString(s)
            )
        }
    }
}

data class BagContents(
    val contents: List<Pair<String, Int>>
): Collection<Pair<String, Int>> by contents {

    companion object {
        fun fromString(s: String): BagContents {

            if (s == "no other bags.") {
                return BagContents(emptyList())
            }

            return BagContents(parseContentsList(s))
        }

        fun parseContentsList(s: String): List<Pair<String, Int>> {
            val capturePattern = Regex("""(\d) ([a-z ]+)(?:bag|bags).""")
            val matchResults = capturePattern.findAll(s)
                .map { it.groupValues }
                .map { it.drop(1).map { it.trim() } }
                .toList()

            return matchResults.map {
                Pair(it.last(), it.first().toInt())
            }
        }
    }
}

fun parseLine(s: String): BagRule {
    return BagRule.fromString(s)
}
