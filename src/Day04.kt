fun main() {

    class Cell(val number: Int, var marked: Boolean = false) {

        override fun toString(): String {
            return (if (marked) "!$number" else "$number").padEnd(3)
        }
    }

    class Line(val line: List<Cell>) {
        fun checkLine(): Boolean {
            return line.none { cell -> !cell.marked }
        }

        fun sumUnmarkedCells(): Int {
            return line.filter { cell -> !cell.marked }.sumOf { cell -> cell.number }
        }

        override fun toString(): String {
            return line.joinToString(" ") { cell -> cell.toString() }
        }
    }

    class Board() {
        val board = ArrayList<Line>()

        fun checkBoard(): Boolean {
            return board.any { line -> line.checkLine() } ||
                    checkColumns()
        }

        fun checkColumns(): Boolean {
            val noColumns = board[0].line.size
            for (i in 0 until noColumns) {
                if (board.all { line -> line.line[i].marked }) return true
            }
            return false
        }

        fun sumUnmarkedCells(): Int {
            return board.sumOf { board -> board.sumUnmarkedCells() }
        }

        override fun toString(): String {
            return board.joinToString("\n") { line -> line.toString() }
        }
    }

    class Boards() {
        val boards = ArrayList<Board>()

        fun forEachCell(action: (Cell) -> Unit) {
            return boards.forEach { board -> board.board.forEach { line -> line.line.forEach(action) } }
        }

        fun checkBoards(): Boolean {
            return boards.any { board -> board.checkBoard() }
        }

        fun getWinningBoard(): Board {
            return boards.first { board -> board.checkBoard() }
        }

        override fun toString(): String {
            return boards.joinToString("\n\n") { board -> board.toString() }
        }
    }

    fun readBoards(input: List<String>): Boards {
        val boards = Boards()
        var currentBoard = Board()
        for (line in input) {
            if (line.isEmpty()) {
                boards.boards.add(currentBoard)
                currentBoard = Board()
            } else {
                currentBoard.board.add(Line(line.split(' ').filter { it.isNotEmpty() }.map { Cell(it.toInt()) }))
            }
        }
        boards.boards.add(currentBoard)
        return boards
    }

    fun markCells(boards: Boards, drawNumber: Int) {
        boards.forEachCell { cell -> if (cell.number == drawNumber) cell.marked = true }
    }

    fun part1(input: List<String>): Int {
        val drawNumbers = input[0].split(',').map { it.toInt() }
        val boards = readBoards(input.subList(2, input.size))
        for (drawNumber in drawNumbers) {
            markCells(boards, drawNumber)
            if (boards.checkBoards()) {
                return boards.getWinningBoard().sumUnmarkedCells() * drawNumber
            }
        }
        return 0
    }

    fun part2(input: List<String>): Int {
        return 0
    }


    // test if implementation meets criteria from the description, like:
    val testInput = readInput("Day04_test")
    check(part1(testInput) == 4512)
    check(part2(testInput) == 0)

    val input = readInput("Day04")
    println(part1(input))
    println(part2(input))
}
