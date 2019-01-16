package board

class GameBoardImpl<T>(width: Int) : GameBoard<T>, SquareBoardImpl(width) {
    private val map: HashMap<Cell, T?>()
    override fun get(cell: Cell): T? {
        return map[cell]
    }

    override fun set(cell: Cell, value: T?) {
        map[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return map.entries.removeIf { predicate(it.value) }.
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        map.values.filter(predicate).
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}