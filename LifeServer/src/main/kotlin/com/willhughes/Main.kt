package com.willhughes

import com.willhughes.life.*
import com.willhughes.rest.Router
import com.willhughes.util.ConfigReader
import com.willhughes.util.GaussianRandom
import kotlinx.serialization.builtins.SetSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlin.math.roundToInt
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
        val person = Person(Random.nextInt(60) + 20, if (Random.nextBoolean()) Gender.MALE else Gender.FEMALE, null)
        person.randomSalary()
        WorldState.lives.add(person)
    }
    LifeRunner
//    val person = Person(Random.nextInt(60) + 20, if (Random.nextBoolean()) Gender.MALE else Gender.FEMALE, null)
//    val intVal:Int = (GaussianRandom.nextRandom() * 50000 * (1..4).random()).roundToInt()
//    println("intVal $intVal")
//    val json = Json(JsonConfiguration.Stable)
//    println("person salary is ${person.salary}")
//    val str = json.stringify(Person.serializer(), person)
//    println("str is $str")

}



