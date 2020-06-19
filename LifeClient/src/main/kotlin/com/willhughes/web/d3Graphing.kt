package com.willhughes.web

import kotlin.math.log

//external object d3 {
//    fun selectAll(str: String): d3
//    fun remove(): d3
//}
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
//            console.log("pack:" + data.balance + ")hier(" + d3.hierarchy(data)[0] + ")");
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
                console.log("boolVal $boolVal")
                return boolVal
            }).transition(transition).style("fill-opacity", fun(d: dynamic): dynamic {
                return if (d.parent == focus) 1 else 0;
            }).on("start", fun(d: dynamic, i: Int, nodes: dynamic) {
                myThis = this
                if (d.parent === focus) nodes[i].setAttribute("style","display: inline")
            }).on("end", fun(d: dynamic, i: Int, nodes: dynamic) {
                myThis = this
                if (d.parent !== focus) nodes[i].setAttribute("style","display: none")
            });
        }

        svg =
            d3.select("#mySvg").attr("viewBox", "-${width / 2} -${height / 2} $width $height").style("display", "block")
                .style("margin", "0 -14px").style("background", color(0)).style("cursor", "pointer").on("click", fun() {
                    console.log("d3Select calls zoom")
                    return zoom(root)
                });
        node =
            svg.append("g").selectAll("circle").data(root.descendants().slice(1)).join("circle")
                .attr("fill", fun(d: dynamic): dynamic {
                    return if (d.children) color(d.depth) else "white"
                }).attr("pointer-events", fun(d: dynamic): dynamic {
                    return if (!d.children) "none" else null
                }).on("mouseover", fun() {
                    d3.select(this).attr("stroke", "#000")
                }).on("mouseout", fun() {
                    d3.select(this).attr("stroke", null)
                }).on("click", fun(d: dynamic) {
                    if (focus !== d) {
                        zoom(d)
                        d3.event.stopPropagation()
                    }
                });

        label = svg.append("g").style("font", "10px sans-serif").attr("pointer-events", "none")
            .attr("text-anchor", "middle").selectAll("text").data(root.descendants()).join("text")
            .style("fill-opacity", fun(d: dynamic): dynamic {
                return if (d.parent === root) 1 else 0
            }).style("display", fun(d: dynamic): dynamic {
                return if (d.parent === root) "inline" else "none"
            }).text(fun(d: dynamic): dynamic {
                return d.data.lastName
            });

      zoomTo(arrayOf(root.x, root.y, root.r * 2));

        var something = svg.node()
    }

//        js(
//            """
//
//                        root = pack(data);
//
//                function pack(data) {
//            console.log("pack:" + data.balance + ")hier(" + d3.hierarchy(data)[0] + ")");
//  return d3.pack().size([width, height]).padding(3)(d3.hierarchy(data).sum(function (d) {
//    return d.balance >= 1 ? Math.log(d.balance) : 1;
//  }).sort(function (a, b) {
//    return b.balance - a.balance;
//  }));
//};
//function zoomTo(v) {
//    var k = width / v[2];
//    view = v;
//    label.attr("transform", function (d) {
////console.log("label:v[0] is ("+v[0]+")d.x:" + d.x + "-k:"+k+"-v1:"+v[1]+",width:"+width+",v2:"+v[2]);
//      return "translate(".concat((d.x - v[0]) * k, ",").concat((d.y - v[1]) * k, ")");
//    });
//    node.attr("transform", function (d) {
////console.log("node:v[0] is ("+v[0]+")d.x:" + d.x + "-k:"+k+"-v1:"+v[1]);
//      return "translate(".concat((d.x - v[0]) * k, ",").concat((d.y - v[1]) * k, ")");
//    });
//    node.attr("r", function (d) {
//      return d.r * k;
//    });
//  }
//"""
//        )
//        js(chartStr)
//    }

//    val chartStr = """
    
//  var focus = root;
//  var svg = d3.select("#mySvg").attr("viewBox", "-".concat(width / 2, " -").concat(height / 2, " ").concat(width, " ").concat(height)).style("display", "block").style("margin", "0 -14px").style("background", color(0)).style("cursor", "pointer").on("click", function () {
//    console.log("d3Select calls zoom");
//    return zoom(root);
//  });
//  console.log("about to append:"+ root.descendants().length);
//  var node = svg.append("g").selectAll("circle").data(root.descendants().slice(1)).join("circle").attr("fill", function (d) {
//    return d.children ? color(d.depth) : "white";
//  }).attr("pointer-events", function (d) {
//    return !d.children ? "none" : null;
//  }).on("mouseover", function () {
//    d3.select(this).attr("stroke", "#000");
//  }).on("mouseout", function () {
//    d3.select(this).attr("stroke", null);
//  }).on("click", function (d) {
//    return focus !== d && (zoom(d), d3.event.stopPropagation());
//  });
//  var label = svg.append("g").style("font", "10px sans-serif").attr("pointer-events", "none").attr("text-anchor", "middle").selectAll("text").data(root.descendants()).join("text").style("fill-opacity", function (d) {
//    return d.parent === root ? 1 : 0;
//  }).style("display", function (d) {
//    return d.parent === root ? "inline" : "none";
//  }).text(function (d) {
//    return d.data.lastName;
//  });
//  console.log("first call to zoomTo:"+root.r+")");
//  zoomTo([root.x, root.y, root.r * 2]);


//  function zoom(d) {
//    var focus0 = focus;
//    focus = d;
//    var transition = svg.transition().duration(d3.event.altKey ? 7500 : 750).tween("zoom", function (d) {
//      var i = d3.interpolateZoom(view, [focus.x, focus.y, focus.r * 2]);
//      return function (t) {
////      console.log("zoomToFromZoom:"+i(t)+")");
//        return zoomTo(i(t));
//      };
//    });
//    label.filter(function (d) {
//      return d.parent === focus || this.style.display === "inline";
//    }).transition(transition).style("fill-opacity", function (d) {
//      return d.parent === focus ? 1 : 0;
//    }).on("start", function (d) {
//      if (d.parent === focus) this.style.display = "inline";
//    }).on("end", function (d) {
//      if (d.parent !== focus) this.style.display = "none";
//    });
//  }
//  console.log("past it all");
//
//   var something = svg.node();
//   
//
//var color = d3.scaleLinear().domain([0, 5]).range(["hsl(152,80%,80%)", "hsl(228,30%,40%)"]).interpolate(d3.interpolateHcl);

//    """


}