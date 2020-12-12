package day10

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day10KtTest {

    val s1 = """
        16
        10
        15
        5
        1
        11
        7
        19
        6
        12
        4
    """.trimIndent()

    val s2 = """
        28
        33
        18
        42
        31
        14
        46
        20
        48
        47
        24
        23
        49
        45
        19
        38
        39
        11
        1
        32
        25
        35
        8
        17
        7
        9
        4
        2
        34
        10
        3
    """.trimIndent()


    @Test
    fun testCounts() {
        val f = parseFile(s1)
        assertEquals(Pair(7, 5), countDifferences(f))
    }

    @Test
    fun testCounts2() {
        val f = parseFile(s2)
        assertEquals(Pair(22, 10), countDifferences(f))
    }
}
