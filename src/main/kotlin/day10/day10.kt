package day10

import files.loadFile
import java.lang.RuntimeException

fun main() {
    val s = loadFile("day10/day10.txt")
    val f = parseFile(s)
    val counts = countDifferences(f)
    val p1 = counts.first * counts.second
    println("Part 1: $counts,  $p1")

    val p2: Long = buildDistinctArrangements(listOf(0) + f.sorted(), 0, hashMapOf())
    println("Part 2: $p2")
}

fun buildDistinctArrangements(f: List<Int>, i: Int, mem: HashMap<Int, Long>): Long {
    if (i in mem) {
        return mem[i]!!
    }

    if (i == f.size - 1) {
        return 1L
    }

    val x = getNextCandidatesIndexes(i, f)
    println(x)
    val numArrangements = x.map {
        buildDistinctArrangements(f, it, mem)
    }.sum()

    mem[i] = numArrangements
    return mem[i]!!
}

private fun getNextCandidatesIndexes(current: Int, data: List<Int>): List<Int> {
    val m = mutableListOf<Int>()

    for (i in current+1 until data.size) {
        val d = data[i]
        if (d < data[current]) {
            continue
        }
        if ((d - data[current]) in 1..3) {
            m.add(i)
        } else {
            break
        }
    }
    return m
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
