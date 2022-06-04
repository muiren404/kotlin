package com.example.myapplication

/**
 * Напиши программу, которая определяет чётное-ли число и выводит результат на экран.
 */
//fun <имя функции>(<список аргументов через запятую>): <Тип Возвращаемого значения>

//+ - * / %

// Объявление переменной
// val <имя>: <тип> = <значение>
// val - неизменяемая переменная, var - изменяемая

/* == > >= < <= !=
 if (условие) {
    Исполняемый код
 } else if (условие) {
    Исполняемый код
 } else {
    Исполняемый код
 }
*/

fun executeLeraProgram1(num: Int) {
    if (num % 2 == 0)
        println("$num чётное")
    else
        println("$num нечётное")
}

/**
 * Напиши программу, которая определяет положительное-ли число и выводит результат на экран.
 */

fun executeLeraProgram2(num: Double) {
    if (num > 0)
        println("$num is positive")
    else if (num < 0)
        println("$num is negative")
    else
        println("$num is zero")
}

/**
 * Напиши программу, которая принимает делимое и делитель
 * определяет целую часть и остаток от деления
 * выведи результат на экран в формате "Целое: x, Остаток: y"
 */
//Доработать
fun executeLeraProgram3(num: Int, div: Int) {
    if (div == 0)
        println("Нельзя делить на 0")
    else {
        val celoe: Int = num / div
        val ostatok: Int = num % div
        println("Целое: $celoe, Остаток: $ostatok")
    }
}

// when {
// 	num > 0 -> {}
// 	num < 0 -> {}
// 	else -> {}
// } && - и || - или

/**
 * Напиши программу, которая выводит на экран: "FizzBuzz" если число кратно 3 и кратно 5
 * "Fizz" если кратно 3
 * "Buzz" если кратно 5
 * Иначе выводить "zz"
 * Используй оператор when
 */

fun executeLeraProgram4 (num: Int) {
    when {
        (num % 5 == 0) && (num % 3 == 0) -> println ("FizzBuzz")
        (num % 3 == 0) -> println ("Fizz")
        (num % 5 == 0) -> println ("Buzz")
        else ->  println ("zz")
    }
}

fun main() {
    executeLeraProgram1(1001)
    executeLeraProgram2(0.5)
    executeLeraProgram3(42, 35)

    executeLeraProgram4(15)
    executeLeraProgram4(9)
    executeLeraProgram4(25)
    executeLeraProgram4(11)
}