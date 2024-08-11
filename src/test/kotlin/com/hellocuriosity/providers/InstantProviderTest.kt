package com.hellocuriosity.providers

import com.hellocuriosity.providers.InstantProvider.COINBASE_DATE_TIME_FORMAT
import com.hellocuriosity.providers.InstantProvider.FINANZFLUSS_DATE_TIME_FORMAT
import com.hellocuriosity.providers.InstantProvider.toInstantFrom
import com.hellocuriosity.providers.InstantProvider.toString
import java.time.DateTimeException
import java.time.Instant
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

private typealias TestableToInstant = Triple<Instant?, String?, String?>
private typealias TestableToString = Triple<String?, Instant?, String?>

class InstantProviderTest {
    private val data: List<TestableToInstant> =
        listOf(
            TestableToInstant(null, null, null),
            TestableToInstant(null, null, COINBASE_DATE_TIME_FORMAT),
            TestableToInstant(null, "2011-09-06 04:30:00 UTC", null),
            TestableToInstant(
                Instant.ofEpochMilli(1315283400000),
                "2011-09-06 04:30:00 UTC",
                COINBASE_DATE_TIME_FORMAT,
            ),
        )

    private val toStringData: List<TestableToString> =
        listOf(
            TestableToString(null, null, null),
            TestableToString(null, null, FINANZFLUSS_DATE_TIME_FORMAT),
            TestableToString(null, Instant.ofEpochMilli(1315283400000), null),
            TestableToString("06.09.2011", Instant.ofEpochMilli(1315283400000), FINANZFLUSS_DATE_TIME_FORMAT),
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

    @Test
    fun `Instant to string should convert successfully`() {
        toStringData.forEach { (expected, given, pattern) ->
            assertEquals(expected, given.toString(pattern))
        }
    }
}
