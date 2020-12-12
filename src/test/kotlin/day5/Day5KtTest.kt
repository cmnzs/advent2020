package day4

import day5.getCol
import day5.getId
import day5.getRow

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class Day5KtTest {

    @ParameterizedTest
    @MethodSource("arguments")
    fun testRowNumber(
        value: String,
        expectedRow: Int,
        expectedCol: Int,
        expectedId: Int
    ) {


        val rowString = value.slice(0..6)
        val colString = value.slice(7..9)

        println("$rowString, $colString")
        assertEquals(expectedRow, getRow(rowString))
        assertEquals(expectedCol, getCol(colString))
        assertEquals(expectedId, getId(getRow(rowString), getCol(colString)))
    }

    companion object {
        @JvmStatic
        fun arguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    "FBFBBFFRLR",
                    44,
                    5,
                    357
                ),
                Arguments.of(
                    "BFFFBBFRRR",
                    70,
                    7,
                    567
                ),
                Arguments.of(
                    "FFFBBBFRRR",
                    14,
                    7,
                    119
                ),
                Arguments.of(
                    "BBFFBBFRLL",
                    102,
                    4,
                    820
                )
            )
        }
    }
}
