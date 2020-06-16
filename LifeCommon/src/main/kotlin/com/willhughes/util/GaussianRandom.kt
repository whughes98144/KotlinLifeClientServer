package com.willhughes.util

import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.log
import kotlin.math.sqrt
import kotlin.random.Random

object GaussianRandom {

    fun nextRandom(): Double {
        var u = 0.0
        var v = 0.0
        while (u == 0.0) u = Random.nextDouble(); //Converting [0,1) to (0,1)
        while (v == 0.0) v = Random.nextDouble();
        var num = sqrt(-2.0 * log(u, 10.0)) * cos(2.0 * PI * v)
        num = num / 10.0 + 0.5 // Translate to 0 -> 1
        return if (num > 1 || num < 0) nextRandom() else num // resample between 0 and 1

    }
}