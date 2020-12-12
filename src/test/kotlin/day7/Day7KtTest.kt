package day7

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class Day7KtTest {

    val s = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
            dark orange bags contain 3 bright white bags, 4 muted yellow bags.
            bright white bags contain 1 shiny gold bag.
            muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
            shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
            dark olive bags contain 3 faded blue bags, 4 dotted black bags.
            vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
            faded blue bags contain no other bags.
            dotted black bags contain no other bags.
        """.trimIndent()

    @Test
    fun testCountGold() {
//        assertEquals(4, parseAndCount(s))
        println(parseAndSearch(s))
    }

    @Test
    fun testNewCount(){

    }
    @Test
    fun testGoldContents() {
        val s1 = """
            shiny gold bags contain 2 dark red bags.
            dark red bags contain 2 dark orange bags.
            dark orange bags contain 2 dark yellow bags.
            dark yellow bags contain 2 dark green bags.
            dark green bags contain 2 dark blue bags.
            dark blue bags contain 2 dark violet bags.
            dark violet bags contain no other bags.
        """.trimIndent()


        """
            2^6 + 2^5 + 2^4 + 2^3 + 2^2 + 2^1
        """.trimIndent()
//        val search = search("shiny gold", parseRules(s1), null)
//        val r = countFromRules(parseRules(s1))
        val r = count("shiny gold", parseRules(s1))
        assertEquals(126, r)
    }
    @Test
    fun parseString() {

        assertEquals(
            listOf(
                BagRule(
                    colour = "light red",
                    contents = BagContents(contents = listOf(Pair("bright white", 1), Pair("muted yellow", 2)))
                ),
                BagRule(
                    colour = "dark orange",
                    contents = BagContents(contents = listOf(Pair("bright white", 3), Pair("muted yellow", 4)))
                ),
                BagRule(colour = "bright white", contents = BagContents(contents = listOf(Pair("shiny gold", 1)))),
                BagRule(
                    colour = "muted yellow",
                    contents = BagContents(contents = listOf(Pair("shiny gold", 2), Pair("faded blue", 9)))
                ),
                BagRule(
                    colour = "shiny gold",
                    contents = BagContents(contents = listOf(Pair("dark olive", 1), Pair("vibrant plum", 2)))
                ),
                BagRule(
                    colour = "dark olive",
                    contents = BagContents(contents = listOf(Pair("faded blue", 3), Pair("dotted black", 4)))
                ),
                BagRule(
                    colour = "vibrant plum",
                    contents = BagContents(contents = listOf(Pair("faded blue", 5), Pair("dotted black", 6)))
                ),
                BagRule(colour = "faded blue", contents = BagContents(contents = listOf())),
                BagRule(colour = "dotted black", contents = BagContents(contents = listOf()))
            ),
            parseString(s)
        )

    }

    @Test
    fun parseLine() {
        val s = """
            light red bags contain no other bags.
        """.trimIndent()

        val expected = BagRule(
            "light red",
            BagContents(emptyList())
        )

        assertEquals(expected, parseLine(s))
    }

    @Test
    fun testSplit() {
        val s = """
            light red bags contain no other bags.
        """.trimIndent()

        println(s.split("contain").map { it.trim() })
    }

    @Test
    fun testSplit2() {
        val s = """
            light red bags contain 1 bright white bag, 2 muted yellow bags.
        """.trimIndent()

        println(s.split("contain").map { it.trim() })
    }

    @Test
    fun testRegex() {
        val s = """
            1 bright white bag, 2 muted yellow bags.
        """.trimIndent()


        val capturePattern = Regex("""(\d) ([a-z ]+)(?:bag|bags).""")
        val matchResults = capturePattern.findAll(s)
            .map { it.groupValues }
            .map { it.drop(1).map { it.trim() } }
            .toList()

        assertEquals(
            listOf(
                listOf(
                    "1", "bright white"
                ),
                listOf(
                    "2", "muted yellow"
                )
            ),
            matchResults
        )
    }
}
