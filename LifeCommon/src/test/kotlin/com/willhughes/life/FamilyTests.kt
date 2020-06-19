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
        assertTrue { root.children != null && root.children?.size!! > 2 }
        val json = Json(JsonConfiguration.Stable)
        val stringify = json.stringify(Family.serializer(), root)
        println("we got $stringify")
    }

    @Test
    fun testFamily2() {
        val root = Family.getRoot(initWorldFromData())
        assertTrue { root.children != null && root.children?.size!! > 2 }
        val json = Json(JsonConfiguration.Stable)
        val stringify = json.stringify(Family.serializer(), root)
        println("2 got $stringify")
    }

    @Test
    fun testFamily4() {
        val root = Family.getRoot(initWorldFromData())
        val size = root.children?.size
        assertTrue(size != null && size > 2)
        val json = Json(JsonConfiguration.Stable)
        val stringify = json.stringify(Family.serializer(), root)
        println("2 got $stringify")
        val familyFromStr = json.parse(Family.serializer(), stringify)
        assertEquals(size, familyFromStr.children?.size)
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
        val jsonStr = """[{"gender":"FEMALE","fathersName":null,"age":70,"name":{"first":"Emilia","last":"Sasaki"},"id":1,"previousSpouseIds":[8],"spouseId":24,"parentIds":{"first":null,"second":null},"childrenIds":[24,114,227,381],"alive":false,"balance":7983085.466161369,"educationLevel":4,"salary":220351,"health":19,"generation":0},{"gender":"MALE","fathersName":null,"age":81,"name":{"first":"Haruta","last":"Begam"},"id":2,"previousSpouseIds":[],"spouseId":5,"parentIds":{"first":null,"second":null},"childrenIds":[21,23],"alive":false,"balance":251851.46033674074,"educationLevel":3,"salary":87846,"health":62,"generation":0},{"gender":"MALE","fathersName":null,"age":35,"name":{"first":"Luiz","last":"Hosseini"},"id":3,"previousSpouseIds":[],"spouseId":6,"parentIds":{"first":null,"second":null},"childrenIds":[],"alive":false,"balance":292911.9570448823,"educationLevel":3,"salary":92286,"health":69,"generation":0},{"gender":"MALE","fathersName":null,"age":61,"name":{"first":"Antoni","last":"Sheng"},"id":4,"previousSpouseIds":[],"spouseId":7,"parentIds":{"first":null,"second":null},"childrenIds":[22,53],"alive":false,"balance":709388.8856479235,"educationLevel":2,"salary":90484,"health":16,"generation":0},{"gender":"FEMALE","fathersName":null,"age":85,"name":{"first":"Sophia","last":"Akther"},"id":5,"previousSpouseIds":[2,24],"spouseId":null,"parentIds":{"first":null,"second":null},"childrenIds":[21,23,501,974,1095],"alive":false,"balance":10874336.057779925,"educationLevel":4,"salary":285961,"health":31,"generation":0},{"gender":"FEMALE","fathersName":null,"age":60,"name":{"first":"Hosna","last":"Kato"},"id":6,"previousSpouseIds":[3],"spouseId":24,"parentIds":{"first":null,"second":null},"childrenIds":[],"alive":false,"balance":6013486.431707695,"educationLevel":3,"salary":177878,"health":15,"generation":0},{"gender":"FEMALE","fathersName":null,"age":88,"name":{"first":"Susan","last":"Carter"},"id":7,"previousSpouseIds":[4],"spouseId":null,"parentIds":{"first":null,"second":null},"childrenIds":[22,53],"alive":false,"balance":560088.74642231,"educationLevel":1,"salary":75518,"health":1,"generation":0},{"gender":"MALE","fathersName":null,"age":53,"name":{"first":"Yong","last":"Araujo"},"id":8,"previousSpouseIds":[],"spouseId":1,"parentIds":{"first":null,"second":null},"childrenIds":[24,114,227],"alive":false,"balance":4062670.5186705347,"educationLevel":3,"salary":101549,"health":18,"generation":0},{"gender":"FEMALE","fathersName":null,"age":61,"name":{"first":"Sofia","last":"Santos"},"id":9,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":null,"second":null},"childrenIds":[],"alive":false,"balance":1284261.138317073,"educationLevel":2,"salary":115116,"health":10,"generation":0},{"gender":"FEMALE","fathersName":null,"age":51,"name":{"first":"Fatin","last":"Smith"},"id":10,"previousSpouseIds":[],"spouseId":24,"parentIds":{"first":null,"second":null},"childrenIds":[],"alive":false,"balance":5418845.984279379,"educationLevel":2,"salary":154291,"health":14,"generation":0},{"gender":"FEMALE","fathersName":"Begam","age":51,"name":{"first":"Nour","last":"Begam"},"id":21,"previousSpouseIds":[],"spouseId":114,"parentIds":{"first":2,"second":5},"childrenIds":[458,479,546,921,1004],"alive":false,"balance":2606159.0253380006,"educationLevel":3,"salary":79163,"health":18,"generation":1},{"gender":"FEMALE","fathersName":"Sheng","age":48,"name":{"first":"Aada","last":"Sheng"},"id":22,"previousSpouseIds":[],"spouseId":23,"parentIds":{"first":4,"second":7},"childrenIds":[400,846],"alive":false,"balance":2733367.429631532,"educationLevel":1,"salary":44494,"health":10,"generation":1},{"gender":"MALE","fathersName":"Begam","age":49,"name":{"first":"Eitan","last":"Begam"},"id":23,"previousSpouseIds":[22],"spouseId":null,"parentIds":{"first":5,"second":2},"childrenIds":[400,846],"alive":false,"balance":2192769.1835488435,"educationLevel":2,"salary":99400,"health":19,"generation":1},{"gender":"MALE","fathersName":"Araujo","age":54,"name":{"first":"Mason","last":"Araujo"},"id":24,"previousSpouseIds":[1,6,10],"spouseId":5,"parentIds":{"first":8,"second":1},"childrenIds":[381,501,974,1095],"alive":false,"balance":8461760.950119024,"educationLevel":3,"salary":79377,"health":16,"generation":1},{"gender":"FEMALE","fathersName":"Sheng","age":47,"name":{"first":"Hussa","last":"Sheng"},"id":53,"previousSpouseIds":[],"spouseId":381,"parentIds":{"first":7,"second":4},"childrenIds":[895],"alive":false,"balance":1613989.8492409245,"educationLevel":1,"salary":44251,"health":19,"generation":1},{"gender":"MALE","fathersName":"Araujo","age":71,"name":{"first":"Murad","last":"Araujo"},"id":114,"previousSpouseIds":[21],"spouseId":546,"parentIds":{"first":1,"second":8},"childrenIds":[458,479,546,921,1004,1257,1524,1791],"alive":false,"balance":42310858.93302053,"educationLevel":4,"salary":177094,"health":17,"generation":1},{"gender":"FEMALE","fathersName":"Araujo","age":51,"name":{"first":"Mia","last":"Araujo"},"id":227,"previousSpouseIds":[],"spouseId":400,"parentIds":{"first":8,"second":1},"childrenIds":[975],"alive":false,"balance":3021773.778068934,"educationLevel":2,"salary":122857,"health":15,"generation":1},{"gender":"MALE","fathersName":"Araujo","age":55,"name":{"first":"Logan","last":"Araujo"},"id":381,"previousSpouseIds":[53,458],"spouseId":546,"parentIds":{"first":24,"second":1},"childrenIds":[895,1096,1634],"alive":true,"balance":3956377.8942433815,"educationLevel":4,"salary":130900,"health":27,"generation":2},{"gender":"MALE","fathersName":"Begam","age":46,"name":{"first":"Mark","last":"Begam"},"id":400,"previousSpouseIds":[227],"spouseId":846,"parentIds":{"first":22,"second":23},"childrenIds":[975],"alive":false,"balance":1579647.598835652,"educationLevel":1,"salary":85000,"health":12,"generation":2},{"gender":"FEMALE","fathersName":"Araujo","age":51,"name":{"first":"Eden","last":"Araujo"},"id":458,"previousSpouseIds":[],"spouseId":381,"parentIds":{"first":114,"second":21},"childrenIds":[1096,1634],"alive":false,"balance":3723634.740415116,"educationLevel":2,"salary":94542,"health":11,"generation":2},{"gender":"MALE","fathersName":"Araujo","age":50,"name":{"first":"Henri","last":"Araujo"},"id":479,"previousSpouseIds":[501],"spouseId":null,"parentIds":{"first":114,"second":21},"childrenIds":[1034,1489,1595],"alive":true,"balance":5169103.260294137,"educationLevel":2,"salary":99025,"health":27,"generation":2},{"gender":"FEMALE","fathersName":"Araujo","age":49,"name":{"first":"Sara","last":"Araujo"},"id":501,"previousSpouseIds":[],"spouseId":479,"parentIds":{"first":24,"second":5},"childrenIds":[1034,1489,1595],"alive":false,"balance":2291193.2201064457,"educationLevel":2,"salary":104908,"health":8,"generation":2},{"gender":"FEMALE","fathersName":"Araujo","age":47,"name":{"first":"Eevi","last":"Araujo"},"id":546,"previousSpouseIds":[114],"spouseId":381,"parentIds":{"first":114,"second":21},"childrenIds":[1257,1524,1791],"alive":true,"balance":4498937.7250849195,"educationLevel":4,"salary":113266,"health":67,"generation":2},{"gender":"FEMALE","fathersName":"Begam","age":34,"name":{"first":"Malk","last":"Begam"},"id":846,"previousSpouseIds":[400],"spouseId":895,"parentIds":{"first":22,"second":23},"childrenIds":[1596,1673],"alive":true,"balance":280636.3627981623,"educationLevel":3,"salary":36400,"health":81,"generation":2},{"gender":"MALE","fathersName":"Araujo","age":32,"name":{"first":"Asahi","last":"Araujo"},"id":895,"previousSpouseIds":[],"spouseId":846,"parentIds":{"first":53,"second":381},"childrenIds":[1596,1673],"alive":true,"balance":743473.9102748929,"educationLevel":2,"salary":75067,"health":84,"generation":2},{"gender":"FEMALE","fathersName":"Araujo","age":31,"name":{"first":"Fatma","last":"Araujo"},"id":921,"previousSpouseIds":[],"spouseId":1004,"parentIds":{"first":21,"second":114},"childrenIds":[1792],"alive":true,"balance":147807.93714407994,"educationLevel":2,"salary":26500,"health":82,"generation":2},{"gender":"FEMALE","fathersName":"Araujo","age":29,"name":{"first":"Linda","last":"Araujo"},"id":974,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":24,"second":5},"childrenIds":[],"alive":true,"balance":86839.68135156659,"educationLevel":3,"salary":20400,"health":78,"generation":2},{"gender":"FEMALE","fathersName":"Begam","age":29,"name":{"first":"SofÃ­a","last":"Begam"},"id":975,"previousSpouseIds":[],"spouseId":1095,"parentIds":{"first":227,"second":400},"childrenIds":[1916],"alive":true,"balance":86839.68135156659,"educationLevel":3,"salary":20400,"health":80,"generation":2},{"gender":"MALE","fathersName":"Araujo","age":28,"name":{"first":"Szymon","last":"Araujo"},"id":1004,"previousSpouseIds":[],"spouseId":921,"parentIds":{"first":21,"second":114},"childrenIds":[1792],"alive":true,"balance":509457.89114154666,"educationLevel":4,"salary":113714,"health":91,"generation":2},{"gender":"FEMALE","fathersName":"Araujo","age":27,"name":{"first":"Nur","last":"Araujo"},"id":1034,"previousSpouseIds":[],"spouseId":1096,"parentIds":{"first":479,"second":501},"childrenIds":[],"alive":true,"balance":44222.589318550396,"educationLevel":2,"salary":14700,"health":79,"generation":3},{"gender":"MALE","fathersName":"Araujo","age":25,"name":{"first":"Onni","last":"Araujo"},"id":1095,"previousSpouseIds":[],"spouseId":975,"parentIds":{"first":24,"second":5},"childrenIds":[1916],"alive":true,"balance":98516.00242553865,"educationLevel":3,"salary":86155,"health":76,"generation":2},{"gender":"MALE","fathersName":"Araujo","age":25,"name":{"first":"Eino","last":"Araujo"},"id":1096,"previousSpouseIds":[],"spouseId":1034,"parentIds":{"first":458,"second":381},"childrenIds":[],"alive":true,"balance":17163.838455538655,"educationLevel":2,"salary":9400,"health":61,"generation":3},{"gender":"FEMALE","fathersName":"Araujo","age":20,"name":{"first":"Emilia","last":"Araujo"},"id":1257,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":546,"second":114},"childrenIds":[],"alive":true,"balance":50,"educationLevel":1,"salary":0,"health":84,"generation":3},{"gender":"FEMALE","fathersName":"Araujo","age":13,"name":{"first":"Sarah","last":"Araujo"},"id":1489,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":501,"second":479},"childrenIds":[],"alive":true,"balance":50,"educationLevel":2,"salary":0,"health":77,"generation":3},{"gender":"MALE","fathersName":"Araujo","age":12,"name":{"first":"Nuka","last":"Araujo"},"id":1524,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":114,"second":546},"childrenIds":[],"alive":true,"balance":50,"educationLevel":3,"salary":0,"health":65,"generation":2},{"gender":"FEMALE","fathersName":"Araujo","age":10,"name":{"first":"MarÃ­a Fernanda","last":"Araujo"},"id":1595,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":479,"second":501},"childrenIds":[],"alive":true,"balance":50,"educationLevel":2,"salary":0,"health":84,"generation":3},{"gender":"MALE","fathersName":"Araujo","age":10,"name":{"first":"","last":"Araujo"},"id":1596,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":895,"second":846},"childrenIds":[],"alive":true,"balance":50,"educationLevel":4,"salary":0,"health":70,"generation":3},{"gender":"FEMALE","fathersName":"Araujo","age":9,"name":{"first":"Dulce MarÃ­a","last":"Araujo"},"id":1634,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":458,"second":381},"childrenIds":[],"alive":true,"balance":50,"educationLevel":3,"salary":0,"health":82,"generation":3},{"gender":"FEMALE","fathersName":"Araujo","age":8,"name":{"first":"Eden","last":"Araujo"},"id":1673,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":895,"second":846},"childrenIds":[],"alive":true,"balance":50,"educationLevel":1,"salary":0,"health":75,"generation":3},{"gender":"FEMALE","fathersName":"Araujo","age":5,"name":{"first":"Marie","last":"Araujo"},"id":1791,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":546,"second":114},"childrenIds":[],"alive":true,"balance":50,"educationLevel":3,"salary":0,"health":69,"generation":3},{"gender":"MALE","fathersName":"Araujo","age":5,"name":{"first":"Liam","last":"Araujo"},"id":1792,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":1004,"second":921},"childrenIds":[],"alive":true,"balance":50,"educationLevel":1,"salary":0,"health":74,"generation":3},{"gender":"FEMALE","fathersName":"Araujo","age":2,"name":{"first":"Yasmine","last":"Araujo"},"id":1916,"previousSpouseIds":[],"spouseId":null,"parentIds":{"first":1095,"second":975},"childrenIds":[],"alive":true,"balance":50,"educationLevel":2,"salary":0,"health":67,"generation":3}]"""
        val json = Json(JsonConfiguration.Stable)
        return  json.parse(Person.serializer().list, jsonStr)
    }

}