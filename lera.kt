package ru.itcodegroup.myapplication

object LeraLib {
    enum class Delete {
        FIRST,
        ALL
    }

    //Удаление. Даны две строки удалить первое попавшееся совпадение. Пример: "Я аабаа" "аа". Результат: "Я баа"
    //Найти совпадение, определить его длину, Пропустить эту длину
    fun deleteString(str: String, deletable: String, type: Delete): String {
        var newString: String = ""
        var index: Int = 0
        var count: Int = 0

        while (index < str.length) {
            val cutStr = getSubstring(str, index, deletable.length)
            if (count == 0 && isStringsEqual(cutStr, deletable)) {
                index += deletable.length - 1
                if (Delete.FIRST == type)
                    count++
            } else
                newString += str[index]
            index++
        }
        return newString
    }

    fun insertString(source: String, destination: String, startIndex: Int): String {
        return LeraLib.getSubstring(destination, 0, startIndex) + source + LeraLib.getSubstring(destination, startIndex, destination.length)
    }

    // возьми подстроку
    fun getSubstring(str: String, startIndex: Int, substrLength: Int): String {
        var newString: String = ""
        var index: Int = startIndex
        while (newString.length < substrLength) {
            if (str.length == index)
                break
            newString += str[index]
            index++
        }
        return newString
    }

    // количество совпадений
    fun countMatches(string: String, symbol: Char): Int {
        var count: Int = 0
        var matches: Int = 0
        while (count < string.length) {
            if (string[count] == symbol)
                matches++
            count++
        }
        return matches
    }

    // добавить дополнительнцый символ с периодом в
    fun addAdditionalSymbolEveryPeriodOf(oldString: String, additionalSymbol: Char, everyPeriodOf: Int): String {
        var newString: String = ""
        var count: Int = 0
        while (count < oldString.length) {
            newString += oldString[count]
            count++
            if (count % everyPeriodOf == 0)
                newString += additionalSymbol
        }
        return newString
    }

    // в маленький регистр
    fun toLowerCase(symbol: Char): Char {
        if (symbol.code in 65..90)
            return symbol + 32
        return symbol
    }

    //в большой регистр
    fun toUpperCase(symbol: Char): Char {
        if (symbol.code in 97..122)
            return symbol - 32
        return symbol
    }

    // строки равны?
    fun isStringsEqual(string1: String, string2: String): Boolean {
        var count: Int = 0
        if (string1.length != string2.length)
            return false
        while (count < string1.length) {
            if (string1[count] != string2[count])
                return false
            count++
        }
        return true
    }

    // переверни строку
    fun reverseString(oldString: String): String {
        var reversedString: String = ""
        var count: Int = 0
        while (count < oldString.length) {
            reversedString = oldString[count] + reversedString
            count++
        }
        return reversedString
    }
}

class LeraTasks {
    // Подсчитай произведение элементов массива
    fun calculateMultOfArray(array: Array<Int>): Int {
        var index: Int = 0
        var result: Int = 1
        while (index < array.size) {
            result *= array[index]
            index++
        }
        return result
    }

    //Подсчитать разность произведения четных и нечетных элементов массива
    fun calculateDifferenceOfEvenAndOddMults(array: Array<Int>): Int {
        var index: Int = 0
        var evenMult: Int = 1
        var oddMult: Int = 1
        while (index < array.size) {
            if (array[index] % 2 == 0)
                evenMult *= array[index]
            else
                oddMult *= array[index]
            index++
        }
        return evenMult - oddMult
    }


    //Дана строка. Напиши функцию, которая создаст новую строку, где каждый чётный символ будет маленьким, а нечётный большим
    fun capitalize(oldString: String): String {
        var newString: String = ""
        var index: Int = 0
        while (index < oldString.length) {
            if (index % 2 != 0)
                newString += LeraLib.toLowerCase(oldString[index])
            else
                newString += LeraLib.toUpperCase(oldString[index])
            index++
        }
        return newString
    }

