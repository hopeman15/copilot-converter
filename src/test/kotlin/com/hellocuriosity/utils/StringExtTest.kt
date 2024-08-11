package com.hellocuriosity.utils

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class StringExtTest {
    @Test
    fun `Currency symbol should be valid with default implementation`() {
        assertEquals("15.00", "€15.00".removeCurrency())
    }

    @Test
    fun `Currency symbol should be valid with alternate currency`() {
        assertEquals("15.00", "$15.00".removeCurrency("$"))
    }

    @Test
    fun `Currency symbol should be null when value is null`() {
        assertNull(null.removeCurrency())
    }

    @Test
    fun `Comma should replace a period`() {
        assertEquals("15,00", "15.00".toComma())
    }
}
