/**
 * In Kotlin, class properties (instance variables) must be initialized,
 * unless they are declared as nullable or with a default value.
 *
 * Local variable inside functions must also be initialized before use.
 */

package com.minte9.basics.variables

class MyClass {}

class DefaultValues {
    var a: Int = 0  // default value explicitly set
    var b: Float = 0.0f
    var c: Boolean = false
    var v: MyClass? = null  // nullable reference

    fun showValues() {
        val localVariable = "a"  // local variable must be initialized

        println("int a = $a")
        println("float b = $b")
        println("boolean c = $c")
        println("object v = $v")
        println("String localVariable = $localVariable")
    }
}

fun main() {
    DefaultValues().showValues()
}

/**
    int a = 0
    float b = 0.0
    boolean c = false
    object v = null
    String localVariable = a
 */