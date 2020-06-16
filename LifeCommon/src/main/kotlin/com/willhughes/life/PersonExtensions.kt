package com.willhughes.life

import com.willhughes.util.Logger
import kotlin.random.Random

//
//fun Person.anotherYear() {
//    if (alive) {
//        age++;
//        RulzEngine.applyRule(this)
//    }
//}
//
//fun Person.ifDies(): Boolean {
//    return Death.comes(age)
//}
//
//fun Person.hasDied() {
//    Logger.log("\t${name} has died", 4)
//    alive = false;
//    if (spouse != null) {
//        spouse?.previousSpouses?.add(this)
//        spouse?.spouse = null
//    }
////        WorldState.hasDied(this)
//}
//
//fun Person.associativeMating() {
//    var spouse = WorldState.findAvailableSpouse(this, educationLevel)
//    if (spouse != null) {
//        gotMarried(spouse)
//    }
//
//}
//
//fun Person.gotMarried() {
//    var spouse = WorldState.findAvailableSpouse(this, null)
//    if (spouse != null) {
//        gotMarried(spouse)
//    }
//}
//
//fun Person.gotMarried(spouse: Person) {
//    this.spouse = spouse
//    spouse.spouse = this
//    Logger.log("\t${name} got married to ${spouse.name}!!")
//}
//
//fun Person.newChild() {
//    Logger.log("\t${name} and ${spouse?.name} had a child! ");
//    val child = Person(
//        1,
//        if (Random.nextBoolean()) Gender.MALE else Gender.FEMALE,
//        if (gender == Gender.MALE) name.last else spouse?.name?.last
//    )
//    child.generation = generation + 1
//    val spouse = spouse
//    child.parents = Pair(this, spouse)
//    children.add(child)
//    spouse?.children?.add(child)
//    WorldState.lives.add(child)
//    Logger.log("\tWelcome ${child.name}");
//}
//
