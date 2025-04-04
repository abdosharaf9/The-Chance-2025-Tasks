package week_1.sudoko

import kotlin.math.sqrt

//fun isSudokuGridValid(grid: List<List<Char>>): Boolean {
//    return false
//}

/**
 * Validate if a given Sudoku board is a valid board or not.
 *
 * @param grid The Sudoku board you need to validate
 *
 * @return Returns true if the board is a valid Sudoku board, and false if not.
 * */
fun isSudokuGridValid(grid: List<List<Char>>): Boolean {
    if (grid.isEmpty()) {
        return false
    }

    val size = grid.size
    val subGridSize = sqrt(size.toDouble()).toInt()
    if (subGridSize * subGridSize != size) {
        return false
    }

    val cellRange = when (size) {
        4 -> ('1'..'4')
        9 -> ('1'..'9')
        16 -> ('1'..'9') + ('A'..'G')
        25 -> ('1'..'9') + ('A'..'P')
        else -> return false
    }.toSet() + '-'

    // Check rows
    for (row in grid) {
        if (row.size != size || !areCellsValid(cells = row, cellRange = cellRange)) {
            return false
        }
    }

    // Check columns
    for (col in 0..<size) {
        val curCol = List(size) { grid[it][col] }
        if (!areCellsValid(cells = curCol, cellRange = cellRange)) {
            return false
        }
    }

    // Check subgrids
    for (startRow in 0..<size step subGridSize) {
        for (startCol in 0..<size step subGridSize) {
            val subGrid = mutableListOf<Char>()

            for (row in startRow..<startRow + subGridSize) {
                for (col in startCol..<startCol + subGridSize) {
                    subGrid.add(grid[row][col])
                }
            }

            if (!areCellsValid(cells = subGrid, cellRange = cellRange)) {
                return false
            }
        }
    }

    return true
}

/**
 * Validate if a given list of cell contains duplicates or not, and check if all
 * the cell values are in the given values range.
 *
 * @param cells The list of cells you want to validate
 * @param cellRange Set of allowed numbers or letters values
 *
 * @return Returns true if the list doesn't contain duplicates, and false if not.
 * */
private fun areCellsValid(cells: List<Char>, cellRange: Set<Char>): Boolean {
    val seen = mutableSetOf<Char>()

    for (cell in cells) {
        if (cell in seen || cell !in cellRange) {
            return false
        }

        if (cell != '-') {
            seen.add(cell)
        }
    }

    return true
}