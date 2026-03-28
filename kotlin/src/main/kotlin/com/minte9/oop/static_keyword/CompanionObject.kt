/**
 * Kotlin has no 'static' keyword.
 *
 * Use 'companion object' for static fields/methods.
 * Everything inside a companion object belongs to the class,
 * not to instances (just like Java static).
 *
 * The variable 'totalStudents' is shared across all ClassRoom
 * instances and initialized only once.
 */

package com.minte9.oop.static_keyword

fun main() {
    val classRoom = ClassRoom()

    classRoom.newEntry(Student("Mary"))
    println(ClassRoom.totalStudents)  // 1

    classRoom.newEntry(Student("John"))
    println(ClassRoom.totalStudents)  // 2
}

class ClassRoom {

    // Kotlin equivalent of Java's 'static' field/methods
    companion object {
        var totalStudents: Int = 0
    }

    fun newEntry(student: Student) {
        totalStudents++
    }
}

class Student(val name: String)