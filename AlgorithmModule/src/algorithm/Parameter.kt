package algorithm

interface Parameter {
    val name: String
    fun getParameterValue(): Any
}