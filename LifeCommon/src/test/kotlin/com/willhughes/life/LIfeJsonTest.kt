package com.willhughes.life

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class LIfeJsonTest {

    @Test
    fun testThis() {
        val json = Json(JsonConfiguration.Stable)
        val person = Person(40, Gender.FEMALE, "hughes")
        val spouse = Person(50, Gender.MALE, "smith")
        spouse.gotMarried(person)
        person.newChild()
        person.newChild()
        assertEquals(person.spouse?.children?.size, 2)
        assertEquals(person.childrenIds?.size, 2)
        assertEquals(40, 40)
        assertNotNull(person.spouse)
        assertNotNull(person.spouseId)
        val stringify = json.stringify(Person.serializer(), person)
        assertNotNull(stringify)
        println("Json Str: $stringify")

        val personFromJson:Person = json.parse(Person.serializer(), stringify)
        assertEquals( personFromJson.age, 40 )
        assertNull(personFromJson.spouse)
        println("spouseid: ${personFromJson.spouseId}")
        assertNotNull(personFromJson.spouseId)
        assertEquals(personFromJson.childrenIds?.size, 2)
    }
}