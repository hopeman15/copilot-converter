package com.hellocuriosity.utils

import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNull

class StringExtTest {

    @Test
    fun `Currency to Double should be valid with default implementation`() {
        assertEquals(15.00, "€15.00".currencyToDouble())
    }

    @Test
    fun `Currency to Double should be valid with alternate currency`() {
        assertEquals(15.00, "$15.00".currencyToDouble("$"))
    }

    @Test
    fun `Currency to Double should be null when not a valid number`() {
        assertNull("not a number".currencyToDouble())
    }

    @Test
    fun `Currency to Double should be null when value is null`() {
        assertNull(null.currencyToDouble())
    }
}
