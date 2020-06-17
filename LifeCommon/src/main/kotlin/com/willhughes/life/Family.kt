package com.willhughes.life

import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient

@Serializable
data class Family(
    var lastName: String,
    var spouseName: String?,
    var alive: Boolean,
    var educationLevel: Double,
    var balance: Double,
    var generation: Int,
    @Transient
    var children:List<Family> = ArrayList<Family>()
) {
    var childrenIds: List<String> = ArrayList<String>()
        get() = if (! field.isEmpty()) children.map{it.lastName} else field

    companion object Builder {
        var seen = HashSet<Int>()
        fun getRoot(peopleList: List<Person>):Family {
            seen.clear()
            return Family("root", null, false, 0.0, 0.0, 0, buildFromWorld(peopleList) )
        }

        fun buildFromWorld(people: List<Person>): List<Family> {
            var familySet: MutableList<Family> = ArrayList<Family>()
            for (person in people) {
                if (!seen.contains(person.id)) {
                    seen.add(person.id)
                    var spouseName = ""
                    var educationLevel:Double = person.educationLevel.toDouble()
                    var balance = person.balance
                    if (person.spouse != null) {
                        val spouse = person.spouse
                        seen.add(spouse!!.id)
                        spouseName = spouse.name.last
                        educationLevel += spouse.educationLevel
                        educationLevel /= 2
                        balance += spouse!!.balance
                    }
                    if (person.children.size > 0) {
                        var family = Family(
                            person.name.last,
                            spouseName,
                            person.alive,
                            educationLevel,
                            balance,
                            person.generation
                        )
                        family.children = buildFromWorld(person.children)
                        familySet.add(family);
                    } else {
                        var family = Family(
                            person.name.last,
                            spouseName,
                            person.alive,
                            educationLevel,
                            balance ,
                            person.generation
                        )
                        familySet.add(family)
                    }
                }
            }
            return familySet;
        }
    }
}