    // Поиск. Даны две строки. Проверить сколько раз одна строка входит в другую. Пример: "qwerty" "qwe" = 1. "ononqoq" "on" = 2.
    fun countNumberOfSubstrings(str: String, substr: String): Int {
        var index: Int = 0
        var count: Int = 0
        while (index < str.length) {
            val cutStr = LeraLib.getSubstring(str, index, substr.length)
            if (LeraLib.isStringsEqual(cutStr, substr))
                count++
            index++
        }
        return count
    }
}

class TestTasks {
    val tasks: LeraTasks = LeraTasks()

    fun calculateMultOfArray() {
        val array: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6)
        val result: Int = tasks.calculateMultOfArray(array)
        println("Произведение массива = $result")
    }

    fun calculateDifferenceOfEvenAndOddMults() {
        val array: Array<Int> = arrayOf(1, 2, 3, 4, 5, 6)
        val result: Int = tasks.calculateDifferenceOfEvenAndOddMults(array)
        println("Разность произведения четных и нечетных элементов массива = $result")
    }

    fun countMatches() {
        val string: String = "wefiw73IUQAWIQwrh"
        val symbol: Char = 'w'
        val result: Int = LeraLib.countMatches(string, symbol)
        println("Количество совпадений в строке символа $symbol = $result")
    }

    fun capitalize() {
        val string: String = "g34yhgrd45eydh"
        val result: String = tasks.capitalize(string)
        println("Новая изменённая строка - |$result|")
    }

    fun addAdditionalSymbolEveryPeriodOf() {
        val string: String = "g34yhgrd45eydh"
        val result: String = LeraLib.addAdditionalSymbolEveryPeriodOf(string, ' ', 3)
        println("Новая изменённая строка - |$result|")
    }

    fun reverseString() {
        val string: String = "0123456789"
        val result: String = LeraLib.reverseString(string)
        println("Перевёрнутая строка - |$result|")
    }

    fun isStringsEqual() {
        val string: String = "qwerty1"
        val string2: String = "qwerty2"
        val result: Boolean = LeraLib.isStringsEqual(string, string2)
        println("Строка |$string| равна строке |$string2|? $result")
    }

    fun countNumberOfSubstrings() {
        val string: String = "qqwqwerty"
        val substr: String = "qw"
        val result: Int = tasks.countNumberOfSubstrings(string, substr)
        println("Количество подстрок |$substr| в строке |$string| = $result")
    }

    fun insertString() {
        val destination: String = "123"
        val source: String = "4"
        val result: String = LeraLib.insertString(source, destination, destination.length)
        println("Новая строка |$result|")
    }

    fun deleteString() {
        val str: String = "Я аабаа"
        val deletable: String = "аа"
        val result: String = LeraLib.deleteString(str, deletable, LeraLib.Delete.ALL)
        println("Исходная строка |$str| Удаляемое |$deletable| Новая строка |$result|")
    }


}

fun isArraySortedAscending(array: Array<Int>): Boolean {
    var index: Int = 0
    while (index < array.size - 1) {
        if (array[index] > array[index + 1])
            return false
        index++
    }
    return true
}

fun doBubbleSort(array: Array<Int>) {
    var index: Int = 0
    var temp: Int = 0
    while (!isArraySortedAscending(array)) {
        if (array[index] > array[index + 1]) {
            temp = array[index]
            array[index] = array[index + 1]
            array[index + 1] = temp
        }
        index++
        if (index == array.size - 1)
            index = 0
    }
    printArray(array)
}

fun printArray(array: Array<Int>) {
    var index = 0
    while (index < array.size) {
        print(array [index])
        index++
    }
}

fun main() {
    val test: TestTasks = TestTasks()

    doBubbleSort(arrayOf<Int>(4, 8, 3, 5, 1))// 4 раза пройдёт по массиву 43815 34158 31458 13458
}
