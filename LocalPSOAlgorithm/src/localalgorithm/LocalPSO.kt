package mpsoalgorithm

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
        parameters.putIfAbsent("iterationNumber", 400)
        swarms.add(Swarm(testFunction, parameters))
    }

    override fun getParameter(name: String): Any? {
        return parameters.get(name)
    }

    override fun run(): Pair<DoubleArray, Double> {
        val iterationNumber = parameters.get("iterationNumber") as Int
        for (i in 1..iterationNumber){
            launch(Dispatchers.IO) {
                swarm.iterate()
            }
        }
        return getOptimum()
    }


    private fun getOptimum(): Pair<DoubleArray, Double> {
        var optimum = swarm.getOptimum()
        var optimumValue: Double = testFunction.getResult(optimum)
        return Pair(optimum, optimumValue)
    }

}