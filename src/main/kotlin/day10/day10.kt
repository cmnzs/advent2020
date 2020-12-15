package day10

import files.loadFile
import java.lang.RuntimeException

fun main() {
    val s = loadFile("day10/day10.txt")
    val f = parseFile(s)
    val counts = countDifferences(f)
    val p1 = counts.first * counts.second
    println("Part 1: $counts,  $p1")

    val p2: Long = buildDistinctArrangements(f)
    println("Part 2: $p2")
}

fun buildDistinctArrangements(l: List<Int>): Long {
    var multiplier = 1
    val f = l.sorted().toMutableList()
//    while(f.isNotEmpty()) {
//        mult
//    }
//
//    return multiplier
    return Solution(f).solve()
}

class Solution(private val sortedValues: List<Int>) {

    private val candidateSolutions = mutableListOf<List<Int>>()
    private var counter = 0L

    fun solve(): Long {

        backtrackDFS(listOf(0), sortedValues)
//        return candidateSolutions.size
        return counter
    }

    private fun backtrackDFS(a: List<Int>, data: List<Int>) {
//        println("isSolution(a) ${isSolution(a)}, ${a} ${data}")
        if (isSolution(a)) {
            processSolution(a)
        } else {
            val current = a.last()
            val candidateSteps2 = getNextCandidates(current, data)
            candidateSteps2.forEach {
                backtrackDFS(a + it, data - it)
            }
        }
    }

    private fun getNextCandidatesOriginal(current: Int, data: List<Int>): List<Int> {
        return data.filter { (it - current) in 1..3 }
    }

    private fun getNextCandidates(current: Int, data: List<Int>): List<Int> {
        val m = mutableListOf<Int>()
        for (d in data) {
//            println("d - current: ${d} ${current}, ${d - current} ${(d - current) in 1..3}")
            if (d < current) {
                continue
            }
            if ((d - current) in 1..3) {
                m.add(d)
            } else {
                break
            }
        }
        return m
    }

    private fun isSolution(l: List<Int>): Boolean {
        return l.last() == sortedValues.last()
    }

    private fun processSolution(l: List<Int>) {
//        candidateSolutions.add(l)
        counter += 1
//        if (counter % 100L == 0) println(counter)
    }
}


fun parseFile(s: String): List<Int> {
    return s.split("\n").filter { it.isNotEmpty() }.map { it.trim().toInt() }
}

fun countDifferences(l: List<Int>): Pair<Int, Int> {

    val max = l.maxOrNull()!!
    val builtInDevice = max + 3
    val adapterOrdered = listOf(0) + l.sorted() + builtInDevice

    var oneCounter = 0
    var threeCounter = 0

    adapterOrdered.zipWithNext().map { (x, y) ->
        when (y - x) {
            1 -> oneCounter += 1
            3 -> threeCounter += 1
            else -> throw RuntimeException()
        }
    }

    return oneCounter to threeCounter
}
