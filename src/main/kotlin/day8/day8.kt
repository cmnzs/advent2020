package day8

import files.loadFile
import java.lang.Integer.parseInt

fun main() {
    val s = loadFile("day8/day8.txt")
    val param = parseFile(s)
    println("Part 1: ${proc(param)}")

    println("Part 2: ${correctAndRun(param)}")
}

typealias Instruction = Pair<String, Int>

fun parseFile(s: String): List<IndexedInstruction> {
    return s.split("\n").filter { it.isNotEmpty() }.mapIndexed { i, it ->
        val lineSplit = it.split(" ")
        val op = lineSplit[0]
        val n = parseInt(lineSplit[1])
        return@mapIndexed IndexedInstruction(i, op, n)
    }
}

data class IndexedInstruction(
    val index: Int,
    val name: String,
    val argument: Int
)

fun correctAndRun(l: List<IndexedInstruction>): Int {

    val possiblePrograms = l.filter { it.name != "acc" }.map { it ->
        val copy = l.toMutableList()
        val newInstruction = IndexedInstruction(
            it.index,
            when (it.name) {
                "nop" -> "jmp"
                "jmp" -> "nop"
                else -> "failure"
            },
            it.argument
        )
        copy[it.index] = newInstruction
        copy
    }

    return possiblePrograms.map { p ->
        proc(p)
    }.first { it != -9999 }
}

fun proc(l: List<IndexedInstruction>): Int {
    var acc = 0

    var go = true

    val m = mutableMapOf<IndexedInstruction, Boolean>()
    var instruction = l.first()
    val executionOrder = mutableListOf<IndexedInstruction>()

    var nextIndex = -1

    while (go) {
        executionOrder.add(instruction)
        if (instruction in m) {
            println("The repeated instruction is: $instruction.")
            return -9999
        }
        m[instruction] = true
        when (instruction.name) {
            "acc" -> {
                acc += instruction.argument
                nextIndex = instruction.index + 1
            }
            "jmp" -> {
                nextIndex = instruction.index + instruction.argument
            }
            "nop" -> {
                nextIndex = instruction.index + 1
            }
        }
        if (nextIndex >= l.size) {
            go = false
        } else {
            instruction = l[nextIndex]
        }
    }
    return acc
}

