package day11

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class GridLayoutTest {

    val s = """
        L.LL.LL.LL
        LLLLLLL.LL
        L.L.L..L..
        LLLL.LL.LL
        L.LL.LL.LL
        L.LLLLL.LL
        ..L.L.....
        LLLLLLLLLL
        L.LLLLLL.L
        L.LLLLL.LL
    """.trimIndent()

    @Test
    fun testParsing() {
        val g = GridLayout.fromString(s)
        println(g.layout)
        assertEquals(10, g.layout.size)
        assertEquals(10, g.layout[0].size)
    }

    @Test
    fun testEquality() {
        val g = GridLayout.fromString(s)
        val s2 = """
            #.##.##.##
            #######.##
            #.#.#..#..
            ####.##.##
            #.##.##.##
            #.#####.##
            ..#.#.....
            ##########
            #.######.#
            #.#####.##
        """.trimIndent()
        assertEquals(GridLayout.fromString(s2), g.transition())
    }

    @Test
    fun testAdjacentSearch(){
        val ex = """
            .......#.
            ...#.....
            .#.......
            .........
            ..#L....#
            ....#....
            .........
            #........
            ...#.....
        """.trimIndent()

        assertEquals(8, countOccupiedAdjacent(4, 3, GridLayout.fromString(ex)))
    }

    @Test
    fun testAdjacentSearch2() {

        val ex = """
            .............
            .L.L.#.#.#.#.
            .............
        """.trimIndent()
        assertEquals(0, countOccupiedAdjacent(1, 1, GridLayout.fromString(ex)))
    }

    @Test
    fun testAdjacentSearch3() {

        val ex = """
            .##.##.
            #.#.#.#
            ##...##
            ...L...
            ##...##
            #.#.#.#
            .##.##.
        """.trimIndent()
        println()
        val grid = GridLayout.fromString(ex)
        println(grid.at(3, 3))
        assertEquals(0, countOccupiedAdjacent(3, 3, grid))
    }

    @Test
    fun testTransition() {
        val t1 = """
            #.##.##.##
            #######.##
            #.#.#..#..
            ####.##.##
            #.##.##.##
            #.#####.##
            ..#.#.....
            ##########
            #.######.#
            #.#####.##
        """.trimIndent()
        val t2 = """
            #.LL.LL.L#
            #LLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLL#
            #.LLLLLL.L
            #.LLLLL.L#
        """.trimIndent()

        val t3 = """
            #.LL.LL.L#
            #LLLLLL.LL
            L.L.L..L..
            LLLL.LL.LL
            L.LL.LL.LL
            L.LLLLL.LL
            ..L.L.....
            LLLLLLLLL#
            #.LLLLLL.L
            #.LLLLL.L#
        """.trimIndent()

        val t4 = """
            #.L#.##.L#
            #L#####.LL
            L.#.#..#..
            ##L#.##.##
            #.##.#L.##
            #.#####.#L
            ..#.#.....
            LLL####LL#
            #.L#####.L
            #.L####.L#
        """.trimIndent()
        val actual1 = GridLayout.fromString(s).transition()
        val actual2 = actual1.transition()
        val actual3 = actual2.transition()
        val actual4 = actual3.transition()

        val expected1 = GridLayout.fromString(t1)
        val expected2 =  GridLayout.fromString(t2)
        val expected3 =  GridLayout.fromString(t3)
        val expected4 =  GridLayout.fromString(t4)

//        expected2.print()
//        actual2.print()

        expected3.print()
        actual3.print()

        assertEquals(expected1, actual1)
        assertEquals(expected2, actual2)
        assertEquals(expected3, actual3)
        assertEquals(expected4, actual4)
    }
}
