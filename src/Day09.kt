fun main() {

    fun readArea(input: List<String>): List<List<Int>> {
        return input.map { line -> line.map { point -> Integer.parseInt(point.toString()) }.toList() }.toList()
    }

    fun isLowestPointToAdjacent(area: List<List<Int>>, x: Int, y: Int): Boolean {
        val point = area[x][y]
        for (i in -1..1) {
            for (j in -1..1) {
                if (x + i < 0 || x + i >= area.size || y + j < 0 || y + j >= area[x].size) continue
                if ((i == 0 && j != 0 || j == 0 && i != 0) && point >= area[x+i][y+j]) {
                    return false
                }
            }
        }
        return true
    }

    fun part1(input: List<String>): Int {
        val area = readArea(input)
        var numberOfLowestPoints = 0
        var riskLevels = 0
        for (x in area.indices) {
            for (y in area[x].indices) {
                if (isLowestPointToAdjacent(area, x, y)) {
                    numberOfLowestPoints++
                    riskLevels += (1 + area[x][y])
                }
            }
        }
        return riskLevels
    }

    fun part2(input: List<String>): Int {
        return 0
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day09_test")
    check(part1(testInput) == 15)
    check(part2(testInput) == 0)

    val input = readInput("Day09")
    println(part1(input))
    println(part2(input))
}
