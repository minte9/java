/**
 * In Kotlin, objects are created using 'val' or 'var' references.
 * Memory allocation happens automatically, just like in Java.
 *
 * There is no 'new' for object creation.
 * The reference variable has a fix type.
 * You cannot assign an object to another type.
 */

package com.minte9.basics.objects

class Object {
    var size: Int = 0
    fun bark() {
        println("Ham Ham")
    }
}

fun main() {
    val myDog = Object()
    myDog.size = 40
    myDog.bark()
}