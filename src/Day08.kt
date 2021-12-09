fun main() {
    fun readOutputSegments(input: List<String>): List<String> {
        return input.flatMap { it.split("|")[1].split(" ").filter { s -> s != "|" } }
    }

    fun get1478SegmentCount(segments: List<String>): Int {
        return segments.count { it.length == 2 || it.length == 3 || it.length == 4 || it.length == 7 }
    }

    fun part1(input: List<String>): Int {
        val segments = readOutputSegments(input)
        return get1478SegmentCount(segments)
    }

    fun part2(input: List<String>): Int {
        return 0
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day08_test")
    check(part1(testInput) == 26)
    check(part2(testInput) == 0)

    val input = readInput("Day08")
    println(part1(input))
    println(part2(input))
}
