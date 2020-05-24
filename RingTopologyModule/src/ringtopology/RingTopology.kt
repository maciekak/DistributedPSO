package ringtopology

import topology.Topology
import particle.Particle

class RingTopology : Topology {
    override fun setNeighbours(particles: List<Particle>) {
        for (i in 0..particles.size-1){
            if(i == 0){
                particles[i].particles.add(particles.last())
                particles[i].particles.add(particles[1])
                continue
            }
            if(i == particles.size-1){
                particles[i].particles.add(particles[i-1])
                particles[i].particles.add(particles[0])
                continue
            }

            particles[i].particles.add(particles[i-1])
            particles[i].particles.add(particles[i+1])
        }
    }
}