package com.minte9.basics.strings

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class StringConvertionTest {
    val a: Int = "1".toInt()
    val b: Float = "2.22".toFloat()
    val c: Int = 3

    @Test
    fun toNumberTest() {
        assertEquals(a, 1)
        assertEquals(b, 2.23f, 0.1f)
    }

    @Test
    fun toStringTest() {
        assertEquals(c.toString(), "3")
        assertEquals("$c", "3")
    }
}