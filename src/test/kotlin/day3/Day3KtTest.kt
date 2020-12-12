package day3

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class Day3KtTest {


    @Test
    fun testLoadMap() {

        val map = loadMap(ClassLoader.getSystemResource("day3/day3.txt").readText())
        assertEquals(323, map.size)
        assertEquals(31, map[0].size)
    }

    @Test
    fun testParser() {
        val s = "..##"

        assertEquals(listOf('.', '.', '#', '#'), s.toCharArray().toList())
    }

    @Test
    fun testReachedEnd() {
        val map = loadMap(ClassLoader.getSystemResource("day3/day3.txt").readText())

        assertTrue(
            map.reachedEnd(
                Coordinates(
                    0,
                    322
                )
            )
        )
    }

    @Test
    fun testReduce() {
        val l = listOf(1, 2, 3, 4)
        assertEquals((1 * 2 * 3 * 4), l.reduce { c, a -> c*a })
    }

    @Test
    fun testReduce2() {
        val l = listOf(75, 294, 79, 85, 39)
        assertEquals((75 * 294 * 79 * 85 *39), l.reduce { c, a -> c*a })
    }





    @ParameterizedTest
    @MethodSource("treeCountArguments")
    fun testCountTrees(
        map: Slope,
        expected: Int,
        policy: MovePolicy
    ) {

        assertEquals(expected, countTrees(map, Coordinates(0, 0), policy))
    }

    private companion object {
        @JvmStatic
        fun treeCountArguments() = Stream.of(
            Arguments.of(
                loadMap(
                    """
                    .#..
                    ..##
                    """.trimIndent()
                ),
                0,
                MovePolicy(1, 1)
            ),
            Arguments.of(
                loadMap(
                    """
                    .#..
                    .###
                    """.trimIndent()
                ),
                1,
                MovePolicy(1, 1)
            ),
            Arguments.of(
                loadMap(
                    """
                    .#..
                    .###
                    .###
                    .###
                    """.trimIndent()
                ),
                3,
                MovePolicy(1, 1)
            ),
            Arguments.of(
                loadMap(
                    """
                    .#..
                    .###
                    .#.#
                    .###
                    """.trimIndent()
                ), 2,
                MovePolicy(1, 1)
            ),
            Arguments.of(
                loadMap(
                    """
                    .#..
                    .###
                    .#.#
                    .###
                    """.trimIndent()
                ), 3,
                MovePolicy(3, 1)
            )
        )
    }

}
