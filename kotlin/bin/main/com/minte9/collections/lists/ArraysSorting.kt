/**
 * Kotlin does not modify the original when sorting an immutable list.
 * Instead, it returns a new sorted list.
 *
 * Mutable list can be sort in place.
 * Data classes automatically provide toString(), equals(), hoshCode().
 */

package com.minte9.collections.lists

fun main() {

    // Sorting immutable lists
    val scores = listOf(85, 90, 87)
    val sorted = scores.sorted()
    println(sorted)  // [85, 87, 90]

    // Sorting mutable lists
    val cart = mutableListOf("chocolate", "apple", "banana")
    cart.sort()
    println(cart)  // [apple, banana, chocolate]

    // Sorting lists of objects
    data class User(val name: String, val age: Int) {
        override fun toString() = "$name $age"
    }
    val users = listOf(
        User("Bob", 20),
        User("Alice", 30),
        User("Charlie", 25)
    )
    val sortedByAge = users.sortedBy { it.age }
    println(sortedByAge)  // [Bob 20, Charlie 25, Alice 30]
}