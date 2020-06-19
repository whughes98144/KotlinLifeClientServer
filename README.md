# Kotlin Client/Server JS with D3 Zoomable Circle Packing

Wanted to play with Kotlin and learn a bit about the D3 library so waded in

## Installation

Get the code and build/run with gradle/nodeJs.  Should build and launch the server, currently running on :8010
```
gradle clean run
```
Honestly, I haven't tried it clean and use IntelliJ so ... 

## The meat

First off, don't pass a Kotlin object (say from data class) into D3 as data.  Ouch, that caused me hours of 
pain.  It all looks good in the js debugger but doesn't work.  I was getting data from json, and using the kotlinx serialization
which works great.  But yeah, no.  Just JSON.parse(...) and pass that to D3.

Next, this.  Wouch.  In javascript, from some event listener, it's bound to the element that called it.
I believe Kotlin needs to work this out.  I think.  Could be the D3 libs overshadowing it.  But I haven't completely
figured out to get around it yet.  'this' will be the Kotlin instance.  In many cases D3 has an alternate
way to get you the element. Like below, if you look in the docs enough and check the js, you'll see that actually,
the filter and corresponding start/end callbacks actually get three params, so you can get the current
element from the nodes array along with the index 'i'.

```python
            label.filter(fun(d: dynamic, i: Int, nodes: dynamic): dynamic {
                val boolVal = d.parent === focus || nodes[i].setAttribute("style","display: inline")
                console.log("boolVal $boolVal")
                return boolVal
            }).transition(transition).style("fill-opacity", fun(d: dynamic): dynamic {
                return if (d.parent == focus) 1 else 0;
            }).on("start", fun(d: dynamic, i: Int, nodes: dynamic) {
                if (d.parent === focus) nodes[i].setAttribute("style","display: inline")
            }).on("end", fun(d: dynamic, i: Int, nodes: dynamic) {
                if (d.parent !== focus) nodes[i].setAttribute("style","display: none")
            });

```
Beyond that, much of the D3 syntax on Observable is ES6 which is very translatable to Kotlin (lambdas, string interpolation, etc), just gotta
change 'function' to 'fun' and type the parameter lists.  arrayOf, also.  But really straightforward and worked surprisingly well.

## Credits
[Mike Bostock @ ObservableHQ.com](https://observablehq.com/@d3/zoomable-circle-packing)  Amazing library and play area.

## Contributing
Pull requests are welcome. For major changes, please open an issue first to discuss what you would like to change.

Please make sure to update tests as appropriate.

## License
[GNU GPL](https://www.gnu.org/licenses/gpl-3.0.en.html)