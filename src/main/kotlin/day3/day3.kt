package day3

fun main() {

    val m = loadMap(ClassLoader.getSystemResource("day3/day3.txt").readText())
    val position = Coordinates(0, 0)
    val policy = MovePolicy(3, 1)

    println("Part 1: ${countTrees(m, position, policy)}")

    val policies = listOf(
        MovePolicy(1, 1),
        MovePolicy(3, 1),
        MovePolicy(5, 1),
        MovePolicy(7, 1),
        MovePolicy(1, 2),
    )
    val counts = policies.map {
        countTrees(m, Coordinates(0, 0), it)
    }

    println("Part 2: r: ${counts.map { it.toLong() }.reduce { c, a -> c*a }}, counts: $counts")
}

fun countTrees(slope: Slope, position: Coordinates, policy: MovePolicy): Int {
    var currentPosition: Coordinates = position
    var treeCount = 0

    while (!slope.reachedEnd(currentPosition)) {
        currentPosition = currentPosition.move(policy)
        if (slope.atTree(currentPosition)) {
            treeCount += 1
        }
    }
    return treeCount
}

fun Slope.atTree(pos: Coordinates): Boolean {
    return this[pos.y][pos.x % this[0].size] == '#'
}

fun Slope.reachedEnd(pos: Coordinates): Boolean {
    return (pos.y + 1) >= this.size
}

typealias Slope = List<List<Char>>

fun loadMap(s: String): Slope {
    val lines = s.trim().split("\n")

    return lines.map { it.toCharArray().toList() }
}

data class Coordinates(
    val x: Int,
    val y: Int
) {
    fun move(policy: MovePolicy): Coordinates {
        return Coordinates(
            x + policy.dx,
            y + policy.dy
        )
    }
}

data class MovePolicy(
    val dx: Int,
    val dy: Int
)

