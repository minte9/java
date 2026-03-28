/**
 * Inheritance in Kotlin - Important particularities
 *
 * Kotlin classes are final by default.
 * To allow inheritance, a superclass must be marked as 'open' or 'abstract'.
 * An abstract class is automatically open (can be inherited).
 *
 * Kotlin properties automatically generate getter and setters.
 * Declaring 'var action: String' already creates 'getAction()' and 'setAction()'
 * Therefore, you do NOT write your own setter methods as in Java.
 *
 * No 'extends' keyword, Kotlin inheritance uses a colon.
 * No 'new' keyword when creating objects.
 */

package com.minte9.oop.inheritance

fun main() {
    val dog = Dog()
    val bird = Bird()
    dog.action = "barking"
    bird.action = "flying"
    dog.doAction()  // The dog is barking
    bird.doAction()  // The bird is flying
}

// Superclass
abstract class Animal {
    var action: String = ""
    abstract fun doAction()
}

// Subclass 1
class Dog: Animal() {
    override fun doAction() {
        println("The dog is $action")
    }
}

// Subclass 2
class Bird: Animal() {
    override fun doAction() {
        println("The bird is $action")
    }
}