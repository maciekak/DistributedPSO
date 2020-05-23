package main

import function.Function
import mpsoalgorithm.IntegerParameter
import mpsoalgorithm.MPSO

fun main(args: Array<String>) {
    val testFunction: (DoubleArray) -> Double = { x -> (x[0] - 1) *(x[0] - 1) + (x[1] + 2)*(x[1] + 2) }
    val parameters = listOf(IntegerParameter("first", 3))
    val mpso = MPSO(Function(doubleArrayOf(1.0, 1.0), doubleArrayOf(-1.0, -1.0), testFunction), parameters)
    println("Result: " + mpso.run())
}