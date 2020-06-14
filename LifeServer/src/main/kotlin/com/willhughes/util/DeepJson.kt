package com.willhughes.util

import com.willhughes.life.Person

class DeepJson {

    val seen = HashSet<Person>()
    fun toJson(obj: Any): String {
        return JSON.stringify(obj, { key, value: dynamic ->
            if (value is Person) {
                if (!seen.contains(value)) {
                    seen.add(value);
                    value
                } else {
                }
            } else {
                value
            }
        }
        )
    }
}