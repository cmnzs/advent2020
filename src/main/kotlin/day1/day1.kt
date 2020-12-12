import java.io.File
import java.lang.Integer.parseInt
import java.net.URL


fun main() {

    val lines = ClassLoader.getSystemResource("day1/day1.txt").readText().trim().split("\n")
    val nums: List<Int> = lines.map { parseInt(it) }
    val (x,y) = findPair(nums)
    println("pair: ($x, $y), multiplied: ${x*y}")

    val (p, q, r) = findTriple(nums)
    println("triple: ($p, $q, $r), multiplied: ${p*q*r}")

}

fun findPair(nums: List<Int>): Pair<Int, Int> {
    val map: MutableMap<Int, Int> = mutableMapOf()

    nums.forEach {
        if (map.containsKey(it)) {
            return Pair(it, map[it]!!)
        }
        map[(2020 - it)] = it
    }
    return Pair(-1, -1)
}


fun findTriple(nums: List<Int>): Triple<Int, Int, Int> {
    val map: MutableMap<Int, Pair<Int, Int>> = mutableMapOf()

    nums.forEach { x ->
        nums.forEach { y ->
            map[(2020 - x - y)] = Pair(x, y)
        }
    }

    nums.forEach {
        if (map.containsKey(it)) {
            val (j, y) = map[it]!!
            return Triple(it, j, y)
        }
    }
    return Triple(-1, -1, -1)
}
