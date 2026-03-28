/**
 * Shared state in Kotlin.
 *
 * Kotlin tries to avoid classes for no reason.
 * Kotlin allows top-level properties and functions,
 * which behave like static fields and methods on the JVM.
 */

package com.minte9.oop.static_keyword

var totalEmployees = 0  // top-level shared counter

fun main() {
    newEntry(Employee("Mary"))
    println(totalEmployees)  // 1

    newEntry(Employee("John"))
    println(totalEmployees)  // 2
}

fun newEntry(employee: Employee) {
    totalEmployees++
}

class Employee(val name: String)