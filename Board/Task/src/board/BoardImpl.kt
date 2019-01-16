package board

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = TODO()


open class SquareBoardImpl(override val width: Int) : SquareBoard {

    private val cells = Array(width) { row -> Array(width) { column -> Cell(row + 1, column + 1) } }

    override fun getCellOrNull(i: Int, j: Int): Cell? {
        if (i < 1 || i > width || j < 1 || j > width) {
            return null
        }
        return cells[i - 1][j - 1]
    }

    override fun getCell(i: Int, j: Int): Cell {
        if (i < 1 || i > width || j < 1 || j > width) {
            throw IllegalArgumentException("incorrect values of $i and $j")
        }
        return cells[i - 1][j - 1]
    }

    override fun getAllCells(): Collection<Cell> {
        return cells.iterator().asSequence().flatMap { it -> it.iterator().asSequence() }.toList()
    }

    override fun getRow(row: Int, columnRange: IntProgression): List<Cell> {
        if (row > width) {
            return emptyList()
        }

        return columnRange.iterator().asSequence().takeWhile { it <= width }.map { cells[row-1][it-1] }.toList()
    }

    override fun getColumn(rowRange: IntProgression, column: Int): List<Cell> {
        if (column > width) {
            return emptyList()
        }

        return rowRange.iterator().asSequence().takeWhile { it <= width }.map { cells[it-1][column-1] }.toList()
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        return when (direction) {
            Direction.UP -> getCellOrNull(i - 1, j)
            Direction.DOWN -> getCellOrNull(i + 1, j)
            Direction.LEFT -> getCellOrNull(i, j - 1)
            Direction.RIGHT -> getCellOrNull(i, j + 1)
        }
    }

}