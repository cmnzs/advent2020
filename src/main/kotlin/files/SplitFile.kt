package files

fun splitFile(contents: String, innerDelim: String = " ", outerDelim: String = "\n"): List<List<String>> {
    return contents
        .split(outerDelim)
        .fold(mutableListOf(mutableListOf<String>())) { acc,
                                                        nextVal ->

            if (nextVal == "") {
                acc.add(mutableListOf<String>())
            } else {
                acc.last().addAll(nextVal.split(innerDelim).map { it.trim() })
            }

            return@fold acc
        }
}

fun splitLineSeparatedRecords(contents: String): List<List<String>> {
    return contents
        .split("\n")
        .fold(mutableListOf(mutableListOf<String>())) { acc,
                                                        nextVal ->

            if (nextVal == "") {
                acc.add(mutableListOf<String>())
            } else {
                acc.last().add(nextVal.trim())
            }

            return@fold acc
        }
}
