package week_1.sudoku

/*
Options for row/column/subgrid: null, empty, invalid size, invalid characters, invalid number range,
                                single duplicate, multiple duplicates, multiple have duplicates

Invalid entire grid when:
    - the grid is null ==> can't happen in my code
    - the grid is totally empty (there are no cells) -> false
    - all cells are empty (have '-') -> true
    - it's not a square grid, for example: 8 * 9, single row (1 * 9), or single column (9 * 1) -> false

Invalid row cases:
    - the row in null ==> can't happen in my code
    - the row is empty (have no cells) -> false
    - not all rows are the same size -> false
    - contains an empty cell -> true
    - contains a character out of the range 1:size, for example, '0' or 'a' -> false
    - contains duplicate numbers -> false
    - contains multiple duplicate numbers -> false
    - multiple rows contain duplicates -> false

Invalid column cases:
    - the column in null ==> can't happen in my code
    - the column is empty ==> can't happen because columns aren't lists
        • check if all the column cells are empty (have '-') -> true
    - not all columns are the same size ==> can't actually happen -> false
    - contains an empty cell (have '-') -> true
    - contains a character out of the range 1:size, for example: '0', '/', or 'a' -> false
    - contains duplicate numbers -> false
    - contains multiple duplicate numbers -> false
    - multiple columns contain duplicates -> false

Invalid subgrid cases:
    - contains an empty cell (have '-') -> true
    - contains duplicate numbers -> false
    - multiple subgrids contain duplicates -> false
*/


