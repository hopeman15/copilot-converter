package com.hellocuriosity.providers

import com.hellocuriosity.providers.InstantProvider.COINBASE_DATE_TIME_FORMAT
import com.hellocuriosity.providers.InstantProvider.toInstantFrom
import java.time.DateTimeException
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private typealias Testable = Triple<Instant?, String?, String?>

class InstantProviderTest {
    private val data: List<Testable> =
        listOf(
            // Triple(expected, given, pattern),
            Triple(null, null, null),
            Triple(null, null, COINBASE_DATE_TIME_FORMAT),
            Triple(null, "2011-09-06 04:30:00 UTC", null),
            Triple(Instant.ofEpochMilli(1315283400000), "2011-09-06 04:30:00 UTC", COINBASE_DATE_TIME_FORMAT),
        )

    @Test
    fun `Instant provider converts successful`() {
        data.forEach { (expected, given, pattern) ->
            assertEquals(expected, given.toInstantFrom(pattern))
        }
    }

    @Test
    fun `Instant provider should throw DateTimeException with Unknown Pattern`() {
        assertFailsWith<DateTimeException> { "".toInstantFrom("") }
    }
}
