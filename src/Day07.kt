import kotlin.math.abs

fun main() {
    fun readCrabs(input: List<String>): List<Int> {
        return input.flatMap { it.split(',').map { char -> char.toInt() }.toList() }.toList()
    }

    fun getConstantFuelForPoint(crabs: List<Int>, point: Int): Int {
        return crabs.sumOf { crab -> abs(crab - point) }
    }

    fun getIncreasingFuelForPoint(crabs: List<Int>, point: Int): Int {
        return crabs.sumOf { crab -> (1..abs(crab - point)).sum() }
    }

    fun getFuelForNearestPoint(crabs: List<Int>): Int {
        var fuelForNearestPoint = Int.MAX_VALUE
        for (i in crabs.minOf { it }..crabs.maxOf { it }) {
            val fuelForPoint = getConstantFuelForPoint(crabs, i)
            if (fuelForPoint < fuelForNearestPoint) {
                fuelForNearestPoint = fuelForPoint
            }
        }
        return fuelForNearestPoint
    }

    fun getConstantFuelForNearestPoint(crabs: List<Int>): Int {
        var fuelForNearestPoint = Int.MAX_VALUE
        for (i in crabs.minOf { it }..crabs.maxOf { it }) {
            val fuelForPoint = getIncreasingFuelForPoint(crabs, i)
            if (fuelForPoint < fuelForNearestPoint) {
                fuelForNearestPoint = fuelForPoint
            }
        }
        return fuelForNearestPoint
    }

    fun part1(input: List<String>): Int {
        val crabs = readCrabs(input)
        return getFuelForNearestPoint(crabs)
    }

    fun part2(input: List<String>): Int {
        val crabs = readCrabs(input)
        return getConstantFuelForNearestPoint(crabs)
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day07_test")
    check(part1(testInput) == 37)
    check(part2(testInput) == 168)

    val input = readInput("Day07")
    println(part1(input))
    println(part2(input))
}
