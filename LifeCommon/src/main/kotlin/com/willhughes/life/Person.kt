package com.willhughes.life

import com.willhughes.util.GaussianRandom
import com.willhughes.util.Logger
import kotlinx.serialization.Serializable
import kotlinx.serialization.Transient
import kotlin.math.roundToInt
import kotlin.random.Random

@Serializable
open class Person(var gender: Gender, val fathersName: String?) {
    var age: Int = 0
    var name: Name = NameBuilder.uniqueName(gender, fathersName)
    val id: Int = WorldState.nextId()
    @Transient
    var previousSpouses = ArrayList<Person>()
    var previousSpouseIds: List<Int>? = null
        get() = if (field == null) previousSpouses.map{it.id} else field
    @Transient
    var spouse: Person? = null
    var spouseId: Int? = null
        get() = if(field == null) spouse?.id else field
    @Transient
    var parents: Pair<Person?, Person?>? = null
    var parentIds: Pair<Int?, Int?>? = null
        get() = if (field == null) Pair(parents?.first?.id, parents?.second?.id) else field
    @Transient
    var children = ArrayList<Person>()
    var childrenIds : List<Int>? = null
            get() = if (field == null) children.map{it.id} else field
    var alive = true;
    var balance: Double = 50.0
    var educationLevel = (1..4).random() // 0=No HS, 1=HS, 2=College, 3=post-college
    var salary: Int = 0
    var health: Int = (GaussianRandom.nextRandom() * 150).roundToInt() // avg 75?
    var generation = 0

    constructor(age: Int, gender: Gender, fathersName: String?) : this(gender, fathersName) {
        this.age = age
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other == null || this::class.js != other::class.js) return false
        other as Person
        if (id != other.id) return false
        return true
    }

    override fun hashCode(): Int {
        return id
    }

    override fun toString(): String {
        var s =
            "$id: $name: gender: $gender, age: $age, gen: $generation, health: $health, educ: $educationLevel, salary: $salary, balance: $balance, spouse: ${spouseId}, children: ${childrenIds}, spouses: ${previousSpouseIds}"
        if (!alive) s = "\t( $s )"
        return s
    }

    fun ifDies(): Boolean {
        return Death.comes(age)
    }

    fun hasDied() {
        Logger.log("\t${name} has died", 4)
        alive = false;
        if (spouse != null) {
            spouse?.previousSpouses?.add(this)
            spouse?.spouse = null
        }
    }

    fun anotherYear() {
        if (alive) {
            age++;
            RulzEngine.applyRule(this)
        }
    }

    fun associativeMating() {
        var spouse = WorldState.findAvailableSpouse(this, educationLevel)
        if (spouse != null) {
            gotMarried(spouse)
        }

    }

    fun gotMarried() {
        var spouse = WorldState.findAvailableSpouse(this, null)
        if (spouse != null) {
            gotMarried(spouse)
        }
    }

    fun gotMarried(spouse: Person) {
        this.spouse = spouse
        spouse.spouse = this
        Logger.log("\t${name} got married to ${spouse.name}!!")
    }

    fun randomSalary() {
        salary = (GaussianRandom.nextRandom() * 50000 * educationLevel).roundToInt()
    }

    fun newChild() {
        Logger.log("\t${name} and ${spouse?.name} had a child! ");
        val child = Person(
            if (Random.nextBoolean()) Gender.MALE else Gender.FEMALE,
            null
        )
        child.age = 1
        child.generation = generation + 1
        randomSalary()
        val spouse = spouse
        child.parents = Pair(this, spouse)
        children.add(child)
        spouse?.children?.add(child)
        WorldState.lives.add(child)
        Logger.log("\tWelcome ${child.name}");
    }

    @Serializable
    data class Name(val first: String, val last: String) {
        override fun toString(): String {
            return "$first $last"
        }
    }
}





