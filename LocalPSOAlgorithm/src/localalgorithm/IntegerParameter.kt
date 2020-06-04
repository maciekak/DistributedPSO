package mpsoalgorithm

import algorithm.Parameter

class IntegerParameter(override val name: String, private val value: Int) : Parameter {
    override fun getParameterValue(): Any {
        return value
    }
}