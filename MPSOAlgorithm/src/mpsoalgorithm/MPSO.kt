package mpsoalgorithm

import algorithm.Algorithm
import algorithm.Parameter
import function.Function
import kotlinx.coroutines.*
import swarm.Swarm
import java.util.concurrent.ConcurrentLinkedQueue
import kotlin.collections.*
class MPSO(override val testFunction: Function, override val parameters: MutableMap<String, Any?>) : Algorithm {
    val swarms: MutableList<Swarm> = mutableListOf()
    init{
        val swarmNumber = 2
        parameters.putIfAbsent("particleNumber", 5)
        for (i in 1..swarmNumber){
            swarms.add(Swarm(testFunction, parameters))
        }
    }

    override fun getParameter(name: String): Any? {
        return parameters.get(name)
    }

    override fun run(): Pair<DoubleArray, Double> {
        val iterationNumber = 4
        for (i in 1..iterationNumber){
            val lstOfReturnData = ConcurrentLinkedQueue<Int>()
            runBlocking {
                swarms.forEach {
                    launch(Dispatchers.IO) {
                        it.iterate()
                    }
                }
            }
        }
        return getOptimum()
        //return testFunction.getResult(doubleArrayOf(1.0, 2.0))
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
/*
Algorithm  - input: Function, optional: parameters(List(Swarm), Topology, #ofParticles, iterations, ω, φp, φg),
	getParameter
run{
//Create Swarms if not given
for i in range(iterations)
	Swarm.iterate()	//assuming one swarm
return max of Swarms
}

 */