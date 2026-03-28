/**
 * In real application is best practice to use package structure.
 * This avoids class name conflicts.
 *
 * In Java, the default visibility is package-private.
 * In Kotlin, is different, the default is public.
 */

package com.minte9.basics.classes

class MyClass {  // public (default)
    val greetings = "Hello, Kotlin!"
}

fun main() {
    println(MyClass().greetings)  // Hello, Kotlin!
}