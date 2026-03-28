/**
 * In Kotlin, you don't need to manually write getter and setter methods.
 * This is one of the biggest differences between Java and Kotlin.
 *
 * Under the hood, Kotlin still generated setName() and getName() methods
 * to stay java compatible.
 * You can still write custom getter/setter if needed.
 */

package com.minte9.basics.variables

class Dog {
    var name: String = ""
}

class Cat {
    var name: String = ""
        get() = field.uppercase()
        set(value) {
            field = value.trim()
        }
}

fun main() {
    val dog = Dog()
    dog.name = "Rex"   // calls the generated setter
    println(dog.name)  // calls the generated getter

    val cat = Cat()
    cat.name = "Tom "
    println(cat.name)  // TOM
}