fun main() {
    // region Invalid Grid
    println("Grid Tests:")

    check(
        name = "Given an empty grid, when validating, then it should return false",
        result = validateSudokuGrid(emptyList()),
        correctResult = false
    )

    check(
        name = "Given a grid with all cells are empty, when validating, then it should return true",
        result = validateSudokuGrid(
            List(9) { List(9) { '-' } }
        ),
        correctResult = true
    )

    check(
        name = "Given a non square grid, when validating, then it should return false", // 8 * 9
        result = validateSudokuGrid(
            listOf(
                listOf('-', '-', '-', '-', '-', '-', '2', '-', '-'),
                listOf('-', '8', '-', '-', '-', '7', '-', '9', '-'),
                listOf('6', '-', '2', '-', '-', '-', '5', '-', '-'),
                listOf('-', '7', '-', '-', '6', '-', '-', '-', '-'),
                listOf('-', '-', '-', '9', '-', '1', '-', '-', '-'),
                listOf('-', '-', '-', '-', '2', '-', '-', '4', '-'),
                listOf('-', '-', '5', '-', '-', '-', '6', '-', '3'),
                listOf('-', '9', '-', '4', '-', '-', '-', '7', '-')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a single row grid, when validating, then it should return false", // 1 * 9
        result = validateSudokuGrid(
            listOf(
                listOf('-', '-', '-', '-', '-', '-', '2', '-', '-')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a single column grid, when validating, then it should return false", // 9 * 1
        result = validateSudokuGrid(
            listOf(
                listOf('-'),
                listOf('-'),
                listOf('6'),
                listOf('-'),
                listOf('-'),
                listOf('-'),
                listOf('-'),
                listOf('5'),
                listOf('-')
            )
        ),
        correctResult = false
    )

    // endregion


    // region Invalid Row
    println("\nRows Tests:")

    check(
        name = "Given a grid with an empty row, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The second row is empty
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                emptyList(),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with different row sizes, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The third row has 7 cells not 9
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('4', '8', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with a row that has empty cells, when validating, then it should return true",
        result = validateSudokuGrid(
            listOf( // The second row has an empty cell (first and fourth cells)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('-', '8', '3', '-', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = true
    )

    check(
        name = "Given a grid with a row that has an invalid special character, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The second row has an invalid special character (first cell)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('/', '8', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with a row that has an invalid character, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The second row has an invalid character (first cell)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('a', '8', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with a row that has an invalid number, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The second row has an invalid number (first cell)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('0', '8', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with a duplicate in a single row, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The second row has a duplicate (first and second cells)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('4', '4', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with multiple duplicates in a single row, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The second row has multiple duplicates (first, second, third, and fourth cells)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('4', '4', '2', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with multiple rows have duplicates, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The second and third rows have duplicates (first and second cells)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('4', '4', '3', '2', '5', '7', '1', '9', '6'),
                listOf('1', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    // endregion


    // region Invalid Column
    println("\nColumns Tests:")

    check(
        name = "Given a grid with an empty column, when validating, then it should return true",
        result = validateSudokuGrid(
            listOf( // The first column is empty
                listOf('-', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('-', '8', '3', '2', '5', '7', '1', '9', '6'),
                listOf('-', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('-', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('-', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('-', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('-', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('-', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('-', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = true
    )

    check(
        name = "Given a grid with a column that has an empty cell, when validating, then it should return true",
        result = validateSudokuGrid(
            listOf( // The first column has an empty cell (second and fourth cells)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('-', '8', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('-', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = true
    )

    check(
        name = "Given a grid with a column that has an invalid special character, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The first column has an invalid special character (second cell)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('/', '8', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with a column that has an invalid character, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The first column has an invalid character (second cell)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('a', '8', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with a column that has an invalid number, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The first column has an invalid number (second cell)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('0', '8', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with a duplicate in a single column, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The first column has a duplicate (first and second cells)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('9', '8', '3', '2', '5', '7', '1', '-', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with multiple duplicates in a single column, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The first column has multiple duplicates (first, second, third, and fourth cells)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('9', '8', '3', '2', '5', '7', '1', '-', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('6', '7', '8', '3', '-', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with multiple columns have duplicates, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The first and second columns have duplicates (first and second cells)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('9', '8', '3', '2', '5', '7', '1', '-', '6'),
                listOf('6', '5', '2', '8', '4', '9', '-', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    // endregion


    // region Invalid Subgrid
    println("\nSubgrid Tests:")

    check(
        name = "Given a grid with a subgrid that has an empty cell, when validating, then it should return true",
        result = validateSudokuGrid(
            listOf( // The top-left subgrid has an empty cell (middle cell)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('4', '-', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = true
    )

    check(
        name = "Given a grid with a subgrid that has a duplicate, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The top-left subgrid has a duplicate (first and last cells)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('4', '8', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '9', '8', '4', '-', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '-', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid with multiple subgrids have a duplicate, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The top-left and top-right subgrids have a duplicate number (first and second cells in each one)
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('4', '8', '3', '-', '5', '7', '1', '2', '6'),
                listOf('6', '1', '9', '8', '4', '-', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '-', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '-', '9')
            )
        ),
        correctResult = false
    )

    // endregion


    // region Valid Grid
    println("\nValid Sudoku Tests:")

    check(
        name = "Given a valid grid, when validating, then it should return true",
        result = validateSudokuGrid(
            listOf(
                listOf('9', '5', '7', '6', '1', '3', '2', '8', '4'),
                listOf('4', '8', '3', '2', '5', '7', '1', '9', '6'),
                listOf('6', '1', '2', '8', '4', '9', '5', '3', '7'),
                listOf('1', '7', '8', '3', '6', '4', '9', '5', '2'),
                listOf('5', '2', '4', '9', '7', '1', '3', '6', '8'),
                listOf('3', '6', '9', '5', '2', '8', '7', '4', '1'),
                listOf('8', '4', '5', '7', '9', '2', '6', '1', '3'),
                listOf('2', '9', '1', '4', '3', '6', '8', '7', '5'),
                listOf('7', '3', '6', '1', '8', '5', '4', '2', '9')
            )
        ),
        correctResult = true
    )

    // endregion


    // region 4 * 4 Sudoku
    println("\n4 * 4 Sudoku Tests: ")

    check(
        name = "Given a grid of size 4 * 4 with all cells are empty, when validating, then it should return true",
        result = validateSudokuGrid(
            List(4) { List(4) { '-' } }
        ),
        correctResult = true
    )

    check(
        name = "Given a grid of size 4 * 4 with a row that has a duplicate, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The first row has a duplicate (first and last cells)
                listOf('1', '-', '-', '1'),
                listOf('-', '3', '-', '2'),
                listOf('2', '-', '1', '-'),
                listOf('-', '-', '4', '-')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid of size 4 * 4 with a column that has a duplicate, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The last column has a duplicate (first and second cells)
                listOf('1', '-', '-', '2'),
                listOf('-', '3', '-', '2'),
                listOf('2', '-', '1', '-'),
                listOf('-', '-', '4', '-')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a grid of size 4 * 4 with a subgrid that has a duplicate, when validating, then it should return false",
        result = validateSudokuGrid(
            listOf( // The top-left subgrid has a duplicate (first and last cells)
                listOf('1', '-', '-', '-'),
                listOf('-', '1', '-', '2'),
                listOf('2', '-', '1', '-'),
                listOf('-', '-', '4', '-')
            )
        ),
        correctResult = false
    )

    check(
        name = "Given a valid grid of size 4 * 4, when validating, then it should return true",
        result = validateSudokuGrid(
            listOf(
                listOf('1', '-', '-', '-'),
                listOf('-', '3', '-', '2'),
                listOf('2', '-', '1', '-'),
                listOf('-', '-', '4', '-')
            )
        ),
        correctResult = true
    )

    // endregion


    // region 16 * 16 Sudoku
    println("\n16 * 16 Sudoku Tests: ")

    check(
        name = "Given a grid of size 16 * 16 with all cells are empty, when validating, then it should return true",
        result = validateSudokuGrid(
            List(16) { MutableList(16) { '-' } }
        ),
        correctResult = true
    )

    check(
        name = "Given a grid of size 16 * 16 with a row that has a duplicate, when validating, then it should return false",
        result = validateSudokuGrid(
            // The first row has a duplicate (first and second cells)
            List(16) { MutableList(16) { '-' } }.also {
                it[0][0] = 'A'
                it[0][1] = 'A'
            }
        ),
        correctResult = false
    )

    check(
        name = "Given a grid of size 16 * 16 with a column that has a duplicate, when validating, then it should return false",
        result = validateSudokuGrid(
            // The last column has a duplicate (first and fourth cells)
            List(16) { MutableList(16) { '-' } }.also {
                it[0][0] = 'G'
                it[0][4] = 'G'
            }
        ),
        correctResult = false
    )

    check(
        name = "Given a grid of size 16 * 16 with a subgrid that has a duplicate, when validating, then it should return false",
        result = validateSudokuGrid(
            // The top-left subgrid has a duplicate (first and last cells)
            List(16) { MutableList(16) { '-' } }.also {
                it[0][0] = 'D'
                it[3][3] = 'D'
            }
        ),
        correctResult = false
    )

    check(
        name = "Given a valid grid of size 16 * 16, when validating, then it should return true",
        result = validateSudokuGrid(
            List(16) { MutableList(16) { '-' } }.also {
                it[0][0] = '1'
                it[5][12] = '5'
            }
        ),
        correctResult = true
    )

    // endregion


    // region 25 * 25 Sudoku
    println("\n25 * 25 Sudoku Tests:")
    check(
        name = "Given a grid of size 25 * 25 with a subgrid that has a duplicate, when validating, then it should return false",
        result = validateSudokuGrid(
            // The top-left subgrid has duplicates
            List(25) { MutableList(25) { '-' } }.also {
                it[0][0] = 'P'
                it[1][0] = 'P'
                it[15][20] = '5'
            }
        ),
        correctResult = false
    )

    // endregion
}

private fun check(name: String, result: Boolean, correctResult: Boolean) {
    if (result == correctResult) {
        println("✅ Succeed - $name")
    } else {
        println("❌ Failed - $name")
    }
}
