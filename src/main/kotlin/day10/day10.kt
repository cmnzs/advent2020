package day10

import files.loadFile
import java.lang.RuntimeException

fun main() {
    val s = loadFile("day10/day10.txt")
    println(countDifferences(parseFile(s)))
}

fun parseFile(s: String): List<Int> {
    return s.split("\n").filter {it.isNotEmpty()}.map { it.trim().toInt() }
}

fun countDifferences(l: List<Int>): Pair<Int, Int> {

    val max = l.maxOrNull()!!
    val builtInDevice = max + 3
    val adapterOrdered = listOf(0) + l.sorted() + builtInDevice

    var oneCounter = 0
    var threeCounter = 0

    adapterOrdered.zipWithNext().map { (x, y) ->
        when (y-x) {
            1 -> oneCounter += 1
            3 -> threeCounter += 1
            else -> throw RuntimeException()
        }
    }

    return oneCounter to threeCounter
}
