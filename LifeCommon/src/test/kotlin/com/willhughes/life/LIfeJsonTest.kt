package com.willhughes.life

import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlin.test.*

class LIfeJsonTest {

    @Test
    fun testBasicPerson() {
        val json = Json(JsonConfiguration.Stable)
        val person = Person(40, Gender.FEMALE, "hughes")
        person.newChild()
        assertEquals(40, 40)
        assertNull(person.spouse)
        assertNull(person.spouseId)
        val stringify = json.stringify(Person.serializer(), person)
        assertNotNull(stringify)
        println("Json Str: $stringify")

        val personFromJson:Person = json.parse(Person.serializer(), stringify)
        assertEquals( personFromJson.age, 40 )
        assertNull(personFromJson.spouse)
        assertNull(personFromJson.spouseId)
        assertEquals(personFromJson.childrenIds?.size, 1)
    }

    @Test
    fun testPersonIds() {
        val json = Json(JsonConfiguration.Stable)
        val person = Person(40, Gender.FEMALE, "hughes")
        val spouse = Person(50, Gender.MALE, "smith")
        spouse.gotMarried(person)
        person.newChild()
        person.newChild()
        val childId = person.children[0].id
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
        assertEquals (personFromJson.childrenIds?.contains(childId), true)
    }
}