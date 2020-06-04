package mpsoalgorithm

import algorithm.Algorithm
import algorithm.Parameter
import function.Function
import kotlinx.coroutines.*
import swarm.Swarm
import kotlin.collections.*

class MPSO(override val testFunction: Function, override val parameters: MutableMap<String, Any?>) : Algorithm {
    val swarms: MutableList<Swarm> = mutableListOf()
    init{
        parameters.putIfAbsent("swarmNumber", 20)
        parameters.putIfAbsent("particleNumber", 200)
        parameters.putIfAbsent("iterationNumber", 800)
        val swarmNumber = parameters.get("swarmNumber") as Int
        for (i in 1..swarmNumber){
            swarms.add(Swarm(testFunction, parameters))
        }
    }

    override fun getParameter(name: String): Any? {
        return parameters.get(name)
    }

    override fun run(): Pair<DoubleArray, Double> {
        val iterationNumber = parameters.get("iterationNumber") as Int
        for (i in 1..iterationNumber){
            runBlocking {
                swarms.forEach {
                    launch(Dispatchers.IO) {
                        it.iterate()
                    }
                }
                var result = getOptimum()
                println("Iteration: " + i + "; Optimum value: " + result.second)
            }
        }
        return getOptimum()
    }

    private fun getOptimum(): Pair<DoubleArray, Double> {
        var optimum = swarms[0].getOptimum()
        var optimumValue: Double = testFunction.getResult(optimum)
        for (swarm in swarms){
            if (optimumValue > testFunction.getResult(swarm.getOptimum())){
                optimum = swarm.getOptimum()
                optimumValue = testFunction.getResult(optimum)
            }
        }
        return Pair(optimum, optimumValue)
    }

}