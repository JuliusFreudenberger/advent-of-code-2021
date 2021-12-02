fun main() {
    fun part1(input: List<String>): Int {
        var position = 0
        var depth = 0
        for (command in input) {
            val direction = command.substring(0, command.indexOf(' '))
            val units = command.substring(command.indexOf(' ') + 1).toInt()
            when (direction) {
                "forward" -> position += units
                "down" -> depth += units
                "up" -> depth -= units
            }
        }
        return position * depth
    }

    fun part2(input: List<String>): Int {
        var position = 0
        var depth = 0
        var aim = 0
        for (command in input) {
            val direction = command.substring(0, command.indexOf(' '))
            val units = command.substring(command.indexOf(' ') + 1).toInt()
            when (direction) {
                "forward" -> {
                    position += units
                    depth += units * aim
                }
                "down" -> aim += units
                "up" -> aim -= units
            }
        }
        return position * depth
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day02_test")
    check(part1(testInput) == 150)
    check(part2(testInput) == 900)

    val input = readInput("Day02")
    println(part1(input))
    println(part2(input))
}
