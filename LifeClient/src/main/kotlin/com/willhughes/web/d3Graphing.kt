package com.willhughes.web

import com.willhughes.logConsole
import kotlin.math.log

external val d3: dynamic
class d3Graphing {

    fun circleGraph(family: Any) {
        var data = family
        var root: dynamic;
        d3.selectAll("svg > *").remove();
        var format = d3.format(",d");
        val width = 932;
        val height = width;
        var view = null;
        var svg: dynamic = null;
        var label: dynamic = null;
        var node: dynamic = null;

        var color = d3.scaleLinear()
            .domain(arrayOf(0, 5))
            .range(arrayOf("hsl(152,80%,80%)", "hsl(228,30%,40%)"))
            .interpolate(d3.interpolateHcl)

        fun pack(data: dynamic): Any {
            return d3.pack().size(arrayOf(width, height)).padding(3)(d3.hierarchy(data).sum(fun(d: dynamic): dynamic {
                var balance = d.balance
                balance = if (balance <=1) balance else log(balance,10.0)
                return balance;
            }).sort(fun(a: dynamic, b: dynamic) {
                return b.balance - a.balance;
            }));
        };
        root = pack(data);
        var focus = root;

        fun zoomTo(v: dynamic) {
            val b: Double = v[2]
            var k = width / b;
            view = v
            label.attr("transform", fun(d: dynamic): dynamic {
                return "translate(${(d.x - v[0]) * k},${(d.y - v[1]) * k})"
            });
            node.attr("transform", fun(d: dynamic): dynamic {
                return "translate(${(d.x - v[0]) * k},${(d.y - v[1]) * k})"
            });
            node.attr("r", fun(d: dynamic) {
                return d.r * k;
            });
        }

        fun zoom(d: dynamic) {
            var myThis: dynamic
            focus = d;
            var transition =
                svg.transition().duration(if (d3.event.altKey) 7500 else 750).tween("zoom", fun(d: dynamic): dynamic {
                    var i = d3.interpolateZoom(view, arrayOf(focus.x, focus.y, focus.r * 2));
                    return fun(t: dynamic): dynamic {
                        return zoomTo(i(t));
                    }
                })
            label.filter(fun(d: dynamic, i: Int, nodes: dynamic): dynamic {
                val boolVal = d.parent === focus || nodes[i].setAttribute("style","display: inline")
                return boolVal
            }).transition(transition).style("fill-opacity", fun(d: dynamic): dynamic {
                return if (d.parent === focus) 1 else 0;
            }).on("start", fun(d: dynamic, i: Int, nodes: dynamic) {
                logConsole("start ${i} (${nodes[i]}) v (nope)")
                if (d.parent === focus) {
                    logConsole("start: d.parent == focus, setting to inline for ${i}")
                    nodes[i].setAttribute("style","display: inline")
                }
            }).on("end", fun(d: dynamic, i: Int, nodes: dynamic) {
                logConsole("end ${i} (${nodes[i]}) ")
//                if (d.parent !== focus) {
                    logConsole("end d.parent == focus, setting to none for ${i}")
                    nodes[i].setAttribute("style","display: none")
//                }
            });
        }

        svg =
            d3.select("#mySvg").attr("viewBox", "-${width / 2} -${height / 2} $width $height").style("display", "block")
                .style("margin", "0 -14px").style("background", color(0)).style("cursor", "pointer").on("click", fun() {
                    console.log("d3Select calls zoom")
                    return zoom(root)
                });

        console.log("svg change and is now ($svg) and ${svg.selectAll}")

        node =
            svg.append("g").selectAll("circle").data(root.descendants().slice(1)).join("circle")
                .attr("fill", fun(d: dynamic): dynamic {
                    return if (d.children) color(d.depth) else "white"
                }).attr("pointer-events", fun(d: dynamic): dynamic {
                    return if (!d.children) "none" else null
                }).on("mouseover", fun () {
                    d3.select(d3.event.currentTarget).attr("stroke", "#000");
                }).on("mouseout", fun () {
                    d3.select(d3.event.currentTarget).attr("stroke", null);
                }).on("click", fun(d: dynamic) {
                    if (focus != d) {
                        zoom(d)
                        d3.event.stopPropagation()
                    }
                })

        label = svg.append("g").style("font", "10px sans-serif").attr("pointer-events", "none")
            .attr("text-anchor", "middle").selectAll("text").data(root.descendants()).join("text")
            .style("fill-opacity", fun(d: dynamic): dynamic {
                return if (d.parent == root) 1 else 0
            }).style("display", fun(d: dynamic): dynamic {
                return if (d.parent == root) "inline" else "none"
            }).text(fun(d: dynamic): dynamic {
                return d.data.lastName
            });

        zoomTo(arrayOf(root.x, root.y, root.r * 2));

    }
}