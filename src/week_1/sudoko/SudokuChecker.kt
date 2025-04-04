package week_1.sudoko

fun main() {

}

fun isSudokuGridValid(grid: List<List<Char>>): Boolean {
    if (grid.isEmpty()) {
        return false
    }

    val size = grid.size
    if (size != 9) {
        return false
    }

    for (row in grid) {
        if (row.isEmpty() || row.size != size) {
            return false
        }

        if (!checkCellsList(row)) {
            return false
        }
    }

    for (col in 0..<size) {
        val curCol = mutableListOf<Char>()
        for (row in 0..<size) {
            curCol.add(grid[row][col])
        }

        if (!checkCellsList(curCol)) {
            return false
        }
    }

    val subGridSize = 3
    for (startRow in 0..<size step subGridSize) {
        for (startCol in 0..<size step subGridSize) {
            val seen = mutableListOf<Char>()

            for (row in startRow..<startRow + subGridSize) {
                for (col in startCol..<startCol + subGridSize) {
                    val cell = grid[row][col]
                    if (cell in seen || !checkSingleCell(cell)) {
                        return false
                    }

                    if (cell != '-') {
                        seen.add(cell)
                    }
                }
            }
        }
    }

    return true
}

private fun checkCellsList(cells: List<Char>): Boolean {
    val seen = mutableListOf<Char>()

    for (cell in cells) {
        if (cell in seen || !checkSingleCell(cell)) {
            return false
        }

        if (cell != '-') {
            seen.add(cell)
        }
    }

    return true
}

private fun checkSingleCell(cell: Char): Boolean {
    // TODO: Change the range to accept multiple sizes!!
    return when (cell) {
        '-' -> true
        in '1'..'9' -> true
        else -> false
    }
}