package com.willhughes.life

import com.willhughes.util.ConfigReader
import com.willhughes.util.Logger

/**
 * Rule based system with (yearly) iterations, advancing age and random return variables
 * http transport to UI that displays 'family units' with color/symbol to quickly differentiate higher-order metrics
 * along with ability to zoom in on specifics.  Thus can watch change over time.  Ability from UI to change speed
 * of iterations
 */
external fun setInterval(handler: dynamic, time: Int = definedExternally, vararg arguments: Any?): Int
external fun clearInterval(time: Int = definedExternally): Int

object LifeRunner {

    private const val DELAY = 1000;
    var currentGeneration: Int = 0
    var timerId: Int = 0

    init {
//        timerId = setInterval(runTimer(), 1)
        Logger.noLogging = true
    }

    private fun runTimer(): () -> Unit {
        return {
            runLife()
            if (WorldState.lives.size == 0) {
                clearInterval(timerId)
            } else if (currentGeneration > ConfigReader.jsonObj.initialRun as Int) {
                Logger.noLogging = false
                clearInterval(timerId)
                timerId = setInterval(runTimer(), DELAY)
            }
        }
    }

    fun runLife() {
        currentGeneration++
        WorldState.anotherYear()
        WorldState.lives.forEach { life ->
            life.anotherYear()
            Logger.log(life.toString())
        }
        Logger.log(getStats(), 4)
    }

    fun getStats(): String {
        var str = "\n\nLife.... year $WorldState\n------------------------------------\n"
        str += "All people, living and dead ${WorldState.lives.size}\n"
        var alive = 0;
        var avgDeathAgeSum = 0;
        var married = 0
        var hasKids = 0;
        var avgSalarySum = 0
        for (life in WorldState.lives) {
            if (life.alive) {
                alive++
                avgSalarySum += life.salary
                if (life.spouse != null) {
                    married++
                }
                if (life.children.size > 0) {
                    hasKids++
                }
            } else {
                avgDeathAgeSum += life.age
            }
        }
        var salaryAverage: Int = (avgSalarySum / alive).toInt()
        str += "All living $alive\n"
        str += "Avg death age : ${avgDeathAgeSum / (WorldState.lives.size - alive)}\n"
        str += "Married : $married, hasKids: $hasKids\n"
        str += "Salary Avg : ${salaryAverage}\n"

        return str
    }

}
