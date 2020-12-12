package day4

import files.splitFile
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class Day4KtTest {
    @Test
    fun testFileSplit() {
        val input = """
            asdf xyz:pop
            pop

            ghi
            
            klm
        """.trimIndent()

        assertEquals(listOf(
            listOf("asdf", "xyz:pop", "pop"), listOf("ghi"), listOf("klm")
        ), splitFile(input)
        )
    }

    @Test
    fun testValidation() {
        val testValues = """
            ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            byr:1937 iyr:2017 cid:147 hgt:183cm
        """.trimIndent()

        val sp = splitFile(testValues)

        val valid = hasRequiredKeys(sp.first())
        assertTrue(valid)
    }

    @Test
    fun testValidationBatch() {
        val testValues = """
            ecl:gry pid:860033327 eyr:2020 hcl:#fffffd
            byr:1937 iyr:2017 cid:147 hgt:183cm

            iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884
            hcl:#cfa07d byr:1929

            hcl:#ae17e1 iyr:2013
            eyr:2024
            ecl:brn pid:760753108 byr:1931
            hgt:179cm

            hcl:#cfa07d eyr:2025 pid:166559648
            iyr:2011 ecl:brn hgt:59in
        """.trimIndent()

        val sp = splitFile(testValues)

        val valid = validatePassports(sp)

        val (t, f) = valid.partition { it }
        println(valid)
        assertEquals(2, t.size)
        assertEquals(2, f.size)
    }

    @Test
    fun testValidPassports() {
        val t = """
            pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
            hcl:#623a2f            cxsq

            eyr:2029 ecl:blu cid:129 byr:1989
            iyr:2014 pid:896056539 hcl:#a97842 hgt:165cm

            hcl:#888785
            hgt:164cm byr:2001 iyr:2015 cid:88
            pid:545766238 ecl:hzl
            eyr:2022

            iyr:2010 hgt:158cm hcl:#b6652a ecl:blu byr:1944 eyr:2021 pid:093154719
        """.trimIndent()

        val sp = splitFile(t)
        val result = validatePassports(sp)
        println(result)

        assertTrue(result.all { it })
    }

    @Test
    fun testIndividualValidPassport() {
        val t = """
            pid:087499704 hgt:74in ecl:grn iyr:2012 eyr:2030 byr:1980
            hcl:#623a2f            cxsq
        """.trimIndent()

        splitFile(t).first()
    }


}
