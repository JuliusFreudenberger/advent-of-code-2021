fun main() {
    fun readFish(input: List<String>): MutableList<Int> {
        return input.flatMap { it.split(',').map { char -> char.toInt() }.toMutableList() }.toMutableList()
    }

    fun printFish(fish: List<Int>, generation: Int) {
        println("After %2d day%-1s: %s".format(generation, if (generation != 0) 's' else "", fish.joinToString(",")))
    }

    fun growFish(fish: MutableList<Int>) {
        for (i in fish.indices) {
            if (fish[i] == 0) {
                fish[i] = 6
                fish.add(8)
            } else {
                fish[i]--
            }
        }
    }

    fun part1(input: List<String>): Int {
        val fish = readFish(input)
        for (i in 1..80) {
            growFish(fish)
//            printFish(fish, i)
        }
        return fish.size
    }

    fun part2(input: List<String>): Int {
        return 0
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day06_test")
    check(part1(testInput) == 5934)
    check(part2(testInput) == 0)

    val input = readInput("Day06")
    println(part1(input))
    println(part2(input))
}
