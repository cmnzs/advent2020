package day9

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day9KtTest {

    val s = """
        35
        20
        15
        25
        47
        40
        62
        55
        65
        95
        102
        117
        150
        182
        127
        219
        299
        277
        309
        576
    """.trimIndent()

    @Test
    fun search() {
        val n = parseFile(s)
        assertEquals(listOf(
            15, 25, 47, 40
        ).map { it.toLong() },
            searchContiguousSum(n, 127))
    }
}
