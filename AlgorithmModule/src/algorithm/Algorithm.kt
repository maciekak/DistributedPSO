package algorithm

import function.Function

interface Algorithm {
    val parameters: List<Parameter>
    val testFunction: Function

    fun getParameter(name: String): Parameter
    fun run(): Double
}