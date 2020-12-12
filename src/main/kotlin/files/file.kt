package files

fun loadFile(path: String) = ClassLoader.getSystemResource(path).readText()

