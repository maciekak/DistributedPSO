package localalgorithm

import algorithm.Algorithm
import algorithm.Parameter
import function.Function
import kotlinx.coroutines.*
import swarm.Swarm
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.collections.*

class LocalPSO(override val testFunction: Function, override val parameters: MutableMap<String, Any?>) : Algorithm {
    val swarm: Swarm
    init{
        parameters.putIfAbsent("particleNumber", 5)
        swarm = Swarm(testFunction, parameters)
    }

    override fun getParameter(name: String): Any? {
        return parameters.get(name)
    }

    override fun run(): Pair<DoubleArray, Double> {
        val iterationNumber = parameters.get("iterationNumber") as Int
        for (i in 1..iterationNumber){
            swarm.iterate()
            var result = getOptimum()
            println(result.second)
        }
        return getOptimum()
    }


    private fun getOptimum(): Pair<DoubleArray, Double> {
        var optimum = swarm.getOptimum()
        var optimumValue: Double = testFunction.getResult(optimum)
        return Pair(optimum, optimumValue)
    }

}