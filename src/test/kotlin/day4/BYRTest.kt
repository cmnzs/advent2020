package day4

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class BYRTest {

    @Test
    fun isValid() {
        val x = BYR()
        assertTrue(x.isValid("1980"))
    }

    @Test
    fun isNotValid() {
        val x = BYR()
        assertFalse(x.isValid("1919"))
    }
}
