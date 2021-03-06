package particle

import function.Function
import kotlin.math.abs
import kotlin.random.Random

class Particle (val testFunction: Function, val id: Int, val omega: Double = 0.9, val lampdaP: Double = 0.2, val lampdaG: Double = 0.3){
    val particles: MutableList<Particle> = mutableListOf()
    var currPosition: DoubleArray
    var bestPersonalPosition: DoubleArray
    var bestPosition: DoubleArray
    private var velocity: DoubleArray
    var currentValue = 0.0
    var bestPersonalValue = 0.0
    var bestValue = 0.0
    init {
        val size = testFunction.boundaryDown.size
        currPosition = DoubleArray(size)
        bestPersonalPosition = DoubleArray(size)
        velocity = DoubleArray(size)
        bestPosition = DoubleArray(size)
        for(i in 0..size-1){
            currPosition[i] = Random.nextDouble(testFunction.boundaryDown[i], testFunction.boundaryUp[i])
            bestPersonalPosition[i] = currPosition[i]
            bestPosition[i]  = currPosition[i]
            val diff = abs(testFunction.boundaryDown[i] - testFunction.boundaryUp[i])
            velocity[i]  = Random.nextDouble(-diff, diff)
        }
        currentValue = testFunction.getResult(currPosition)
        bestPersonalValue = currentValue
        bestValue = currentValue
    }


    private fun updateVelocity(){
        for(i in 0..currPosition.size-1) {
            val rp = Random.nextDouble(0.0, 1.0)
            val rg = Random.nextDouble(0.0, 1.0)
            velocity[i] = omega*velocity[i] + lampdaP*rp*(bestPersonalPosition[i] - currPosition[i]) + lampdaG*rg*(bestPosition[i] - currPosition[i])
        }

    }

    private fun updatePosition() {
        for(i in 0..currPosition.size-1) {
            val newPosition = currPosition[i] + velocity[i]
            if (newPosition > testFunction.boundaryDown[i] && newPosition < testFunction.boundaryUp[i])
                currPosition[i] = newPosition
            else if (newPosition < testFunction.boundaryDown[i])
                currPosition[i] = testFunction.boundaryDown[i]
            else if (newPosition > testFunction.boundaryUp[i])
                currPosition[i] = testFunction.boundaryUp[i]
        }
    }

    fun iterate() {
        val bestNeighbour = particles.minBy { p -> testFunction.getResult(p.bestPosition) }
        if(bestNeighbour != null && testFunction.getResult(bestNeighbour.bestPosition) < testFunction.getResult(bestPosition) ){
            bestPosition = bestNeighbour.bestPosition.copyOf()
            bestValue = testFunction.getResult(bestNeighbour.bestPosition)
        }

        updateVelocity()
        updatePosition()
        currentValue = testFunction.getResult(currPosition)

        if(testFunction.getResult(currPosition) < testFunction.getResult(bestPersonalPosition)){
            bestPersonalPosition = currPosition.copyOf()
            bestPersonalValue = testFunction.getResult(currPosition)
            if(testFunction.getResult(currPosition) < testFunction.getResult(bestPosition)){
                bestPosition = currPosition.copyOf()
                bestValue = testFunction.getResult(currPosition)
            }
        }
    }

    fun getValue(): Double {
        return testFunction.getResult(currPosition)
    }
}