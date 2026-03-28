/**
 * TreeSet in Kotlin - Key Concepts
 *
 * Kotlin uses Java's TreeSet under the hood.
 * TreeSet stores UNIQUE elements and keeps them automatically sorted.
 */
package com.minte9.collections.sets

import java.util.TreeSet

fun main() {
    val myTree = TreeSet<A>()
    myTree.add(A("F", "1"))
    myTree.add(A("G", "2"))
    myTree.add(A("H", "4"))
    myTree.add(A("H", "3"))  // Duplicate according to compareTo → ignored

    println(myTree)  // [F (1), G (2), H (4)]
}

class A(val title: String, val artist: String) : Comparable<A> {
    override fun compareTo(other: A): Int = title.compareTo(other.title)
    override fun toString() = "$title ($artist)"
}