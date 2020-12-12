package day4

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class IYRTest {

    @ParameterizedTest
    @MethodSource("arguments")
    fun isValid(
        value: String,
        expected: Boolean
    ) {
        val rule = IYR()

        assertEquals(expected, rule.isValid(value))
    }

    companion object {
        @JvmStatic
        fun arguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    "2009",
                     false
                ),
                Arguments.of(
                    "2010",
                    true
                ),
                Arguments.of(
                    "2011",
                    true
                ),
                Arguments.of(
                    "2020",
                    true
                ),
                Arguments.of(
                    "2021",
                    false
                )
            )
        }
    }
}
