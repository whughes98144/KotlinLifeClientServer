package com.willhughes.life

import kotlin.test.Test
import kotlin.test.assertEquals

class LIfeJsonTest {

    @Test
    fun testThis() {
//        val json = Json(JsonConfiguration.Stable)
        val person = Person(40, Gender.FEMALE, "hughes")
        assertEquals(40, 40)
//        val stringify = json.stringify(Person.serializer(), person)
//        assertNotNull(stringify)
//        val parseJson:Person = json.parse(Person.serializer(), stringify)
//        assertEquals( parseJson.age, 40 )
    }
}