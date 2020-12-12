package day6

import files.splitLineSeparatedRecords
import org.junit.jupiter.api.Test

internal class Day6KtTest {

    @Test
    fun countManifest() {
    }

    @Test
    fun toSet() {
        val input = """
            abc

            a
            b
            c

            ab
            ac

            a
            a
            a
            a

            b
        """.trimIndent()
        val y = splitLineSeparatedRecords(input)
        y.forEach {
            println(countManifest(it))
        }

    }
}
