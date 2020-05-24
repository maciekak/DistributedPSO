package main

import function.Function
import mpsoalgorithm.IntegerParameter
import mpsoalgorithm.MPSO
import gridtopology.GridTopology
fun main(args: Array<String>) {
    val testFunction: (DoubleArray) -> Double = { x -> (x[0] - 1) *(x[0] - 1) + (x[1] + 2)*(x[1] + 2) }
    //al parameters = listOf(IntegerParameter("first", 3))
    val parameters: MutableMap<String, Any?> = mutableMapOf<String, Any?>()
    parameters.put("topology", GridTopology())
    val mpso = MPSO(Function(doubleArrayOf(1.0, 1.0), doubleArrayOf(-1.0, -1.0), testFunction), parameters)
    val result = mpso.run()
    println("Result: " + result.first.contentToString() + result.second)
}