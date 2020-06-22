package com.willhughes.rest

external val __dirname: dynamic
external fun require(module: String): dynamic
class Router(val routeHandler: RouteHandler) {
    val PORT: Int = 8020
    init {
        var express = require("express");
        var app = express();
        var path = require("path")
        var cors = require("cors")

        app.use(cors())
        val staticWebContentPath = path.join(__dirname, "../../../../../out/production/static") as String
        println("webpath is $staticWebContentPath")
        app.use(express.static(staticWebContentPath))
        for ( route: String in routeHandler.routes.keys) {
            println("adding route for $route")
            app.get(route, routeHandler.routes.get(route))
        }
        var server = app.listen(PORT, {
            println("Example app listening ")
        })
    }
}