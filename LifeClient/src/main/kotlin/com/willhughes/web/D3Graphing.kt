package com.willhughes.web

import kotlin.math.log

external val d3: dynamic
external val Intl: dynamic
class D3Graphing {

    fun circleGraph(family: Any) {
        val data = family
        d3.selectAll("svg > *").remove()
        var format = d3.format(",d")
        val width = 932
        val height = width
        var label: dynamic = null
        var labelBalance: dynamic = null
        var node: dynamic = null
        var svg: dynamic = null

        val color = d3.scaleLinear()
            .domain(arrayOf(0, 5))
            .range(arrayOf("hsl(152,80%,80%)", "hsl(228,30%,40%)"))
            .interpolate(d3.interpolateHcl)

        fun pack(data: dynamic): Any {
            return d3.pack().size(arrayOf(width, height)).padding(3)(d3.hierarchy(data).sum(fun(d: dynamic): dynamic {
                var balance = d.balance
                balance = if (balance <= 1) balance else log(balance, 10.0)
                return balance
            }).sort(fun(a: dynamic, b: dynamic) {
                return b.balance - a.balance;
            }))
        }
        val root: dynamic = pack(data)
        var focus = root
        var view: dynamic = null

        fun zoomTo(v: dynamic) {
            val k = (width / v[2] as Double)
            view = v
            label.attr("transform", fun(d: dynamic): String { return "translate(${(d.x - v[0]) * k},${(d.y - v[1]) * k})" })
            labelBalance.attr("transform", fun(d: dynamic): String { return "translate(${(d.x - v[0]) * k},${(d.y - v[1]+10) * k})"});
            node.attr("transform", fun(d: dynamic): String { return "translate(${(d.x - v[0]) * k},${(d.y - v[1]) * k})" })
            node.attr("r", fun(d: dynamic): dynamic {
                return d.r * k })
        }

        fun zoom(d: dynamic) {
            val focus0 = focus
            focus = d
            val transition = svg.transition()
                .duration(if (d3.event.altKey) 7500 else 750)
                .tween("zoom", fun(d: dynamic): dynamic {
                    val i = d3.interpolateZoom(view, arrayOf(focus.x, focus.y, focus.r * 2))
                    return fun(t: dynamic) {  zoomTo(i(t)) }
                })

            label
                .filter(fun(d: dynamic, i: Int, nodes: dynamic): Boolean {
//                    return (d.parent === focus || nodes[i].setAttribute("style", "display: inline"))
                    return true;
                })
                .transition(transition)
                .style("fill-opacity", fun(d: dynamic): Int { if (d.parent === focus) return 1 else return 0 })
                .on("start", fun(d: dynamic, i: Int, nodes: dynamic) {
                        if (d.parent === focus) nodes[i].setAttribute("style", "display: inline") })
                .on("end", fun(d: dynamic, i: Int, nodes: dynamic) {
                    if (d.parent !== focus) {
                        nodes[i].setAttribute("style", "display: none")
                    }
                })
            labelBalance
                .filter(fun(d: dynamic, i: Int, nodes: dynamic): Boolean {
//                    return (d.parent === focus || nodes[i].setAttribute("style", "display: inline"))
                    return true;
                })
                .transition(transition)
                .style("fill-opacity", fun(d: dynamic): Int { if (d.parent === focus) return 1 else return 0 })
                .on("start", fun(d: dynamic, i: Int, nodes: dynamic) {
                    if (d.parent === focus) nodes[i].setAttribute("style", "display: inline") })
                .on("end", fun(d: dynamic, i: Int, nodes: dynamic) {
                    if (d.parent !== focus) {
                        nodes[i].setAttribute("style", "display: none")
                    }
                })
        }

        svg = d3.select("#mySvg")
            .attr("viewBox", "-${width / 2} -${height / 2} ${width} ${height}")
            .style("display", "block")
            .style("margin", "0 -14px")
            .style("background", color(0))
            .style("cursor", "pointer")
            .on("click", fun() { zoom(root) })

        node = svg.append("g")
            .selectAll("circle")
            .data(root.descendants().slice(1))
            .join("circle")
            .attr("fill", fun(d: dynamic): String { return if (d.children) color(d.depth) else "white" })
            .attr("pointer-events", fun(d: dynamic): String? { return if (!d.children) "none" else null })
            .on("mouseover", fun() {
                d3.event.currentTarget.setAttribute("stroke", "#000")
            })
            .on("mouseout", fun() {
                d3.event.currentTarget.setAttribute("stroke", null)
            })
            .on("click", fun(d: dynamic) { if (focus !== d) {
                zoom(d)
                d3.event.stopPropagation()
            }})

        fun displayBalance(balance: Double) : String {
            return Intl.NumberFormat("en-US").format(balance)
        }
        label = svg.append("g")
            .style("font", "14px sans-serif")
            .attr("pointer-events", "none")
            .attr("text-anchor", "middle y=30")
            .selectAll("text")
            .data(root.descendants())
            .join("text")
            .style("fill-opacity", fun(d: dynamic): Int { return if (d.parent === root) 1 else 0 })
            .style("display", fun(d: dynamic): String { return if (d.parent === root) "inline" else "none" })
            .text(fun(d: dynamic): dynamic {
                return d.data.lastName
            })
        labelBalance = svg.append("g")
            .style("font", "12px sans-serif")
            .attr("pointer-events", "none")
            .attr("text-anchor", "middle")
            .selectAll("text")
            .data(root.descendants())
            .join("text")
            .style("fill-opacity", fun(d: dynamic): Int { return if (d.parent === root) 1 else 0 })
            .style("display", fun(d: dynamic): String { return if (d.parent === root) "inline" else "none" })
            .text(fun(d: dynamic): dynamic {
                return displayBalance(d.data.balance)
            })

        zoomTo(arrayOf(root.x, root.y, root.r * 2))
    }
}