/**
 * Polymorphism + Dependency Injection in Kotlin
 *
 * FileOpener receive an Item in its constructor (DI).
 * At runtime, the same fileOpener.open() produces different
 * behavior depending on whether we injected Csv or Xml.
 */

package com.minte9.oop.polymorphism

fun main() {
    val csv = Csv()
    val xml = Xml()
    val csvOpener = FileOpener(csv)
    val xmlOpener = FileOpener(xml)
    csv.open()  // CSV opened
    xml.open()  // XML opened
}

abstract class Item {
    abstract fun open()
}

class Csv: Item() {
    override fun open() = println("CSV opened")
}

class Xml: Item() {
    override fun open() = println("XML opened")
}

class FileOpener (private val item: Item) {  // Dependency Injection
    fun open() = item.open()  // polymorphic call
}