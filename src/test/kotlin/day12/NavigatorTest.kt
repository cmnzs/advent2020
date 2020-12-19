package day12

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import kotlin.math.PI
import kotlin.math.cos

internal class NavigatorTest {

    @Test
    fun travel() {
        val f = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent().parseFile()

        assertEquals(25, Navigator(f).solve())
    }

    @Test
    fun solve2() {
        val f = """
            F10
            N3
            F7
            R90
            F11
        """.trimIndent().parseFile()

        assertEquals(286, Navigator(f).solve2())
    }

    @Test
    fun solve2_second() {
        val f = """
            F10
            N3
            E1000
            W1000
            F7
            R90
            F11
        """.trimIndent().parseFile()

        assertEquals(286, Navigator(f).solve2())
    }

    @Test
    fun travel2() {

        val f = """
            F10
            N3
            F7
            R90
            F11
            R180
            F11
            R180
            F11
        """.trimIndent().parseFile()

        Navigator(f).travel()
        assertEquals(25, Navigator(f).solve())
    }

    @Test
    fun testMove() {
        val p = Position(0.0, 0.0)
        assertEquals(Position(0.0, 1.0), p.moveDirection(Direction.NORTH, 1))
        assertEquals(Position(0.0, -2.0), p.moveDirection(Direction.SOUTH, 2))
        assertEquals(Position(-1.0, 0.0), p.moveDirection(Direction.WEST, 1))
        assertEquals(Position(-1.0, 0.0), p.moveDirection(Direction.WEST, 1))
        assertEquals(Position(0.0, -1.0), p.moveDirection(Direction.SOUTH, 1))
        assertEquals(Position(0.0, 1.0), p.moveDirection(Direction.NORTH, 1))
        assertEquals(Position(1.0, 0.0), p.moveDirection(Direction.EAST, 1))
        assertEquals(Position(1.0, 0.0), p.moveDirection(Direction.EAST, 1))
        assertEquals(Position(100.0, 0.0), p.moveDirection(Direction.EAST, 100))
    }
    @Test
    fun testMove2() {
        val p = Position(1.0, 1.0)
        assertEquals(Position(1.0, 2.0), p.moveDirection(Direction.NORTH, 1))
        assertEquals(Position(1.0, -1.0), p.moveDirection(Direction.SOUTH, 2))
        assertEquals(Position(0.0, 1.0), p.moveDirection(Direction.WEST, 1))
        assertEquals(Position(0.0, 1.0), p.moveDirection(Direction.WEST, 1))
        assertEquals(Position(1.0, 0.0), p.moveDirection(Direction.SOUTH, 1))
        assertEquals(Position(1.0, 2.0), p.moveDirection(Direction.NORTH, 1))
        assertEquals(Position(2.0, 1.0), p.moveDirection(Direction.EAST, 1))
        assertEquals(Position(2.0, 1.0), p.moveDirection(Direction.EAST, 1))
        assertEquals(Position(101.0, 1.0), p.moveDirection(Direction.EAST, 100))
    }

    @Test
    fun t() {
        val p = Position(0.0, 0.0)
        var theta = 270

        if (theta > 180) {
            theta = theta - 360
        }
        val r = theta.toDouble() * PI / 180.0

        println("r = $r, cos(r) = ${cos(r)}")
    }

    @Test
    fun testCardinals() {
        assertEquals(Direction.NORTH,Direction.EAST.rotate(90))
        assertEquals(Direction.WEST, Direction.EAST.rotate(180))
        assertEquals(Direction.SOUTH,Direction.EAST.rotate(270))
        assertEquals(Direction.NORTH,Direction.EAST.rotate(-270))
        assertEquals(Direction.SOUTH,Direction.EAST.rotate(-90))
        assertEquals(Direction.WEST, Direction.EAST.rotate(-180))

        assertEquals(Direction.WEST,Direction.NORTH.rotate(90))
        assertEquals(Direction.SOUTH, Direction.NORTH.rotate(180))
        assertEquals(Direction.SOUTH, Direction.NORTH.rotate(-180))
        assertEquals(Direction.EAST,Direction.NORTH.rotate(270))
        assertEquals(Direction.WEST,Direction.NORTH.rotate(-270))
        assertEquals(Direction.EAST,Direction.NORTH.rotate(-90))

        assertEquals(Direction.SOUTH,Direction.WEST.rotate(90))
        assertEquals(Direction.EAST, Direction.WEST.rotate(180))
        assertEquals(Direction.NORTH,Direction.WEST.rotate(270))
        assertEquals(Direction.SOUTH,Direction.WEST.rotate(-270))
        assertEquals(Direction.NORTH,Direction.WEST.rotate(-90))
        assertEquals(Direction.EAST, Direction.WEST.rotate(-180))

        assertEquals(Direction.EAST,Direction.SOUTH.rotate(90))
        assertEquals(Direction.NORTH, Direction.SOUTH.rotate(180))
        assertEquals(Direction.WEST,Direction.SOUTH.rotate(270))
        assertEquals(Direction.EAST,Direction.SOUTH.rotate(-270))
        assertEquals(Direction.WEST,Direction.SOUTH.rotate(-90))
        assertEquals(Direction.NORTH,Direction.SOUTH.rotate(-180))
    }

    @Test
    fun testRotations() {
        assertEquals(
            Position(-1.0, -1.0),
            Position(1.0, 1.0).rotate(180)
        )
    }

    @Test
    fun testRotations2() {
        assertEquals(
            Position(7.0, 3.0),
            Position(-7.0, -3.0).rotate(180)
        )
    }
}
