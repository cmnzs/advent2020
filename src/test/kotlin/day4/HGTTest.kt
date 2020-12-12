package day4

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream

internal class HGTTest {

    @ParameterizedTest
    @MethodSource("arguments")
    fun isValid(
        value: String,
        expected: Boolean
    ) {
        val rule = HGT()

        assertEquals(expected, rule.isValid(value))
    }

    companion object {
        @JvmStatic
        fun arguments(): Stream<Arguments> {
            return Stream.of(
                Arguments.of(
                    "149cm",
                     false
                ),
                Arguments.of(
                    "150cm",
                    true
                ),
                Arguments.of(
                    "160cm",
                    true
                ),
                Arguments.of(
                    "193cm",
                    true
                ),
                Arguments.of(
                    "194cm",
                    false
                ),
                Arguments.of(
                    "58in",
                    false
                ),
                Arguments.of(
                    "59in",
                    true
                ),
                Arguments.of(
                    "75in",
                    true
                ),
                Arguments.of(
                    "76in",
                    true
                ),
                Arguments.of(
                    "77in",
                    false
                )

            )
        }
    }
}
