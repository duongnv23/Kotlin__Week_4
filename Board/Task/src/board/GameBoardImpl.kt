package board

class GameBoardImpl<T>(width: Int) : GameBoard<T>, SquareBoardImpl(width) {
    private val map = HashMap<Cell, T?>()
    override fun get(cell: Cell): T? {
        return map[cell]
    }

    override fun set(cell: Cell, value: T?) {
        map[cell] = value
    }

    override fun filter(predicate: (T?) -> Boolean): Collection<Cell> {
        return map.entries.filter { it -> predicate(it.value) }.map { it.key }
    }

    override fun find(predicate: (T?) -> Boolean): Cell? {
        return map.entries.asSequence().filter { it -> predicate(it.value) }.map { it.key }.firstOrNull()
    }

    override fun any(predicate: (T?) -> Boolean): Boolean {
        return map.values.any(predicate)
    }

    override fun all(predicate: (T?) -> Boolean): Boolean {
        return map.values.all(predicate)
    }

}