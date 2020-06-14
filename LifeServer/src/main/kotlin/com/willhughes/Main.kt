package com.willhughes

import com.willhughes.life.*
import com.willhughes.rest.Router
import com.willhughes.util.ConfigReader
import kotlin.random.Random


/**
 * Example main function. Started at script body.
 * At first time you have to run `mvn package`. 
 * Open example index.html in browser once you compile it.
 */

//external val __dirname: dynamic
//external fun require(module: String): dynamic

fun main(args: Array<String>) {
//    val path = require("path")
//    val staticWebContentPath: String = path.join(__dirname, "../src/main/resources/rulz.json") as String
//    println("path is $staticWebContentPath")
    Router(LifeRouteHandler())
    for (i in 1..ConfigReader.jsonObj.numOfLives) {
        WorldState.lives.add(Person(Random.nextInt(60)+20, if (Random.nextBoolean()) Gender.MALE else Gender.FEMALE, null))
    }
//    LifeRunner
}



