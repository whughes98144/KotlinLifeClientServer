package com.willhughes.life

import com.willhughes.util.ConfigReader

object RulzEngine {

    var rulz = readRulz()
    fun applyRule(person: Person) {
        var world = WorldState
        for (rule in rulz) {
            if (rule.active == null || rule?.active) {
                if (eval(rule.conditional)) {
                    eval(rule.action)
                }
            }
        }
    }

    fun readRulz(): List<Rule> {

        var jsonRulz = ArrayList<Rule>()
        for (rule in ConfigReader.jsonObj.rulz) {
            jsonRulz.add(Rule(rule.title, rule.conditional, rule.action, rule.active))
        }
        return jsonRulz
    }

    data class Rule(val title: String, val conditional: String, val action: String, val active: Boolean?)

}
