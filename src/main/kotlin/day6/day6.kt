package day6

import files.loadFile
import files.splitFile
import files.splitLineSeparatedRecords

fun main() {

    val f = loadFile("day6/day6.txt").trim()
    val split = splitLineSeparatedRecords(f)
    println(split.map { manifest ->
        countManifest(manifest.filter { it.isNotEmpty() })
    }.sum())
}

fun countManifest(manifest: List<String>): Int {
    return manifest.map {
        it.toSet()
    }.filter { it.isNotEmpty() }.also { println(it) }.reduce { acc, set ->
        return@reduce acc.intersect(set)
    }.count()
}

