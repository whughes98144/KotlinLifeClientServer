package com.willhughes.web

import org.w3c.xhr.XMLHttpRequest

fun fetch(url:String, callback: ((String) -> Unit)) {
    val xmlHttp = XMLHttpRequest()
    xmlHttp.open("GET", url)
    xmlHttp.onload = {
        if (xmlHttp.readyState == 4.toShort() && xmlHttp.status == 200.toShort()) {
            callback(xmlHttp.responseText)
        }
    }
    xmlHttp.send()
}
