package day8

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day8KtTest {

    val s = """
            nop +0
            acc +1
            jmp +4
            acc +3
            jmp -3
            acc -99
            acc +1
            jmp -4
            acc +6
        """.trimIndent()
    @Test
    fun parseFile() {

        assertEquals(listOf(
            "nop" to +0,
            "acc" to +1,
            "jmp" to +4,
            "acc" to +3,
            "jmp" to -3,
            "acc" to -99,
            "acc" to +1,
            "jmp" to -4,
            "acc" to +6
        ).mapIndexed { i, it -> IndexedInstruction(i, it.first, it.second) },
            parseFile(s))
    }

    @Test
    fun testProc() {
        assertEquals(5, proc(day8.parseFile(s)))
    }

    @Test
    fun testFindInvalidInstruction(){
        assertEquals(8, correctAndRun(day8.parseFile(s)))
    }
}
