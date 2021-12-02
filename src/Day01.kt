fun main() {
    fun part1(input: List<String>): Int {
        var numberIncreases = 0
        for (index in 1 until input.size) {
            if (input[index].toInt() > input[index - 1].toInt()) numberIncreases++
        }
        return numberIncreases
    }

    fun getWindowSum(input: List<String>, start: Int, end: Int): Int {
        var sum = 0
        for (index in start..end) {
            sum += input[index].toInt()
        }
        return sum
    }

    fun part2(input: List<String>): Int {
        var numberIncreases = 0
        val windowSize = 3
        for (index in 1 until input.size - (windowSize - 1)) {
            if (getWindowSum(input, index, index + (windowSize - 1)) > getWindowSum(input, index - 1, index + (windowSize - 2))) numberIncreases++
        }
        return numberIncreases
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day01_test")
    check(part1(testInput) == 7)
    check(part2(testInput) == 5)

    val input = readInput("Day01")
    println(part1(input))
    println(part2(input))
}
