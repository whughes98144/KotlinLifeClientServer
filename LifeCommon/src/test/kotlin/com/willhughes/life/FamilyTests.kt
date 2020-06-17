package com.willhughes.life

import kotlinx.serialization.builtins.list
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlin.random.Random
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class FamilyTests {

    @Test
    fun testInitWorldState() {
        initWorldState()
        assertTrue { WorldState.lives.size > 20 }
    }

    @Test
    fun testFamily() {
        initWorldState()
        val root = Family.getRoot(WorldState.lives)
        assertTrue { root.children.size > 2 }
        val json = Json(JsonConfiguration.Stable)
        val stringify = json.stringify(Family.serializer(), root)
        println("we got $stringify")
    }

    @Test
    fun testFamily2() {
        val root = Family.getRoot(initWorldFromData())
        assertTrue { root.children.size > 2 }
        val json = Json(JsonConfiguration.Stable)
        val stringify = json.stringify(Family.serializer(), root)
        println("2 got $stringify")
    }

    @Test
    fun testFamily3() {
        var root = Family.getRoot(initWorldFromData())
        var size = root.children.size
        assertTrue { size > 2 }
        root = Family.getRoot(initWorldFromData())
        assertEquals(size, root.children.size)
    }

    private fun initWorldState() {
        for (i in 1..20) {
            val person = Person(Random.nextInt(60) + 20, if (Random.nextBoolean()) Gender.MALE else Gender.FEMALE, null)
            person.randomSalary()
            person.associativeMating()
            if (person.spouse != null) {
                while (Random.nextBoolean()) {
                    person.newChild()
                    println("${person.name} had a new child!")
                }
            }
            person.children.forEach {
                while (Random.nextBoolean()) {
                    it.newChild()
                    println("${person.name}'s child ${it.name} had a new child!")
                }
            }
            WorldState.lives.add(person)
        }
    }

    private fun initWorldFromData() :List<Person> {
        val jsonStr = """[{"gender":"MALE","fathersName":null,"age":79,"name":{"first":"Abdallah","last":"Umar"},"id":1,"previousSpouseIds":[],"spouseId":2,"parentIds":{"first":null,"second":null},"childrenIds":[34,74,144],"alive":false,"balance":1588965.4251554043,"educationLevel":3,"salary":95760,"health":14,"generation":0},{"gender":"FEMALE","fathersName":null,"age":69,"name":{"first":"SalomÃ©","last":"Gomes"},"id":2,"previousSpouseIds":[1],"spouseId":34,"parentIds":{"first":null,"second":null},"childrenIds":[34,74,144,415,457],"alive":false,"balance":10386983.716280693,"educationLevel":4,"salary":248792,"health":34,"generation":0},{"gender":"FEMALE","fathersName":null,"age":58,"name":{"first":"Aya","last":"Zhou"},"id":3,"previousSpouseIds":[],"spouseId":7,"parentIds":{"first":null,"second":null},"childrenIds":[76],"alive":false,"balance":631059.4002816052,"educationLevel":2,"salary":91021,"health":19,"generation":0},{"gender":"FEMALE","fathersName":null,"age":55,"name":{"first":"Sofia","last":"Shu"},"id":4,"previousSpouseIds":[],"spouseId":47,"parentIds":{"first":null,"second":null},"childrenIds":[435],"alive":false,"balance":8162332.725741999,"educationLevel":3,"salary":98923,"health":19,"generation":0},{"gender":"MALE","fathersName":null,"age":89,"name":{"first":"TomÃ¡s","last":"MuÃ±oz"},"id":5,"previousSpouseIds":[],"spouseId":8,"parentIds":{"first":null,"second":null},"childrenIds":[11,47,75,109],"alive":false,"balance":1269252.1490236898,"educationLevel":2,"salary":96707,"health":18,"generation":0},{"gender":"FEMALE","fathersName":null,"age":60,"name":{"first":"Elena","last":"Shimizu"},"id":6,"previousSpouseIds":[],"spouseId":74,"parentIds":{"first":null,"second":null},"childrenIds":[648],"alive":true,"balance":11886824.83551883,"educationLevel":4,"salary":229598,"health":37,"generation":0},{"gender":"MALE","fathersName":null,"age":58,"name":{"first":"Santiago","last":"Jing"},"id":7,"previousSpouseIds":[3],"spouseId":10,"parentIds":{"first":null,"second":null},"childrenIds":[76],"alive":false,"balance":429870.74150957685,"educationLevel":1,"salary":46772,"health":13,"generation":0},{"gender":"FEMALE","fathersName":null,"age":47,"name":{"first":"Malak","last":"Sinh"},"id":8,"previousSpouseIds":[5],"spouseId":null,"parentIds":{"first":null,"second":null},"childrenIds":[11,47,75,109],"alive":false,"balance":670142.9543498273,"educationLevel":1,"salary":51982,"health":12,"generation":0},{"gender":"FEMALE","fathersName":null,"age":66,"name":{"first":"Lilja","last":"Nguyen"},"id":9,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":null,"second":null},"childrenIds":[],"alive":false,"balance":8218727.111728817,"educationLevel":4,"salary":236869,"health":18,"generation":0},{"gender":"FEMALE","fathersName":null,"age":72,"name":{"first":"Nora","last":"Sahoo"},"id":10,"previousSpouseIds":[7],"spouseId":null,"parentIds":{"first":null,"second":null},"childrenIds":[],"alive":false,"balance":10488705.791334687,"educationLevel":4,"salary":253230,"health":16,"generation":0},{"gender":"FEMALE","fathersName":"MuÃ±oz","age":32,"name":{"first":"Ane","last":"MuÃ±oz"},"id":11,"previousSpouseIds":[],"spouseId":76,"parentIds":{"first":8,"second":5},"childrenIds":[456,502],"alive":true,"balance":202369.3990930265,"educationLevel":1,"salary":30527,"health":82,"generation":1},{"gender":"MALE","fathersName":"Umar","age":30,"name":{"first":"Kristian","last":"Umar"},"id":34,"previousSpouseIds":[2],"spouseId":75,"parentIds":{"first":2,"second":1},"childrenIds":[415,457,503],"alive":true,"balance":644953.4573579005,"educationLevel":3,"salary":76849,"health":95,"generation":1},{"gender":"MALE","fathersName":"MuÃ±oz","age":29,"name":{"first":"SÅta","last":"MuÃ±oz"},"id":47,"previousSpouseIds":[4],"spouseId":144,"parentIds":{"first":8,"second":5},"childrenIds":[435,674],"alive":true,"balance":86839.68135156659,"educationLevel":2,"salary":20400,"health":74,"generation":1},{"gender":"MALE","fathersName":"Umar","age":27,"name":{"first":"Noam","last":"Umar"},"id":74,"previousSpouseIds":[],"spouseId":6,"parentIds":{"first":1,"second":2},"childrenIds":[648],"alive":true,"balance":185139.52245208676,"educationLevel":3,"salary":79244,"health":75,"generation":1},{"gender":"FEMALE","fathersName":"MuÃ±oz","age":27,"name":{"first":"Nour","last":"MuÃ±oz"},"id":75,"previousSpouseIds":[],"spouseId":34,"parentIds":{"first":5,"second":8},"childrenIds":[503],"alive":true,"balance":44222.589318550396,"educationLevel":3,"salary":14700,"health":67,"generation":1},{"gender":"MALE","fathersName":"Jing","age":27,"name":{"first":"Arjun","last":"Jing"},"id":76,"previousSpouseIds":[],"spouseId":11,"parentIds":{"first":7,"second":3},"childrenIds":[456,502],"alive":true,"balance":44222.589318550396,"educationLevel":2,"salary":14700,"health":87,"generation":1},{"gender":"MALE","fathersName":"MuÃ±oz","age":25,"name":{"first":"Asahi","last":"MuÃ±oz"},"id":109,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":8,"second":5},"childrenIds":[],"alive":true,"balance":17163.838455538655,"educationLevel":3,"salary":9400,"health":77,"generation":1},{"gender":"FEMALE","fathersName":"Umar","age":23,"name":{"first":"Yasmine","last":"Umar"},"id":144,"previousSpouseIds":[],"spouseId":47,"parentIds":{"first":1,"second":2},"childrenIds":[674],"alive":true,"balance":38982.66851328101,"educationLevel":2,"salary":38282,"health":76,"generation":1},{"gender":"FEMALE","fathersName":"Umar","age":8,"name":{"first":"Sarah","last":"Umar"},"id":415,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":34,"second":2},"childrenIds":[],"alive":true,"balance":50,"educationLevel":1,"salary":0,"health":69,"generation":2},{"gender":"MALE","fathersName":"MuÃ±oz","age":7,"name":{"first":"Pierre","last":"MuÃ±oz"},"id":435,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":4,"second":47},"childrenIds":[],"alive":true,"balance":50,"educationLevel":3,"salary":0,"health":79,"generation":1},{"gender":"FEMALE","fathersName":"Jing","age":6,"name":{"first":"Julie","last":"Jing"},"id":456,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":11,"second":76},"childrenIds":[],"alive":true,"balance":50,"educationLevel":2,"salary":0,"health":83,"generation":2},{"gender":"FEMALE","fathersName":"Umar","age":6,"name":{"first":"LucÃ­a","last":"Umar"},"id":457,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":34,"second":2},"childrenIds":[],"alive":true,"balance":50,"educationLevel":1,"salary":0,"health":70,"generation":2},{"gender":"FEMALE","fathersName":"Jing","age":4,"name":{"first":"Sofia","last":"Jing"},"id":502,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":11,"second":76},"childrenIds":[],"alive":true,"balance":50,"educationLevel":2,"salary":0,"health":72,"generation":2},{"gender":"MALE","fathersName":"Umar","age":4,"name":{"first":"Omar","last":"Umar"},"id":503,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":34,"second":75},"childrenIds":[],"alive":true,"balance":50,"educationLevel":2,"salary":0,"health":78,"generation":2},{"gender":"MALE","fathersName":"Umar","age":3,"name":{"first":"Habib","last":"Umar"},"id":648,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":74,"second":6},"childrenIds":[],"alive":true,"balance":50,"educationLevel":3,"salary":0,"health":66,"generation":2},{"gender":"MALE","fathersName":"MuÃ±oz","age":2,"name":{"first":"William","last":"MuÃ±oz"},"id":674,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":144,"second":47},"childrenIds":[],"alive":true,"balance":50,"educationLevel":1,"salary":0,"health":72,"generation":2}]"""
        val json = Json(JsonConfiguration.Stable)
        return  json.parse(Person.serializer().list, jsonStr)
    }

}