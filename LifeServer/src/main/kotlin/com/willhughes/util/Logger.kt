package com.willhughes.util

object Logger {

    const val DEFAULT_CUTOFF_VAL = 3
    var noLogging: Boolean = false
    var cutoffLogLevel = DEFAULT_CUTOFF_VAL

    fun log(message: String, logLevel: Int) {
        if  (logLevel >= cutoffLogLevel && !noLogging) {
            println(message)
        }
    }
    fun log(message: String) {
        log(message, 2)
    }
    fun setCutoff(level: Int) {
        cutoffLogLevel = level
    }
}