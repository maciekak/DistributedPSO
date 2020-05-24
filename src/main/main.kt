package main

import function.Function
import mpsoalgorithm.IntegerParameter
import mpsoalgorithm.MPSO
import gridtopology.GridTopology
import ringtopology.RingTopology
fun main(args: Array<String>) {
    val testFunction: (DoubleArray) -> Double = { x -> (x[0] - 1) *(x[0] - 1) + (x[1] + 2)*(x[1] + 2) }
    val parameters: MutableMap<String, Any?> = mutableMapOf<String, Any?>()
    parameters.put("topology", RingTopology())
    //parameters.put("particleNumber", 50)
    val mpso = MPSO(Function(doubleArrayOf(10.0, 10.0), doubleArrayOf(-5.0, -5.0), testFunction), parameters)
    val result = mpso.run()
    println("Optimum: " + result.first.contentToString() + " Optimum value: " + result.second)
}