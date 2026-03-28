/**
 * A class is a blueprint for an object.
 *
 * Properties (fields) hold data an object knows.
 * Functions (methods) define what an object does.
 *
 * For mutable properties Kotlin uses 'var'
 * For read-only properties Kotlin uses 'val'
 *
 * Getter/Setter are created automatically.
 */

package com.minte9.basics.classes

class Student {  // blueprint
    var name: String = ""
}

fun main() {
    val obj = Student()  // object
    obj.name = "John"

    println("Hello ${obj.name}")  // Hello John
}