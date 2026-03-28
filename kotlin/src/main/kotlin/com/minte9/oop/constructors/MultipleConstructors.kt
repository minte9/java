/**
 * Kotlin supports multiple constructors.
 *
 * The PRIMARY constructor is the main one.
 * The SECONDARY constructor must delegate to the primary constructor
 * using this(), similar to Java's constructor chaining.
 */

package com.minte9.oop.constructors

fun main() {
    val a = Rectangle()
    val b = Rectangle(100, 200)
    val c = Rectangle(300, 400, 11, 22)
    println(a)  // W: 0 H: 0 x: 0 y: 0
    println(b)  // W: 100 H: 200 x: 0 y: 0
    println(c)  // W: 300 H: 400 x: 11 y: 22
}

class Rectangle (
    var width: Int,
    var height: Int,
    var x: Int,
    var y: Int
) {
    // Secondary constructors
    constructor() : this(0, 0, 0, 0)
    constructor(width: Int, height: Int) : this(width, height, 0, 0)

    override fun toString(): String = "W: $width H: $height x: $x y: $y"
}