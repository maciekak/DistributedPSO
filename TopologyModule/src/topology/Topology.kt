package topology

import particle.Particle

interface Topology {
    fun setNeighbours(particles: List<Particle>)
}