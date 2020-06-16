package com.willhughes.life

import com.willhughes.rest.RouteHandler
import kotlinx.serialization.builtins.SetSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class LifeRouteHandler : RouteHandler() {

    override fun initMap() {
        val json = Json(JsonConfiguration.Stable)
        routes.put("/generation/list", { req, res: dynamic ->
            val respStr = json.stringify(SetSerializer(Person.serializer()), WorldState.lives)
            res.end(respStr)
        })
        routes.put("/generation/next", { req, res: dynamic ->
            LifeRunner.runLife()
            val respStr = json.stringify(SetSerializer(Person.serializer()), WorldState.lives)
            res.end(respStr)
        })
    }
}