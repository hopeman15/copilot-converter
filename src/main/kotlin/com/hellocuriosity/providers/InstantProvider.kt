package com.hellocuriosity.providers

import java.time.Instant
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

object InstantProvider {
    const val COINBASE_DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss z"

    fun String?.toInstantFrom(pattern: String?): Instant? =
        this?.let { date ->
            pattern
                ?.toFormat()
                .toZone(date = date)
                .toInstant()
        }

    private fun String?.toFormat(): DateTimeFormatter? = this?.let(DateTimeFormatter::ofPattern)

    private fun DateTimeFormatter?.toZone(date: String): ZonedDateTime? = this?.let { ZonedDateTime.parse(date, it) }

    private fun ZonedDateTime?.toInstant(): Instant? = this?.toInstant()
}
