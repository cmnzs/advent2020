package day2


import org.hamcrest.MatcherAssert.assertThat
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream
import kotlin.test.assertEquals


internal class PolicyTest {

    @ParameterizedTest
    @MethodSource("validateArguments")
    fun part2Validate(
        ld: LineData,
        expected: Boolean
    ) {
        assertEquals(ld.isValid2(), expected)
    }

    private companion object {
        @JvmStatic
        fun validateArguments() = Stream.of(
            Arguments.of(
                LineData(
                    Policy(
                        1, 2, 'n'
                    ), "nntnnn"
                ),
                false
            ),
            Arguments.of(
                LineData(
                    Policy(
                        1, 3, 'n'
                    ), "ntnnn"
                ),
                false
            ),
            Arguments.of(
                LineData(
                    Policy(
                        1, 2, 'n'
                    ), "ntnnn"
                ),
                true
            ),
            Arguments.of(
                LineData(
                    Policy(
                        1, 2, 't'
                    ), "nt"
                ),
                true
            ),
            Arguments.of(
                LineData(policy=Policy(lower=9, upper=10, character='w'), password="wwwwwwwwsw"),
                true
            ),


        )
    }

}
