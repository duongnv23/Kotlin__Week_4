package board

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = GameBoardImpl(width)


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

    override fun getRow(i: Int, jRange: IntProgression): List<Cell> {
        if (i > width) {
            return emptyList()
        }

        return jRange.iterator().asSequence().takeWhile { it <= width }.map { cells[i-1][it-1] }.toList()
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        if (j > width) {
            return emptyList()
        }

        return iRange.iterator().asSequence().takeWhile { it <= width }.map { cells[it-1][j-1] }.toList()
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