package com.willhughes.util

external val __dirname: dynamic
external fun require(module: String): dynamic
object ConfigReader {
    val path = require("path")
    val staticWebContentPath: String = path.join(__dirname, "../src/main/resources/rulz.json") as String
    val jsonObj = js("require('../../../../../LifeServer/src/main/resources/rulz.json')")
//    val jsonObj: dynamic = {  }

    init {
        println("path is ($staticWebContentPath")
        println("JSON log level is ${jsonObj.logLevel}")
        Logger.setCutoff(jsonObj.logLevel)
    }
}