/**
 * Interfaces in Kotlin - Important notes
 *
 * A class implements an interface using ":".
 * All interface members are implicitly open (can be overwritten).
 */

package com.minte9.oop.interfaces

fun main() {
    val cat = Cat()
    cat.move()  // The cat is moving.
    cat.play()  // The cat is playing.
}

abstract class Feline {
    abstract fun move()
}

interface Playable {
    fun play()
}

class Cat : Feline(), Playable {
    override fun move() {
        println("The cat is moving.")
    }
    override fun play() {
        println("The cat is playing.")
    }
}