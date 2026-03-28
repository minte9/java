/**
 * Kotlin offer elegant alternatives to manual throwing.
 * Idiomatic exception handling is one of the strongest parts of Kotlin.
 *
 * - require() for bad call input (IllegalArgumentException)
 * - check() for bad internal state logic (IllegalStateException)
 */

package com.minte9.basics.exceptions

fun main() {
    try {
        processAge(-34);  // Correct code (for compiler)
    } catch (e: Exception) {
        println("${e.message}")  // Wrong value!
    }

    try {
        login(null); // Correct code (for compiler)
    } catch (e: Exception) {
        println("${e.message}")  // User must be logged in!
    }
}

fun processAge(age: Int) {
    require(age >= 0) { "Age cannot be negative!"}
    println("Age = $age")
}

class User()
fun login(user: User?) {
    check(user != null) { "User must be logged in!" }
}

