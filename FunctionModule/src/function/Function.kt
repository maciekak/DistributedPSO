package function

class Function(val boundaryUp: DoubleArray, val boundaryDown: DoubleArray, val testFunction: (DoubleArray) -> Double) {

    fun getResult(x: DoubleArray) : Double {
        return testFunction(x)
    }
}