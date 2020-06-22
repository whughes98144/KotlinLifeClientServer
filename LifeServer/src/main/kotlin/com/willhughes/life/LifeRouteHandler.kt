package com.willhughes.life

import com.willhughes.rest.RouteHandler
import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration

class LifeRouteHandler : RouteHandler() {

    override fun initMap() {
        val json = Json(JsonConfiguration.Stable)
        routes.put("/generation/list", { req, res: dynamic ->
            val respStr = json.stringify(Person.serializer().list, WorldState.lives)
            res.end(respStr)
        })
        routes.put("/generation/next", { req, res: dynamic ->
            LifeRunner.runLife()
            val respStr = json.stringify(Person.serializer().list, WorldState.lives)
            res.end(respStr)
        })
        routes.put("/family/list", { req, res: dynamic ->
            val respStr = json.stringify(Family.serializer(), Family.getRoot(WorldState.lives))
            res.end(respStr)
        })
    }
}