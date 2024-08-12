package com.hellocuriosity.generate

import com.hellocuriosity.data.converters.CoinbaseConverter
import com.hellocuriosity.data.converters.CoinbaseToFinanzflussConverter
import com.hellocuriosity.data.converters.Csv
import com.hellocuriosity.data.converters.CsvJsonConverter
import com.hellocuriosity.data.models.coinbase.CoinbaseRaw
import com.hellocuriosity.data.models.coinbase.CoinbaseTransaction
import com.hellocuriosity.data.models.finanzfluss.FinanzflussTransaction
import com.hellocuriosity.providers.FileWriterProvider
import com.hellocuriosity.utils.toCoinbaseTransaction
import com.hellocuriosity.utils.toJson
import io.github.hellocuriosity.forgery
import io.mockk.confirmVerified
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import io.mockk.verify
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.encodeToJsonElement
import kotlin.test.AfterTest
import kotlin.test.Test

class FinanzflussCsvFromCoinbaseCsvTest {
    private val csvConverter: CsvJsonConverter = mockk()
    private val coinbaseConverter: CoinbaseConverter = mockk()
    private val coinbaseToFinanzflussConverter: CoinbaseToFinanzflussConverter = mockk()
    private val fileWriterProvider: FileWriterProvider = mockk()

    private val jsonElement: JsonElement =
        Json.encodeToJsonElement(
            listOf(
                mapOf(
                    "ID" to "123-id",
                    "Timestamp" to "2024-07-15 06:22:17 UTC",
                    "Transaction Type" to "BUY",
                    "Asset" to "BTC",
                    "Quantity Transacted" to "0.0",
                    "Price Currency" to "0.0",
                    "Price at Transaction" to "0.0",
                    "Subtotal" to "0.0",
                    "Total (inclusive of fees and/or spread)" to "0.0",
                    "Fees and/or Spread" to "0.0",
                    "Notes" to "just a note",
                ),
            ),
        )

    private val rawTransactions: List<CoinbaseRaw> = jsonElement.toCoinbaseTransaction()
    private val coinbaseTransaction: CoinbaseTransaction by forgery()

    private val finanzflussTransaction: FinanzflussTransaction by forgery()
    private val finanzflussJson: JsonElement = listOf(finanzflussTransaction).toJson()
    private val csv: Csv = "csv"

    private val sut: FinanzflussCsvFromCoinbaseCsv by lazy {
        FinanzflussCsvFromCoinbaseCsv(
            csvConverter = csvConverter,
            coinbaseConverter = coinbaseConverter,
            coinbaseToFinanzflussConverter = coinbaseToFinanzflussConverter,
            fileWriterProvider = fileWriterProvider,
        )
    }

    @AfterTest
    fun teardown() {
        confirmVerified(
            csvConverter,
            coinbaseConverter,
            coinbaseToFinanzflussConverter,
            fileWriterProvider,
        )
    }

    @Test
    fun testGenerate() {
        every { csvConverter.from(any()) } returns jsonElement
        every { coinbaseConverter.from(any()) } returns coinbaseTransaction
        every { coinbaseToFinanzflussConverter.from(any()) } returns finanzflussTransaction
        every { csvConverter.to(any()) } returns csv
        every { fileWriterProvider.write(any()) } just runs

        sut.generate("test-file")

        verify { csvConverter.from("test-file") }
        verify { coinbaseConverter.from(rawTransactions[0]) }
        verify { coinbaseToFinanzflussConverter.from(coinbaseTransaction) }
        verify { csvConverter.to(finanzflussJson) }
        verify { fileWriterProvider.write(csv) }
    }
}
