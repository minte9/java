/**
 * Polymorphism in Kotlin - key points
 *
 * Polymorphism allows treating a subclass instance as its superclass type.
 * Kotlin infers the type automatically (no need for casting like in Java).
 */

package com.minte9.oop.polymorphism

fun main() {
    val user = Client()
    user.name = "Mary"

    println(user.reading())  // Mary is reading
    println(user.buying())  // Mary is buying
}

abstract class User {
    var name: String = ""
    fun reading(): String {
        return "$name is reading"
    }
}

class Client: User() {
    fun buying(): String = "$name is buying" // single-expression function
}