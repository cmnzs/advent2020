package day11

import day11.GridLayout.Companion.fromString
import files.loadFile
import java.lang.RuntimeException

fun main() {
    val f = loadFile("day11/day11.txt")
    val g = GridLayout.fromString(f)
    println("Solution 1: ${g.solve()}")
}

enum class SeatPosition(val s: String) {
    FLOOR("."),
    EMPTY("L"),
    OCCUPIED("#");

    companion object {
        fun fromString(s: String): SeatPosition {
            return when (s) {
                "." -> FLOOR
                "L" -> EMPTY
                "#" -> OCCUPIED
                else -> throw RuntimeException("oh no $s")
            }
        }
    }
}

class GridLayout(var layout: List<List<SeatPosition>>) {

    companion object {
        fun fromString(s: String): GridLayout {
            return GridLayout( s
                .split("\n")
                .filter { it.isNotEmpty() }
                .map { it.split("").filter { it.isNotEmpty() } }
                .map { it.map { seatString -> SeatPosition.fromString(seatString) } }
            )
        }
    }

    override fun equals(other: Any?): Boolean {
        println("Is grid? ${other is GridLayout}")
        if (other is GridLayout) {
            for ( i in other.layout.indices) {
                for (j in other.layout[i].indices) {
                    if (layout[i][j] != other.layout[i][j]) {
                        return false
                    }
                }
            }
            return true
        }
        return false
    }

    fun at(rowIndex: Int, colIndex: Int): SeatPosition {
        return layout[rowIndex][colIndex]
    }

    fun print() {
        println("[--")
        for (r in layout) {
            println(r.map { it.s })
        }
        println("--]")
    }

    fun transition(): GridLayout {
        return GridLayout(layout.mapIndexed { i, row ->  row.mapIndexed { j, x-> getNextState(i, j, this) }})
    }

    fun solve(): Int {
        var current: GridLayout = this
        var prev: GridLayout? = null

        while (true) {
            prev = current
            current = prev?.transition()
            if (current.layout == prev.layout) break
        }
        return current!!.layout.flatten().count { it == SeatPosition.OCCUPIED }
    }
}

fun getNextState(i: Int, j: Int, grid: GridLayout): SeatPosition {
    val numOccupied = countOccupiedAdjacent(i,j,grid)

    val currentPosition = grid.at(i, j)

    return when {
        currentPosition == SeatPosition.EMPTY && numOccupied == 0  -> SeatPosition.OCCUPIED
        currentPosition == SeatPosition.OCCUPIED && numOccupied >= 5  -> SeatPosition.EMPTY
        else -> currentPosition
    }
}
operator fun Pair<Int,Int>.plus(o: Pair<Int, Int>): Pair<Int,Int> {
    return this.first + o.first to this.second + o.second
}

operator fun Pair<Int,Int>.times(i: Int): Pair<Int,Int> {
    return this.first * i to this.second * i
}

fun Pair<Int, Int>.inGrid(grid: GridLayout): Boolean {
    return this.first >= 0 && this.second >= 0 && this.first < grid.layout.size && this.second < grid.layout[0].size
}

fun Pair<Int, Int>.isOccupied(grid: GridLayout): Boolean {
    return grid.at(first, second)  == SeatPosition.OCCUPIED
}

fun Pair<Int, Int>.isNotOccupied(grid: GridLayout): Boolean {
    return !this.isOccupied(grid)
}

fun Pair<Int, Int>.isEmpty(grid: GridLayout): Boolean {
    return grid.at(first, second)  == SeatPosition.EMPTY
}

fun countOccupiedAdjacent(rowIndex: Int, colIndex: Int, grid:GridLayout): Int {
    val directions = listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to +1, 1 to +1, 1 to 0, 1 to -1)

    val coords = directions.map {
        var possiblePoint =  (rowIndex to colIndex) + it
        if (!possiblePoint.inGrid(grid)){
            return@map null
        }
        while (possiblePoint.isNotOccupied(grid)) {

            if (possiblePoint.isEmpty(grid)) {
                return@map null
            }
            if ((possiblePoint + it).inGrid(grid)) {
                possiblePoint += it
            } else {
                return@map null
            }
        }
        return@map possiblePoint
    }

    return coords.filterNotNull().count { it.isOccupied(grid) }
}

fun countOccupied(rowIndex: Int, colIndex: Int, grid:GridLayout): Int {
    val offsets = listOf(-1 to -1, -1 to 0, -1 to 1, 0 to -1, 0 to +1, 1 to +1, 1 to 0, 1 to -1)
    val coords = offsets.map { it + (rowIndex to colIndex)}.filter { it.inGrid(grid) }
    return coords.count { it.isOccupied(grid) }
}
