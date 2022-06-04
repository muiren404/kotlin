package ru.itcodegroup.myapplication

//Напмсать функцию, которая вставляет новый элемент по указанному индексу
// fun addByIndex(array: ArrayList<Int>, index: Int, value: Int)
// Например массив [0,1,2,3,4,5,6] добавить элемент 13 по индексу 4. [0,1,2,3,4,4,5,6]  [0,1,2,3,13,4,5,6]


//fun addByIndex(array: ArrayList<Int>, newValueIndex: Int, value: Int) {
//    var lastIndex = array.lastIndex
//
//    array.add(array[array.lastIndex])
//
//    while (lastIndex > newValueIndex) {
//        array[lastIndex] = array[lastIndex - 1]
//        lastIndex--
//    }
//
//    array[newValueIndex] = value
//}
//
//fun main() {
//    val array: ArrayList<Int> = arrayListOf<Int>(0, 1, 2, 3, 4, 5, 6)
//
//    printArrayHorizontally(array)
//    addByIndex(array, array.size, 13)
//    printArrayHorizontally(array)
//}

fun printArrayHorizontally(array: ArrayList<Int>) {
    var index = 0
    while (index < array.size) {
        print("${array[index]} ")
        index++
    }
    println()
}

//Напмсать функцию, которая удаляет элемент по индексу
// fun removeByIndex(array: ArrayList<Int>, index: Int)
// Например массив [0,1,2,3,4,5,6] удалить по индексу 2. [0,1,3,4,5,6]

fun removeByIndex(array: ArrayList<Int>, indexRemoving: Int) {
    var index = indexRemoving

    while (index < array.size - 1) {
        array[index] = array[index + 1]
        index++
    }
    array.removeAt(index)
}

fun main() {
    val array: ArrayList<Int> = arrayListOf<Int>(0, 1, 2, 3, 4, 5, 6)

    printArrayHorizontally(array)
    removeByIndex(array, 2)
    printArrayHorizontally(array)
}
