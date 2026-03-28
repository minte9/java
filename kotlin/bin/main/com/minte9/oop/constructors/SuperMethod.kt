/**
 * Kotlin has a `super()` call, but the syntax is different because
 * constructors work differently from Java.
 *
 * Kotlin does not use `super()` inside the constructor body.
 * Instead, the super constructor is called in the class header.
 * This call must happen immediately after the subclass constructor declaration.
 */

package com.minte9.oop.constructors

fun main() {
    MyClass()  // Parent constructor called.
}

// Parent class
open class BaseClass {
    constructor() {
        println("Parent constructor called.")
    }
}

class MyClass : BaseClass {
    constructor() : super()  // equivalent to Java's super()
}