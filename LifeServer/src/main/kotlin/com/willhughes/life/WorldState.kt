package com.willhughes.life

import com.willhughes.util.GaussianRandom

object WorldState {
    var id = 1;
    var inflation: Double = 1.04
    var investmentReturn: Double = 1.06
    var unemploymentRate: Double = 1.04
    val lives = HashSet<Person>()
    var year = 0;

    const val MAX_INFLATION = 8;

    fun anotherYear() {
        year++
        inflation = 1 + (GaussianRandom.nextRandom() / 10);
    }

    fun findAvailableSpouse(person: Person, educationLevel: Int?): Person? {
        for (life in lives) {
            if (life.spouse == null && life.age > 21 && person.gender != life.gender && life.alive) {
                println("Available spouse: $educationLevel, ${life.educationLevel}")
                if (educationLevel != null && life.educationLevel - educationLevel <= 1) {
                    return life
                } else if (educationLevel == null) {
                    return life
                }
            }
        }
        return null
    }

    fun nextId(): Int {
        return id++;
    }

    fun addPerson(person: Person) {
        lives.add(person);
    }

    override fun toString(): String {
        return "Year: $year, NumOfLivePeople: ${lives.size}, lastId: $id"
    }
}