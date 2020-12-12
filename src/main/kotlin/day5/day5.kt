package day5

import files.loadFile
import org.junit.jupiter.api.assertThrows

fun main() {
    val f = loadFile("day5/day5.txt").split("\n").map { it.trim() }.filter { it.isNotEmpty() }
    val ids = f.map { value ->

        val rowString = value.slice(0..6)
        val colString = value.slice(7..9)

        println("$rowString, $colString")
        val row = getRow(rowString.trim())
        val col = getCol(colString.trim())
        getId(row, col)
    }

    println("Max: ${ids.max()}... ")

    println("Missing: ${findMissing(ids)}")

}

fun findMissing(idList: List<Int>): Int {
    val sorted = idList.sorted()
    val zipped = sorted.zipWithNext()
    zipped.map { it ->
        val (a, b) = it
        if (b - a != 1) {
            return b - 1
        }
    }
    return -1
}

/**
 * For example, consider just the first seven characters of FBFBBFFRLR:

Start by considering the whole range, rows 0 through 127.
F means to take the lower half, keeping rows 0 through 63.
B means to take the upper half, keeping rows 32 through 63.
F means to take the lower half, keeping rows 32 through 47.
B means to take the upper half, keeping rows 40 through 47.
B keeps rows 44 through 47.
F keeps rows 44 through 45.
The final F keeps the lower of the two, row 44.

 */

fun getRow(rowString: String): Int {
//    return getRowIterative(rowString)
    return getRowRecursive(rowString, 0, 127)
}

fun getRowRecursive(string: String, l: Int, u: Int): Int {
    println("$string, $l, $u")
    if (string.length == 1) {
        return when {
            string.last() == 'B' -> {
                u
            }
            string.last() == 'F' -> {
                l
            }
            else -> {
                throw Exception("Invalid character in rowString")
            }
        }
    }

    val mid = (u - l) / 2 + l

    return when {
        string.first() == 'B' -> {
            getRowRecursive(string.slice(1 until string.length), mid+1, u)
        }
        string.first() == 'F' -> {
            getRowRecursive(string.slice(1 until string.length), l, mid)
        }
        else -> {
            throw Exception("Invalid character in rowString")
        }
    }
}

fun getRowIterative(rowString: String): Int {
    var lower = 0
    var upper = 127

    rowString.forEachIndexed() { _, ch ->

        val mid = (upper - lower) / 2 + lower
        if (ch == 'F') {
            upper = mid
        }

        if (ch == 'B') {
            lower = mid
        }
    }

    return if (rowString.last() == 'B') {
        lower
    } else {
        upper
    }
}
fun getCol(colString: String): Int {
//    return getColIterative(colString)
    return getColRecursive(colString, 0, 7)
}

fun getColRecursive(string: String, l: Int, u: Int): Int {
    println("$string, $l, $u")
    if (string.length == 1) {
        return when {
            string.last() == 'R' -> {
                u
            }
            string.last() == 'L' -> {
                l
            }
            else -> {
                throw Exception("Invalid character in rowString")
            }
        }
    }

    val mid = (u - l) / 2 + l

    return when {
        string.first() == 'R' -> {
            getColRecursive(string.slice(1 until string.length), mid+1, u)
        }
        string.first() == 'L' -> {
            getColRecursive(string.slice(1 until string.length), l, mid)
        }
        else -> {
            throw Exception("Invalid character in rowString")
        }
    }
}

fun getColIterative(colString: String): Int {
    var lower = 0
    var upper = 7

    colString.forEachIndexed() { _, ch ->

        val mid = (upper - lower) / 2 + lower
        if (ch == 'R') {
            lower = mid
        }

        if (ch == 'L') {
            upper = mid
        }
    }

    return if (colString.last() == 'R') {
        lower
    } else {
        upper
    }
}

fun getId(row: Int, col: Int) = (row * 8) + col
