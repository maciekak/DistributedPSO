package gridtopology

import particle.Particle
import topology.Topology
import kotlin.math.ceil
import kotlin.math.sqrt

class GridTopology : Topology {
    override fun setNeighbours(particles: List<Particle>) {
        val size = ceil(sqrt(particles.size.toDouble())).toInt()
        for(i in 0..particles.size-1){
            //left
            if(i % size != 0){
                particles[i].particles.add(particles[i-1])
            }
            //up
            if(i >= size){
                particles[i].particles.add(particles[i-size])
            }
            //right
            if((i+1) % size != 0 && i+1 < particles.size){
                particles[i].particles.add(particles[i+1])
            }
            //down
            if(i+size < particles.size){
                particles[i].particles.add(particles[i+size])
            }
        }
    }
}