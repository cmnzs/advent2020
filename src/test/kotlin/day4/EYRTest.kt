package day4

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class EYRTest {

    @ParameterizedTest
    @MethodSource("arguments")
    fun isValid(
        value: String,
        expected: Boolean
    ) {
        val rule = EYR()

        assertEquals(expected, rule.isValid(value))
    }

    companion object {
        @JvmStatic
        fun arguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    "2019",
                     false
                ),
                Arguments.of(
                    "2020",
                    true
                ),
                Arguments.of(
                    "2021",
                    true
                ),
                Arguments.of(
                    "2030",
                    true
                ),
                Arguments.of(
                    "2031",
                    false
                )
            )
        }
    }
}
