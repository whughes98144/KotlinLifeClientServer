package com.willhughes.life

import com.willhughes.rest.RouteHandler
import com.willhughes.util.DeepJson

class LifeRouteHandler : RouteHandler () {

    override fun initMap() {
        routes.put("/generation/list", {
                req, res: dynamic ->
            res.end(DeepJson().toJson(WorldState.lives))
        })
        routes.put("/generation/next", {
            req, res: dynamic ->
            LifeRunner.runLife()
            res.end(DeepJson().toJson(WorldState.lives))
        })
    }
}