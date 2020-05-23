package mpsoalgorithm

import algorithm.Algorithm
import algorithm.Parameter
import function.Function

class MPSO(override val testFunction: Function, override val parameters: List<Parameter>) : Algorithm {
    override fun getParameter(name: String): Parameter {
        return parameters.first { p -> p.name == name }
    }

    override fun run(): Double {
        //TODO: nie wiem jak tutaj
        return testFunction.getResult(doubleArrayOf(1.0, 2.0))
    }

}