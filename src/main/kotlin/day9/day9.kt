package day9

import files.loadFile

fun main() {
    val s = loadFile("day9/day9.txt")
    val nums = parseFile(s)
    val validNums = validate(nums, 25)

    val targetValue = validNums.first { !it.second }.first
    println("First invalid: ${targetValue}")
    val partTwoComponents = searchContiguousSum(nums, targetValue)
    println("Part 2: ${partTwoComponents.min()!! + partTwoComponents.max()!!}")
}

fun parseFile(s: String): List<Long> {
    return s.split("\n").map { it.trim() }.filter { it.isNotEmpty() }.map { it.toLong() }
}

fun validate(x: List<Long>, preambleLength: Int): List<Pair<Long, Boolean>> {

    return x.mapIndexed { i, it ->
        if (i < preambleLength) {
            it to true
        } else {
            val slidingWindow = x.slice((i-preambleLength)..i)
            (it to hasTwoSum(slidingWindow, it))
        }
    }
}

fun hasTwoSum(nums: List<Long>, x: Long): Boolean {
    val map: MutableMap<Long, Long> = mutableMapOf()

    nums.forEach {
        if (map.containsKey(it)) {
            return true
        }
        map[(x - it)] = it
    }
    return false
}

fun searchContiguousSum(nums: List<Long>, target: Long): List<Long> {

    var found = false

    var outerIndex = 0
    var innerIndex = 0

    while(!found) {
        val sliced = nums.slice(outerIndex..innerIndex)
        val s = sliced.sum()
        if (s < target) {
            innerIndex += 1
        } else if (s > target) {
            outerIndex+=1
            innerIndex = outerIndex
        } else {
            return sliced
        }
    }
    return emptyList()
}
