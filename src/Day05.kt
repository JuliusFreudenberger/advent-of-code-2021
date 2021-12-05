fun main() {

    open class Point(val x: Int, val y: Int) {
        constructor(input: String) : this(input.split(',')[0].trim().toInt(), input.split(',')[1].trim().toInt())

        override fun toString(): String {
            return "$x,$y"
        }
    }

    class Coordinate(x: Int, y: Int, var numberOfOverlappingLines: Int = 0) : Point(x, y)

    class Line {
        val start: Point
        val end: Point

        constructor(point1: Point, point2: Point) {
            if (point1.x == point2.x && point1.y <= point2.y ||
                    point1.y == point2.y && point1.x <= point2.x) {
                start = point1
                end = point2
            } else {
                start = point2
                end = point1
            }
        }

        constructor(input: String) : this(Point(input.split("->")[0]), Point(input.split("->")[1]))

        fun getMaxX(): Int {
            return Integer.max(start.x, end.y)
        }

        fun getMaxY(): Int {
            return Integer.max(start.y, end.y)
        }

        fun isHorizontal(): Boolean {
            return start.x == end.x
        }

        fun isVertical(): Boolean {
            return start.y == end.y
        }

        fun isHorizontalOrVertical(): Boolean {
            return isHorizontal() || isVertical()
        }

        override fun toString(): String {
            return "$start -> $end"
        }
    }

    class Floor(sizeX: Int, sizeY: Int) {
        val points = ArrayList<ArrayList<Coordinate>>(sizeX + 1)

        init {
            for (x in 0..sizeX) {
                points.add(x, ArrayList(sizeY + 1))
                for (y in 0..sizeY) {
                    points[x].add(y, Coordinate(x, y))
                }
            }
        }

        fun addLine(line: Line) {
            if (line.isVertical()) {
                for (x in line.start.x..line.end.x) {
                    points[x][line.start.y].numberOfOverlappingLines++
                }
            } else if (line.isHorizontal()) {
                for (y in line.start.y..line.end.y) {
                    points[line.start.x][y].numberOfOverlappingLines++
                }
            } else {
                throw NotImplementedError()
            }
        }

        fun getAmountOfPointsWithTwoOrMoreOverlappingLines(): Int {
            return points.sumOf { it.count { coordinate -> coordinate.numberOfOverlappingLines >= 2 } }
        }

        override fun toString(): String {
            return points.joinToString("\n") { line -> line.joinToString(" ") { coordinate -> coordinate.numberOfOverlappingLines.toString() } }
        }
    }


    fun part1(input: List<String>): Int {
        val lines = ArrayList<Line>()
        for (lineInput in input) {
            lines.add(Line(lineInput))
        }

        val maxX = lines.maxOf(Line::getMaxX)
        val maxY = lines.maxOf(Line::getMaxY)

        val floor = Floor(maxX, maxY)

        val horizontalOrVerticalLines = lines.filter(Line::isHorizontalOrVertical)

        horizontalOrVerticalLines.forEach(floor::addLine)
        return floor.getAmountOfPointsWithTwoOrMoreOverlappingLines()
    }

    fun part2(input: List<String>): Int {
        return 0
    }


// test if implementation meets criteria from the description, like:
    val testInput = readInput("Day05_test")
    check(part1(testInput) == 5)
    check(part2(testInput) == 0)

    val input = readInput("Day05")
    println(part1(input))
    println(part2(input))
}
