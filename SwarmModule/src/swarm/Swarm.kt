package swarm

import function.Function
import particle.Particle
import topology.Topology
import kotlinx.coroutines.*
import java.util.concurrent.*
class Swarm (var function: Function, var parameters: MutableMap<String, Any?>){
    val particles: MutableList<Particle> = mutableListOf()
    val topology: Topology = parameters.get("topology") as Topology
    init{
        var particleNumber = parameters.get("particleNumber") as Int
        for(i in 1..particleNumber){
            particles.add(Particle(function))
        }
        updateNeighbours()
    }

    fun iterate() {
        val lstOfReturnData = ConcurrentLinkedQueue<Int>()
        runBlocking {
            particles.forEach {
                launch(Dispatchers.IO) {
                    it.iterate()
                }
            }
        }
    }

    fun getOptimum(): DoubleArray {
        var optimum: DoubleArray = particles[0].bestPosition
        var optimumValue: Double = function.getResult(optimum)
        for (particle in particles){
            if (optimumValue > function.getResult(particle.bestPosition)){
                optimum = particle.bestPosition
                optimumValue = function.getResult(optimum)
            }
        }
        return optimum
    }

    fun updateNeighbours(){
        topology.setNeighbours(particles)
    }

    fun deleteParticle(index: Int){
        particles.removeAt(index)
    }

    fun addParticle(particle: Particle){
        particles.add(particle)
    }

}