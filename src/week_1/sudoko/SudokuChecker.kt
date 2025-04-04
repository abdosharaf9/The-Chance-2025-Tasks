package week_1.sudoko

import kotlin.math.sqrt

fun main() {

}

fun isSudokuGridValid(grid: List<List<Char>>): Boolean {
    if (grid.isEmpty()) {
        return false
    }

    val size = grid.size
    val subGridSize = sqrt(size.toDouble()).toInt()
    if (subGridSize * subGridSize != size) {
        return false
    }

    val cellRange = if (size == 16) {
        (('1'..'9') + ('A'..'G') + ('a'..'g')).toSet()
    } else {
        (('1'..size.digitToChar())).toSet()
    } + '-'

    // Check rows
    for (row in grid) {
        if (row.isEmpty() || row.size != size) {
            return false
        }

        if (!checkCellsList(row, cellRange)) {
            return false
        }
    }

    // Check columns
    for (col in 0..<size) {
        val curCol = mutableListOf<Char>()
        for (row in 0..<size) {
            curCol.add(grid[row][col])
        }

        if (!checkCellsList(curCol, cellRange)) {
            return false
        }
    }

    // Check subgrids
    for (startRow in 0..<size step subGridSize) {
        for (startCol in 0..<size step subGridSize) {
            val seen = mutableListOf<Char>()

            for (row in startRow..<startRow + subGridSize) {
                for (col in startCol..<startCol + subGridSize) {
                    val cell = grid[row][col]
                    if (cell in seen || cell !in cellRange) {
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

private fun checkCellsList(cells: List<Char>, cellRange: Set<Char>): Boolean {
    val seen = mutableListOf<Char>()

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