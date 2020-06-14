package com.willhughes.rest

abstract class RouteHandler {
    val routes = HashMap<String, (Any, Any) -> Unit>()

    abstract fun initMap();

    init {
        initMap();
    }
}