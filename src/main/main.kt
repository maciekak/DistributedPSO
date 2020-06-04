package main

import function.Function
import gridtopology.GridTopology
import mpsoalgorithm.MPSO
import kotlin.math.cos

fun main() {
    val parameters: MutableMap<String, Any?> = mutableMapOf()
    parameters.put("topology", GridTopology())
    parameters.put("particleNumber", 30)
    parameters.put("iterationNumber", 400)
    parameters.put("swarmNumber", 10)
    val algorithm = MPSO(Function(doubleArrayOf(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                            doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), ::testFunctionCosff), parameters)
    val result = algorithm.run()
    println("Optimum: " + result.first.contentToString() + " Optimum value: " + result.second)
}

fun testFunctionCosff(x: DoubleArray): Double{
    val scale = 100
    val k = 10
    var m = (1..x.size).map { it / x.size }
    var result = 0.0
    for(i in 0..x.size-1){
        result += if(x[i] <= m[i]){
            cos(k*(x[i]-m[i]))*(1.0-(m[i]-x[i]))
        } else{
            cos(k*(x[i]-m[i]))*(1.0-(x[i]-m[i]))
        }
    }

    return result/x.size*scale
}