package main

import function.Function
import mpsoalgorithm.MPSO
import ringtopology.RingTopology
import kotlin.math.cos

fun main(args: Array<String>) {
    val testFunction: (DoubleArray) -> Double = { x -> (x[0] - 1) *(x[0] - 1) + (x[1] + 2)*(x[1] + 2) }
    val parameters: MutableMap<String, Any?> = mutableMapOf<String, Any?>()
    parameters.put("topology", RingTopology())
    //parameters.put("particleNumber", 50)
    val mpso = MPSO(Function(doubleArrayOf(1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0),
                            doubleArrayOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0), ::testFunctionCosff), parameters)
    val result = mpso.run()
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