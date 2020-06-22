package com.willhughes

import com.willhughes.web.D3Graphing
import com.willhughes.web.fetch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.w3c.dom.Element
import org.w3c.dom.events.Event
import kotlin.browser.document

external fun jq(id: String): dynamic
fun main() {
    fetchFamily()
    val button = document.getElementById("nextGenButton")
    button?.addEventListener("click", fun(event: Event) {
        fetchNextGen()
        fetchFamily()
    })
}

fun fetchFamily() {
    val json = Json(JsonConfiguration.Stable)
    val url = "http://localhost:8010/family/list"
    fetch(url, { response: String ->
        D3Graphing().circleGraph(JSON.parse(response))

    })

}
fun fetchNextGen() {
    val url = "http://localhost:8010/generation/next"
    fetch(url, { response: String ->
    })
}

fun logConsole(message: String) {
    val consoleDiv:Element? = document.getElementById("outputConsole")
    consoleDiv?.innerHTML = "<p>$message</p>" + consoleDiv?.innerHTML ;
}