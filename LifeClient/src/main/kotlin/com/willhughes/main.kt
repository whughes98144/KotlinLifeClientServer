package com.willhughes

import com.willhughes.web.d3Graphing
import com.willhughes.web.fetch
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import org.w3c.dom.Element
import org.w3c.dom.events.Event
import kotlin.browser.document

external fun jq(id: String): dynamic
fun main() {
    fetchFamily()
//    drawTable()
    val button = document.getElementById("nextGenButton")
    button?.addEventListener("click", fun(event: Event) {
        fetchNextGen()
        fetchFamily()
    })
}

fun drawTable() {
    js("""
    var sampleData ={};	/* Sample random data. */
	["HI", "AK", "FL", "SC", "GA", "AL", "NC", "TN", "RI", "CT", "MA",
	"ME", "NH", "VT", "NY", "NJ", "PA", "DE", "MD", "WV", "KY", "OH",
	"MI", "WY", "MT", "ID", "WA", "DC", "TX", "CA", "AZ", "NV", "UT",
	"CO", "NM", "OR", "ND", "SD", "NE", "IA", "MS", "IN", "IL", "MN",
	"WI", "MO", "AR", "OK", "KS", "LS", "VA"]
		.forEach(function(d){
			var low=Math.round(100*Math.random()),
				mid=Math.round(100*Math.random()),
				high=Math.round(100*Math.random());
			sampleData[d]={low:d3.min([low,mid,high]), high:d3.max([low,mid,high]),
					avg:Math.round((low+mid+high)/3), color:d3.interpolate("#0295D5", "#F88909")(low/100)};
		});
    """)

//    drawTable(document.getElementById("table") as HTMLDivElement, js("sampleData"))
    js("uStates.draw(\"#statesvg\", sampleData, _.tooltipHtmlJs);")

    val d3_kt: dynamic = js("d3.select(self.frameElement)")
    d3_kt.style("height", "600px")
}
fun fetchFamily() {
    val json = Json(JsonConfiguration.Stable)
    val url = "http://localhost:8010/family/list"
    fetch(url, { response: String ->
//        val family = json.parse(Family.serializer(), response)
        d3Graphing().circleGraph(JSON.parse(response))

//        family.children.forEach {
//            logConsole("personChild is ${it.toString()}")
//        }
    })

}
fun fetchNextGen() {
    val json = Json(JsonConfiguration.Stable)
    val url = "http://localhost:8010/generation/next"
    fetch(url, { response: String ->
//        val personSet:Set<Person> = json.parse(SetSerializer(Person.serializer()), response)
//        personSet.forEach {
//            logConsole("person is ${it.toString()}")
//        }
    })
//    val xmlHttp = XMLHttpRequest()
//    xmlHttp.open("GET", url)
//    xmlHttp.onload = {
//        if (xmlHttp.readyState == 4.toShort() && xmlHttp.status == 200.toShort()) {
//            callback.invoke(xmlHttp.responseText)
//            val text = xmlHttp.responseText
//            logConsole("Got response $text")
//        }
//    }
//    xmlHttp.send()
}

fun logConsole(message: String) {
    val consoleDiv:Element? = document.getElementById("outputConsole")
    consoleDiv?.innerHTML = "<p>$message</p>" + consoleDiv?.innerHTML ;
}