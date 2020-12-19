package day12

import files.loadFile
import java.lang.RuntimeException
import kotlin.math.*

fun main() {
  val instructions = loadFile("day12/day12.txt").parseFile()
  println("Solution 1: ${Navigator(instructions).solve()}")
  println("Solution 2: ${Navigator(instructions).solve2()}")
}
fun String.parseFile(): List<Instruction> {
  return this.split("\n").filter { it.isNotEmpty() }.map {
    return@map Instruction(it.first().toString(), it.drop(1).toInt())
  }
}

data class Instruction(val ch: String, val amt: Int)

data class Position(var x: Double, var y: Double) {

  init {
    x = floor(round(x))
    y = floor(round(y))
  }
  fun moveNorth(amt: Int): Position {
    return Position(x, y + amt)
  }
  fun moveSouth(amt: Int): Position {
    return Position(x, y - amt)
  }

  fun moveEast(amt: Int): Position {
    return Position(x + amt, y)
  }

  fun moveWest(amt: Int): Position {
    return Position(x - amt, y)
  }

  operator fun minus(other: Position): Position {
    return Position(x - other.x, y - other.y)
  }

  operator fun plus(other: Position): Position {
    return Position(x + other.x, y + other.y)
  }

  fun rotate(theta: Int): Position {

    val t = when {
      else -> {
        Math.toRadians(theta.toDouble())
      }
    }
//    println("THETA: $theta -- t: $t\n")
//    println("($x * ${cos(t)} - $y*${sin(t)}),( $x*${sin(t)} + $y*${cos(t)})")
    val one = x * cos(t) - y*sin(t)
    val two = x * sin(t) + y*cos(t)
//    println("${one}, ${two}")
    return Position(one, two)
  }

  fun moveDirection(d: Direction, amt: Int): Position {

    return Position(
      x + (amt * cos(Math.toRadians(d.degrees.toDouble()))),
      y + (amt * sin(Math.toRadians(d.degrees.toDouble())))
    )
  }
}

enum class Direction(val degrees: Int) {
  NORTH(90), SOUTH(-90), EAST(0), WEST(180);

  fun rotate(n: Int): Direction {
    val x = ((n / 90).toInt() + ordering.indexOf(this)) % ordering.size

    val j = if (x < 0) (ordering.size + x) else x
    return ordering[j]
  }

  companion object {
    val ordering = listOf(EAST, NORTH, WEST, SOUTH)
  }
}

data class Vector(
  val p: Position,
  val o: Direction
) {
  fun acceptInstruction(instruction: Instruction): Vector {
    return when (instruction.ch) {
      "N" -> {
        Vector(p.moveNorth(instruction.amt), o)
      }
      "S" -> {
        Vector(p.moveSouth(instruction.amt), o)
      }
      "E" -> {
        Vector(p.moveEast(instruction.amt), o)
      }
      "W" -> {
        Vector(p.moveWest(instruction.amt), o)
      }
      "L" -> {
        Vector(p, o.rotate(instruction.amt))
      }
      "R" -> {
        Vector(p, o.rotate(-instruction.amt))
      }
      "F" -> {
        Vector(p.moveDirection(o, instruction.amt), o)
      }
      else -> throw RuntimeException("FAIL")
    }
  }
  fun acceptInstruction2(instruction: Instruction, otherPoint: Position): Vector {
    return when (instruction.ch) {
      "N" -> {
        Vector(p.moveNorth(instruction.amt), o)
      }
      "S" -> {
        Vector(p.moveSouth(instruction.amt), o)
      }
      "E" -> {
        Vector(p.moveEast(instruction.amt), o)
      }
      "W" -> {
        Vector(p.moveWest(instruction.amt), o)
      }
      "L" -> {
        val newPosition = this.p.rotate(instruction.amt)
        Vector(newPosition, o)
      }
      "R" -> {
        val newPosition = this.p.rotate(-instruction.amt)
        Vector(newPosition, o)
      }
      "F" -> {
        Vector(p + (instruction.amt * otherPoint), o)
      }
      else -> throw RuntimeException("FAIL")
    }
  }
  companion object {
    fun starter(): Vector {
      return Vector(
        Position(0.0, 0.0),
        Direction.EAST
      )
    }
  }
}

private operator fun Int.times(point: Position): Position {
  return Position((point.x * this), point.y * this)
}

class Navigator(val instructions: List<Instruction>) {
  var ship: Vector = Vector.starter()
  var waypoint: Vector = Vector(Position(10.0, 1.0), Direction.EAST)

  fun travel(): Vector {
    val x = ArrayDeque(instructions)

    while(x.isNotEmpty()) {
      val instruction = x.removeFirst()

      print("Position: $ship, Moving $instruction")
      ship = ship.acceptInstruction(instruction)
      print(". $ship\n")
    }
    return ship
  }

  fun travelWithWaypoint(): Vector {
    val x = ArrayDeque(instructions)
    var i = 0
    while(x.isNotEmpty()) {
      val instruction = x.removeFirst()
      i += 1
      println("Waypoint Position: $waypoint")
      println("Ship Position: $ship")
      println("Instruction $i, $instruction")
      if (instruction.ch == "F") {
        ship = ship.acceptInstruction2(instruction, waypoint.p)
      } else {
        waypoint = waypoint.acceptInstruction2(instruction, ship.p)
      }
    }
    return ship
  }

  fun solve(): Int {
    val x = travel()
    println(x)
    return (abs(x.p.x) + abs(x.p.y)).toInt()
  }

  fun solve2(): Int {
    val x = travelWithWaypoint()
    println(x)
    return (abs(x.p.x) + abs(x.p.y)).toInt()
  }
}
