package week_1.sudoku

import kotlin.math.sqrt

/**
 * Validate if a given Sudoku board is a valid board or not.
 *
 * @param grid The Sudoku board you need to validate.
 *
 * @return Returns true if the board is a valid Sudoku board, and false if not.
 * */
fun validateSudokuGrid(grid: List<List<Char>>): Boolean {
    if (grid.isEmpty()) {
        return false
    }

    val size = grid.size
    val subGridSize = sqrt(size.toDouble()).toInt()
    if (subGridSize * subGridSize != size) {
        return false
    }

    val cellRange = getCellsRange(size)

    if (!validateRows(grid = grid, size = size, cellRange = cellRange)) {
        return false
    }

    if (!validateColumns(grid = grid, size = size, cellRange = cellRange)) {
        return false
    }

    return validateSubGrids(
        grid = grid,
        size = size,
        subGridSize = subGridSize,
        cellRange = cellRange
    )
}

/**
 * Calculate the valid range of values for each cell according to the grid size.
 *
 * @param size The grid size, which will be the limit.
 *
 * @return A set of the values we can use in each cell.
 * */
private fun getCellsRange(size: Int): Set<Char> {
    return when (size) {
        4 -> ('1'..'4')
        9 -> ('1'..'9')
        16 -> ('1'..'9') + ('A'..'G')
        25 -> ('1'..'9') + ('A'..'P')
        else -> return emptySet()
    }.toSet() + '-'
}

/**
 * Validate if all the rows don't contain any duplicates, and
 * all their values are in the valid range.
 *
 * @param grid The Sudoku board you want to validate.
 * @param size The grid size.
 * @param cellRange The range of valid values to use in each cell.
 *
 * @return Returns true if all the rows are valid, and false if not.
 *
 * */
private fun validateRows(grid: List<List<Char>>, size: Int, cellRange: Set<Char>): Boolean {
    for (row in grid) {
        if (row.size != size || !validateAListOfCells(cells = row, cellRange = cellRange)) {
            return false
        }
    }

    return true
}

/**
 * Validate if all the columns don't contain any duplicates, and
 * all their values are in the valid range.
 *
 * @param grid The Sudoku board you want to validate.
 * @param size The grid size.
 * @param cellRange The range of valid values to use in each cell.
 *
 * @return Returns true if all the columns are valid, and false if not.
 * */
private fun validateColumns(grid: List<List<Char>>, size: Int, cellRange: Set<Char>): Boolean {
    for (column in 0..<size) {
        val currentColumn = List(size) { grid[it][column] }
        if (!validateAListOfCells(cells = currentColumn, cellRange = cellRange)) {
            return false
        }
    }

    return true
}

/**
 * Validate if all the subgrids don't contain any duplicates, and
 * all their values are in the valid range.
 *
 * @param grid The Sudoku board you want to validate.
 * @param size The grid size.
 * @param cellRange The range of valid values to use in each cell.
 *
 * @return Returns true if all the subgrids are valid, and false if not.
 * */
private fun validateSubGrids(
    grid: List<List<Char>>,
    size: Int,
    subGridSize: Int,
    cellRange: Set<Char>
): Boolean {
    for (startRow in 0..<size step subGridSize) {
        for (startColumn in 0..<size step subGridSize) {
            val curSubGrid = mutableListOf<Char>()

            for (row in startRow..<startRow + subGridSize) {
                for (column in startColumn..<startColumn + subGridSize) {
                    curSubGrid.add(grid[row][column])
                }
            }

            if (!validateAListOfCells(cells = curSubGrid, cellRange = cellRange)) {
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
 * @param cells The list of cells you want to validate.
 * @param cellRange Set of allowed numbers or letters values.
 *
 * @return Returns true if the list doesn't contain duplicates, and false if not.
 * */
private fun validateAListOfCells(cells: List<Char>, cellRange: Set<Char>): Boolean {
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