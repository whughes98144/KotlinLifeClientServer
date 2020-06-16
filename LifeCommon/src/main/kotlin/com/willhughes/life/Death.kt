package com.willhughes.life

import com.willhughes.util.Logger
import kotlin.math.min

object Death {
    //        https://www.cdc.gov/nchs/nvss/mortality/gmwk23r.htm
//        1-4 5-14 15-24 25-34 35-44 45-54 55-64 65-74 75-84 85 years
//        per 100,000
    val rate = listOf(700.0, 31.5, 17.0, 81.5, 103.6, 201.6, 433.2, 940.9, 2255.0, 5463.1, 14593.3)
    fun comes(age: Int): Boolean {
        var bin = (age + 5) / 10 + 1
        bin = min(bin, rate.lastIndex)
        if (age < 2) bin = 0
        val randomChance = (0..100000).random()
        Logger.log("Death comes: age: $age, bin: ${bin}, rate: ${rate.get(bin)}, randomChance: $randomChance", 1)
        return randomChance < rate.get(bin)
    }
}
