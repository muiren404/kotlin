package ru.itcodegroup.myapplication

import java.lang.Exception
import java.lang.IllegalStateException

// Судоку верное, если есть более 16 цифр .
// Если элементов 9 в ряду
// Если 9 строк
// Если все элементы цифры от 1 до 9 или точки.
// Если цифры не повторяются в одном ряду, столбце и блоке 3х3.
// и судоку имеет единственное решение
val VALID_SUDOKU_NUMBER_COUNT = 17

fun printInitial(array: ArrayList<String>) {
    for (item in array) {
        for (cIndex in 0 until item.length) {
            print("${item[cIndex]}  ")
        }
        println()
    }
    println()
}

class Validation {

    fun isValid(array: ArrayList<String>) {
        var countOfNumber = 0
        if (array.size != 9)
            throw IllegalStateException("Не 9 строк")
        for (index in 0..array.lastIndex) {
            val row = array[index]
            if (row.length != 9)
                throw IllegalStateException("В строке ${index + 1} не 9 символов")
            countOfNumber += checkContentOfRow(row)
        }
        if (countOfNumber < VALID_SUDOKU_NUMBER_COUNT)
            throw IllegalStateException("В таблице менее $VALID_SUDOKU_NUMBER_COUNT цифр")
    }

    private fun checkContentOfRow(row: String): Int {
        var count = 0
        var index = 0
        while (index < row.length) {
            val symbol: Char = row[index]
            if (isNumber(symbol))
                count++
            if (!isValidSymbol(symbol))
                throw IllegalStateException("Судоку не должно содержать символ |$symbol|")
            index++
        }
        return count
    }

    private fun isValidSymbol(symbol: Char): Boolean {
        return isNumber(symbol) || symbol == '.'
    }

    private fun isNumber(symbol: Char): Boolean {
        return symbol.code in 49..57
    }
}

class Solver(arrayOfStrings: ArrayList<String>) {

    private var baseMap: MutableMap<Int, MutableMap<Int, Int>> = mutableMapOf()
    private var solutions: MutableSet<MutableMap<Int, MutableMap<Int, Int>>> = mutableSetOf()

    init {
        baseMap = convertToMap(arrayOfStrings)
        sudokuRowColumnMatrixValidation()
        solve(baseMap)
        checkSolutions()
    }

    private fun sudokuRowColumnMatrixValidation() {
        convertMapToList(baseMap)
    }

    private fun checkSolutions() {
        when {
            solutions.size > 1 -> {
                var index = 1
                for (solution in solutions) {
                    println("Решение ${index}:")
                    printResult(solution)
                    index++
                }
                throw IllegalStateException("Судоку имеет несколько решений: ${solutions.size}.")
            }
            solutions.isEmpty() -> {
                throw IllegalStateException("Судоку не имеет решения.")
            }
            else -> {
                println("Решение:")
                printResult(solutions.first())
            }
        }

    }

    private fun printResult(solution: MutableMap<Int, MutableMap<Int, Int>>) {
        for ((rowIndex, row) in solution) {
            for ((columnIndex, value) in row) {
                if (value == 0)
                    print(".  ")
                else
                    print("$value  ")
            }
            println()
        }
        println()
        println()
    }

    private fun convertToMap(arrayOfStrings: ArrayList<String>): MutableMap<Int, MutableMap<Int, Int>> {
        val map = mutableMapOf<Int, MutableMap<Int, Int>>()
        for (index in 1..9)
            map[index] = mutableMapOf()
        for (rIndex in 0..arrayOfStrings.lastIndex) {
            val row = arrayOfStrings[rIndex]
            for (cIndex in 0 until row.length)
                map[rIndex + 1]!![cIndex + 1] = when (row[cIndex]) {
                    in '1'..'9' -> row[cIndex].code - 48
                    else -> 0
                }
        }
        return map
    }

