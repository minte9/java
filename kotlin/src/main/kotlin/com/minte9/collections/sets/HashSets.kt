/**
 * HashSet in Kotlin - Key Concepts
 *
 * Kotlin uses Java's HashSet under the hood.
 * HashSet stores UNIQUE elements and does NOT preserve order.
 * Data classes automatically provide toString(), equals(), hoshCode().
 */

package com.minte9.collections.sets

fun main() {
    val mySet = hashSetOf("AAA", "AAA", "BBB", "CCC")
    println(mySet)  // [AAA, CCC, BBB]  (order may vary)

    val songs = hashSetOf(
        Song("Imagine", "John Lennon"),
        Song("Imagine", "John Lennon"),  // duplicate - ignored
        Song("Africa", "Toto"),
        Song("Africa", "Weezer")          // not a duplicate
    )
    println(songs) // [Africa (Weezer), Imagine (John Lennon), Africa (Toto)]
}

data class Song(val title: String, val artist: String) {
    override fun toString() = "$title ($artist)"
}