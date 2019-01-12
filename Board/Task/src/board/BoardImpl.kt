package board

import board.Direction.*

fun createSquareBoard(width: Int): SquareBoard = SquareBoardImpl(width)
fun <T> createGameBoard(width: Int): GameBoard<T> = TODO()


class SquareBoardImpl(override val width: Int) : SquareBoard {

    private val cells = Array(width, { row -> Array(width, { column -> Cell(row + 1, column + 1) }) })


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
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getColumn(iRange: IntProgression, j: Int): List<Cell> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun Cell.getNeighbour(direction: Direction): Cell? {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}

