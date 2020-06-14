package com.willhughes

import org.w3c.xhr.XMLHttpRequest
import kotlin.browser.document

external fun jq(id: String): dynamic
external class Person
fun main() {
    document.write("calling getJson, world!")

    val url = "http://localhost:8010/generation/list"
    val xmlHttp = XMLHttpRequest()
    xmlHttp.open("GET", url)
    xmlHttp.onload = {
        if (xmlHttp.readyState == 4.toShort() && xmlHttp.status == 200.toShort()) {
//            callback.invoke(xmlHttp.responseText)
            val text = xmlHttp.responseText
            document.write("Got response $text")
            val parse = JSON.parse<HashSet<Person>>(text)
            document.write("person list is ${parse}")
            for (person: Person in parse) {
                document.write("<p>person is ${person}</p>")

            }
        }
    }

    xmlHttp.send()
}