    private fun solve(map: MutableMap<Int, MutableMap<Int, Int>>) {
        val copiedMap = mutableMapOf<Int, MutableMap<Int, Int>>()
        for (index in 1..9)
            copiedMap[index] = mutableMapOf()
        for ((rowIndex, row) in map) {
            for ((columnIndex, value) in row) {
                copiedMap[rowIndex]!![columnIndex] = value
            }
        }
        while (true) {
            var list: List<SudokuElement> = listOf()
            try {
                list = convertMapToList(copiedMap)
            } catch (e: Exception) {
                if (e is IllegalStateException)
                    return
                else
                    throw Exception(e.message)
            }

            if (list.find { it.value == 0 } == null)
                break
            if (list.find { it.value == 0 && it.unUsedNumbers.isEmpty() } != null)
                return
            list = list.sortedWith(compareBy<SudokuElement> { it.value != 0 }.thenBy { it.unUsedNumbers.size })
            val filtered = list.filter { it.unUsedNumbers.size == 1 }
            if (filtered.isEmpty()) {
                for (number in list.first().unUsedNumbers) {
                    copiedMap[list.first().rowIndex]!![list.first().columnIndex] = number
                    solve(copiedMap)
                }
            } else {
                for (item in filtered)
                    copiedMap[item.rowIndex]!![item.columnIndex] = item.unUsedNumbers.first()
            }
        }
        printResult(copiedMap)
        solutions.add(copiedMap)
        //для нахождения и вывода всех решений комментить нижние 2 строки
        if (solutions.size > 1)
            throw IllegalStateException("Судоку имеет более одного решения.")
    }

    private fun convertMapToList(map: MutableMap<Int, MutableMap<Int, Int>>): List<SudokuElement> {
        val list = mutableListOf<SudokuElement>()
        for ((rowIndex, row) in map) {
            for ((columnIndex, value) in row) {
                list.add(
                    SudokuElement(
                        value,
                        rowIndex,
                        columnIndex,
                        map
                    )
                )
            }
        }
        return list.toList()
    }
}

data class SudokuElement(
    var value: Int,
    val rowIndex: Int,
    val columnIndex: Int,
    val map: MutableMap<Int, MutableMap<Int, Int>>
) {
    var row: List<Int> = listOf()
    var column: List<Int> = listOf()
    var matrix: List<Int> = listOf()
    var unUsedNumbers: List<Int> = listOf()

    init {
        fillEntities()
        checkRules()
    }

    private fun checkRules() {
        var count = 0
        for (number in 1..9) {
            count = 0
            count += row.count { it == number }
            count += column.count { it == number }
            count += matrix.count { it == number }
            if (count > 3)
                throw IllegalStateException("Элемент $number в $rowIndex ряду и $columnIndex столбце дублируется")
        }
    }

    private fun fillEntities() {
        fillMatrix()
        fillColumn()
        fillRow()
        if (value == 0)
            fillUnUsedNumbers()
    }

    private fun fillUnUsedNumbers() {
        val mutableList = mutableListOf<Int>()
        for (number in 1..9) {
            if (!row.contains(number) && !column.contains(number) && !matrix.contains(number))
                mutableList.add(number)
        }
        unUsedNumbers = mutableList.toList()
    }

    private fun fillRow() {
        val mutableList = mutableListOf<Int>()
        for (cIndex in 1..9)
            mutableList.add(map[rowIndex]!![cIndex]!!)
        row = mutableList.toList()
    }

    private fun fillColumn() {
        val mutableList = mutableListOf<Int>()
        for (rIndex in 1..9)
            mutableList.add(map[rIndex]!![columnIndex]!!)
        column = mutableList.toList()
    }

    private fun fillMatrix() {
        val rowRange: IntRange = if (rowIndex >= 7) 7..9 else if (rowIndex >= 4) 4..6 else 1..3
        val columnRange: IntRange = if (columnIndex >= 7) 7..9 else if (columnIndex >= 4) 4..6 else 1..3

        val matrixMutable = mutableListOf<Int>()
        for (rIndex in rowRange)
            for (cIndex in columnRange)
                matrixMutable.add(map[rIndex]!![cIndex]!!)
        matrix = matrixMutable.toList()
    }
}

fun main() {
    val array = arrayListOf(
        "..1...2..",
        ".3.....4.",
        "5...3...6",
        "...1.7...",
        ".4.....8.",
        "...9.2...",
        "3.......8",
        ".6..5..3.",
        "..2...7.."
    )
    printInitial(array)
    Validation().isValid(array)
    Solver(array)
}
