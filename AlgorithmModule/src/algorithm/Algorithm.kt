package algorithm

import function.Function

interface Algorithm {
    val parameters: MutableMap<String, Any?>
    val testFunction: Function

    fun getParameter(name: String): Any?
    fun run(): Pair<DoubleArray, Double>
}