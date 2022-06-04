package ru.itcodegroup.myapplication

//написать программу с помощью цикла, которая выведет первые 12 чисел фибоначчи
fun solveFibonachi() {
    var index = 0
    var first = 0
    println(first)
    var second = 1
    println(second)
    while (index < 12) {
        val result = first + second
        first = second
        second = result
        println(result)
        index++
    }
}

//написать программу через рекурсию


fun main(){
    solveFibonachi()
}