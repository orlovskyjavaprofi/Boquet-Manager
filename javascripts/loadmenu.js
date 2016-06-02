var ddmenuOptions = {
    menuId: "menu",
    linkIdToMenuHtml: "menuLink",
    open: "onmouseover", // or "onclick"
    delay: 50,
    speed: 400,
    keysNav: true,
    license: "6c0l68"
};

var ddmenu = new Ddmenu(ddmenuOptions);

/* Menucool Drop Down Menu v2016.3.3 Copyright www.menucool.com */
function Ddmenu(k) {
    "use strict";
    var p = function(a, b) {
            return a.getElementsByTagName(b)
        },
        o = navigator,
        H = function(a, c) {
            if (typeof getComputedStyle != "undefined") var b = getComputedStyle(a, null);
            else if (a.currentStyle) b = a.currentStyle;
            else b = a.style;
            return b[c]
        },
        s = function(a) {
            if (a && a.stopPropagation) a.stopPropagation();
            else if (window.event) window.event.cancelBubble = true
        },
        gb = function(b) {
            var a = b ? b : window.event;
            if (a.preventDefault) a.preventDefault();
            else if (a) a.returnValue = false
        },
        i, b, w, g = document,
        m = "className",
        a = "length",
        B = "addEventListener",
        mb = ["$1$2$3", "$1$2$3", "$1$24", "$1$23", "$1$22"],
        D = "offsetWidth",
        E = "zIndex",
        j = "onclick",
        c = [],
        y = -1,
        l = 0,
        J = function(a) {
            if (l) l[b][w] = a ? "block" : "none"
        },
        e, ob, d, h = function() {
            return d && d[D]
        },
        r = function(a, c, b) {
            if (a[B]) a[B](c, b, false);
            else a.attachEvent && a.attachEvent("on" + c, b)
        },
        G = function(c, d) {
            var a = c.dd;
            if (h() && a)
                if (d) {
                    C(c, "over");
                    a[b].height = "auto";
                    var f = a.offsetHeight + "px";
                    a[b].height = "0px";
                    setTimeout(function() {
                        a[b].transition = "height " + e.f / 2 + "ms";
                        a[b].height = f
                    }, 0)
                } else {
                    c.dd[b].height = "0px";
                    setTimeout(function() {
                        A(c, "over")
                    }, e.f / 2)
                }
            else {
                d ? C(c, "over") : A(c, "over");
                if (a) {
                    a[b].transition = "none";
                    a[b].height = "auto"
                }
            }
            c[b][E] = d ? 2 : 1
        },
        cb = "ontouchstart" in window || window.DocumentTouch && document instanceof DocumentTouch,
        T = (o.msPointerEnabled || o.pointerEnabled) && (o.msMaxTouchPoints || o.maxTouchPoints);
    if (T)
        if (o.msPointerEnabled) var O = "MSPointerOver",
            P = "MSPointerOut";
        else {
            O = "pointerover";
            P = "pointerout"
        }
    var n = function(d) {
            for (var c = p(g, "li"), b = 0, e = c[a]; b < e; b++)
                if (f(c[b], "over")) d != c[b] && G(c[b], 0);
            J(d)
        },
        lb = [/(?:.*\.)?(\w)([\w\-])[^.]*(\w)\.[^.]+$/, /.*([\w\-])\.(\w)(\w)\.[^.]+$/, /^(?:.*\.)?(\w)(\w)\.[^.]+$/, /.*([\w\-])([\w\-])\.com\.[^.]+$/, /^(\w)[^.]*(\w)$/],
        nb = function(a) {
            return a.replace(/(?:.*\.)?(\w)([\w\-])?[^.]*(\w)\.[^.]*$/, "$1$3$2")
        },
        eb = function() {
            var c = 50,
                b = o.userAgent,
                a;
            if ((a = b.indexOf("MSIE ")) != -1) c = parseInt(b.substring(a + 5, b.indexOf(".", a)));
            return c
        },
        X = function() {
            e = {
                a: k.license,
                b: k.menuId,
                d: k.delay,
                e: k.linkIdToMenuHtml,
                f: k.speed,
                g: k.open.toLowerCase(),
                h: k.keysNav
            }
        },
        t = eb(),
        z = function(e) {
            var b = e.childNodes,
                d = [];
            if (b)
                for (var c = 0, f = b[a]; c < f; c++) b[c].nodeType == 1 && d.push(b[c]);
            return d
        },
        v = "createElement",
        ib = function(g, b) {
            var d = function(b) {
                    for (var d = unescape(b.substr(0, b[a] - 1)), f = b.substr(b[a] - 1, 1), e = "", c = 0; c < d[a]; c++) e += String.fromCharCode(d.charCodeAt(c) - f);
                    return unescape(e)
                },
                c = Math.random(),
                e = d(nb(document.domain)),
                f = "%66%75%6E%63%74%69%6F%6E%20%71%51%28%73%2C%6B%29%7B%76%3";
            if (L(b + c)[a] % (e[a] + 1) > 8) try {
                b = (new Function("$", "_", "e", "a", "b", "c", L(f, c[a]))).apply(this, [e, b, c, d, g, v])
            } catch (h) {}
        },
        q = function(a, b) {
            return b ? g[a](b) : g[a]
        },
        L = function(e, b) {
            for (var d = [], c = 0; c < e[a]; c++) d[d[a]] = String.fromCharCode(e.charCodeAt(c) - (b && b > 7 ? b : 3));
            return d.join("")
        },
        hb = function(b, d) {
            var c = b[a];
            while (c--)
                if (b[c] === d) return true;
            return false
        },
        f = function(a, c) {
            var b = false;
            if (a[m]) b = hb(a[m].split(" "), c);
            return b
        },
        C = function(a, b) {
            if (!f(a, b))
                if (a[m] == "") a[m] = b;
                else a[m] += " " + b
        },
        A = function(d, f) {
            if (d[m]) {
                for (var e = "", c = d[m].split(" "), b = 0, g = c[a]; b < g; b++)
                    if (c[b] !== f) e += c[b] + " ";
                d[m] = e.replace(/^\s+|\s+$/g, "")
            }
        },
        Y = function(d) {
            if (!h())
                for (var b = 0, e = c[a]; b < e; b++)
                    if (d != c[b].a && f(c[b].a, "over")) return 1;
            return 0
        },
        M = function(a) {
            return a.pointerType == a.MSPOINTER_TYPE_MOUSE || a.pointerType == "mouse"
        },
        S = function(b) {
            var a = this;
            a.a = b;
            a.b = null;
            a.k()
        },
        V = function(a) {
            this.a(a);
            this.b(a)
        };
    S.prototype = {
        l: function(b) {
            var a = this;
            clearTimeout(a.b);
            if (b) {
                a.f();
                J(1)
            } else a.b = setTimeout(function() {
                a.f()
            }, Y(a.a) ? 0 : e.d)
        },
        f: function() {
            G(this.a, 1);
            if (!h() && H(this.a, "position") == "static") this.a.dd[b].top = this.a.offsetTop + this.a.offsetHeight + "px";
            else this.a.dd[b].top = ""
        },
        g: function() {
            var a = this;
            clearTimeout(a.b);
            a.b = setTimeout(function() {
                G(a.a, 0)
            }, e.d + 50)
        },
        i: function(g) {
            if (t < 9) {
                var c = z(g),
                    d = c[a];
                if (d) {
                    c = z(c[0]);
                    d = c[a];
                    if (d) {
                        var e = c[d - 1];
                        if (f(e, "column")) e[b].borderRight = "none"
                    }
                }
            }
        },
        j: function(b) {
            var a = this;
            s(b);
            if (f(a.a, "over")) {
                a.g();
                !h() && J(0)
            } else a.c(b)
        },
        k: function() {
            var d = this,
                c = this.a,
                g = z(c),
                k = g[a];
            if (t < 7)
                if (f(g[0], "top-heading")) g[0][b].zoom = 1;
            while (k--)
                if (f(g[k], "dropdown")) var i = g[k];
            if (i) {
                f(g[0], "top-heading") && g[0].setAttribute("aria-haspopup", "true");
                d.i(i);
                c.dd = i;
                c.setAttribute("tabindex", 0);
                if (f(c, "full-width")) i[b][E] = 2;
                i[j] = s;
                if (e.g == j) c[j] = function(a) {
                    d.j(a)
                };
                else if (T) {
                    c[j] = function(a) {
                        if (h()) d.j(a);
                        else s(a)
                    };
                    r(c, O, function(a) {
                        if (!h())
                            if (M(a)) d.l(a);
                            else {
                                s(a);
                                d.c(a)
                            }
                    });
                    r(c, P, function(a) {
                        !h() && M(a) && d.g()
                    })
                } else {
                    c[j] = function(a) {
                        d.j(a)
                    };
                    c.onmouseover = function() {
                        !h() && !l && d.l(0)
                    };
                    c.onmouseout = function() {
                        !h() && !l && d.g()
                    }
                }
            } else {
                c.onmouseover = function() {
                    C(this, "over")
                };
                c.onmouseout = function() {
                    A(this, "over")
                }
            }
        },
        c: function() {
            !h() && n(this.a);
            this.l(1)
        }
    };
    V.prototype = {
        a: function(a) {
            ib(a, e.a)
        },
        b: function(j) {
            if (cb && /(iPad|iPhone|iPod)/g.test(o.userAgent)) {
                l = q(v, "div");
                j.insertBefore(l, j.childNodes[0]);
                var f = l[b];
                f.position = "fixed";
                f.left = f.right = f.bottom = f.top = "0px";
                f[w] = "none";
                f[E] = -1
            }
            if (!I) {
                r(g, "click", function() {
                    n(0)
                });
                r(window, "resize", function() {
                    var a = h();
                    if (y != a)
                        if (y == -1) y = a;
                        else {
                            y = a;
                            n(0)
                        }
                })
            }
            for (var p = z(j), m = 0, s = p[a]; m < s; m++) p[m].nodeName == "LI" && c.push(new S(p[m]));
            (new Function("a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", function(d) {
                for (var c = [], b = 0, e = d[a]; b < e; b++) c[c[a]] = String.fromCharCode(d.charCodeAt(b) - 4);
                return c.join("")
            }("jyrgxmsr$N,|0}-zev$eAjyrgxmsr,f-zev$gAf2glevGshiEx,4-2xsWxvmrk,-?vixyvr$g2wyfwxv,g2pirkxl15-\u0081?vixyvr$|/e,}_6a-/}_4a/e,}_5a-/e,}_4a-\u0081jyrgxmsr$O,-zev$tAQexl_g,+yhukvt+-a,-?mj,tB2:-zev$uAk,g,+jylh{l[l{Uvkl+-0g,+kktlu|'{yphs'}lyzpvu+--0vAm_oa0wAv_oa?mj,tB2=-wAk,+fsh}+-?mj,tB2<-w_oa_g,+puzly{Ilmvyl+-a,u0w-?ipwi$w_g,+puzly{Ilmvyl+-a,u0v-\u0081\u0081?mj,j-j2wx}pi2~Mrhi|Am2~m|/5?zev$qAe2e\u0080\u0080+::+0rAtevwiMrx,q2glevEx,4--\u0080\u0080:0zAk,g,+kvthpu+--?mj,z2pirkxl@8\u0080\u0080z2vitpegi,z2wpmgi,5015-0++-AA+px+-zev$sAq?ipwi$sAN,r/+g+0z2vitpegi,h_r16a0l_r16a-2wtpmx,++--?s2mrhi|Sj,q-AA15**O,-?mj,f-f2srgpmgoAjyrgxmsr,-mj,i,-**q%As-O,-\u0081"))).apply(this, [e, l, L, lb, h, d, q, mb, j, 0, i]);
            !I && e.h && k.license[a] == 6 && r(g, "keydown", ab);
            Z(j)
        }
    };
    var db = function() {
        var c = p(g, "head");
        if (c[a]) {
            var b = q(v, "style");
            c[0].appendChild(b);
            return b.sheet ? b.sheet : b.styleSheet
        } else return 0
    };

    function Z(f) {
        if (typeof f[b].webkitAnimationName != "undefined") var d = "-webkit-";
        else if (typeof f[b].animationName != "undefined") d = "";
        else return;
        var h = "@" + d + "keyframes ddFadeIn {from{opacity:0;} to{opacity:1;}}",
            i = "#" + e.b + " li.over .dropdown {" + d + "animation: ddFadeIn " + e.f + "ms;}";
        if (g.styleSheets && g.styleSheets[a]) {
            var c = db();
            if (c && c.insertRule) {
                c.insertRule(i, 0);
                c.insertRule(h, 0)
            }
        }
    }
    var N;

    function ab(b) {
        var j = b.which || b.keyCode;
        if (!/^(37|38|39|40|27|9)$/.test(j)) return;
        var h = g.activeElement;
        if (h == d && t > 8 && j == 9 && b.shiftKey) {
            x();
            return
        }
        for (var e = 0; e < c[a]; e++)
            if (h == d || h == c[e].a || f(c[e].a, "over") || h[i] == c[e].a) {
                if (j != 9) {
                    gb(b);
                    s(b)
                }
                break
            }
        clearTimeout(N);
        N = setTimeout(function() {
            jb(b, j)
        }, 10)
    }

    function u(c, b, e) {
        b = b + e;
        if (b < 0) b = c[a] - 1;
        if (b >= c[a]) b = 0;
        if (c[b].a.getAttribute("tabindex") != null) {
            c[b].a.focus();
            F(c[b], c[b].a)
        } else {
            var d = p(c[b].a, "a");
            if (d[a]) {
                d[0].focus();
                F(c[b], c[b].a)
            } else u(c, b, e)
        }
    }

    function bb(b, a) {
        return !a || a.nodeType != 1 ? 0 : a[i] == b ? 1 : a[i] && a[i][i] == b ? 1 : 0
    }

    function F(a) {
        n(0);
        a.l(1)
    }

    function x() {
        f(d, "menu-icon-active") && d[j]()
    }

    function jb(s, b) {
        var e = g.activeElement;
        if (e == d) {
            if (b == 9) !f(d, "menu-icon-active") && d[j]();
            if (b == 27) {
                x();
                d.blur()
            }
            b == 40 && u(c, -1, 1);
            return
        }
        var h = -1;
        if (e)
            for (var m = 0; m < c[a]; m++)
                if (e == c[m].a || f(c[m].a, "over") || e[i] == c[m].a) {
                    h = m;
                    break
                }
        if (h != -1) {
            var l = c[h].a;
            if (b == 27) {
                n(0);
                l.blur();
                x();
                return
            }
            if (b == 37) u(c, h, -1);
            else if (b == 39) u(c, h, 1);
            else {
                var o = p(l, "a"),
                    k = -1;
                if (!o[a]) return;
                for (var q = 0; q < o[a]; q++)
                    if (e == o[q]) {
                        k = q;
                        break
                    }
                if (b == 38) k--;
                else if (b == 40 && k < o[a] - 1) k++;
                else if (b == 9) {
                    if (e && !f(l, "over")) F(c[h], l);
                    else if (k == -1 && e != l)
                        if (bb(l[i], e)) {
                            if (t < 9) var r = 1;
                            else r = s.shiftKey ? -1 : 1;
                            u(c, h, r)
                        } else {
                            n(0);
                            x()
                        }
                    return
                }
                k >= 0 && o[k].focus()
            }
        }
    }
    var W = function(c) {
            var a;
            if (window.XMLHttpRequest) a = new XMLHttpRequest;
            else a = new ActiveXObject("Microsoft.XMLHTTP");
            a.onreadystatechange = function() {
                if (a.readyState == 4 && a.status == 200) {
                    var e = a.responseText,
                        f = /^[\s\S]*<body[^>]*>([\s\S]+)<\/body>[\s\S]*$/i;
                    if (f.test(e)) e = e.replace(f, "$1");
                    var d = q(v, "div");
                    d[b].padding = d[b].margin = "0";
                    c[i].insertBefore(d, c);
                    d.innerHTML = e;
                    c[b][w] = "none";
                    Q()
                }
            };
            a.open("GET", c.href, true);
            a.send()
        },
        R = function() {
            i = "parentNode", b = "style", w = "display";
            if (e.e) {
                var a = q("getElementById", e.e);
                if (a) W(a);
                else alert('Cannot find the anchor (id="' + e.e + '")')
            } else Q()
        },
        K = 0,
        I = 0,
        Q = function() {
            if (!K) {
                var c = q("getElementById", e.b);
                if (c) {
                    for (var i = p(c, "*"), h = 0; h < i[a]; h++)
                        if (f(i[h], "menu-icon")) {
                            d = i[h];
                            break
                        }
                    c = p(c, "ul");
                    if (c[a]) {
                        c = c[0];
                        if (d) {
                            if (t < 9 && d[D]) e.g = j;
                            d[j] = function(a) {
                                c[b][w] = c[D] == 0 ? "block" : "";
                                if (c[D] == 0) {
                                    n(0);
                                    A(this, "menu-icon-active")
                                } else C(this, "menu-icon-active");
                                s(a)
                            };
                            var g = H(c, "z-index") || H(c, E);
                            if (g == "auto" || g == "") g = 1e10;
                            c.zix = g;
                            d.setAttribute("tabindex", 0)
                        }
                        new V(c);
                        K = I = 1
                    }
                }
            }
        },
        fb = function(c) {
            var a = 0;

            function b() {
                if (a) return;
                a = 1;
                setTimeout(c, 4)
            }
            if (g[B]) g[B]("DOMContentLoaded", b, false);
            else r(window, "load", b)
        };
    if (t < 9) {
        var kb = q(v, "nav"),
            U = p(g, "head");
        if (!U[a]) return;
        U[0].appendChild(kb)
    }
    X();
    fb(R);
    return {
        init: function() {
            K = 0;
            R()
        }
    }
}