/**
 * In Kotlin, variables hold references to objects, just like in Java.
 *
 * When one reference is reassigned, the old object may become
 * unreachable and is the eligible for garbage collection.
 */
package com.minte9.basics.objects

data class Book (val name: String)

fun main() {
    var a = Book(name = "A")
    var b = Book(name = "B")
    print("${a.name}${b.name} ")  // AB

    var c = b  // c references the same object as b
    print("${a.name}${b.name}${c.name} ")  // ABB

    b = a  // b now points to a; object "B" has no more references
    print("${a.name}${b.name}${c.name} ")  // AAB
}