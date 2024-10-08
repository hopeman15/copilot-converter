package com.hellocuriosity.data.converters

import com.hellocuriosity.data.models.coinbase.CoinbaseRaw
import com.hellocuriosity.data.models.coinbase.CoinbaseTransaction
import com.hellocuriosity.data.models.coinbase.toCoinbaseTransactionType
import com.hellocuriosity.providers.InstantProvider.COINBASE_DATE_TIME_FORMAT
import com.hellocuriosity.providers.InstantProvider.toInstantFrom
import com.hellocuriosity.utils.removeCurrency
import io.github.hellocuriosity.ModelForge
import io.github.hellocuriosity.addProvider
import io.github.hellocuriosity.build
import io.github.hellocuriosity.forgery
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CoinbaseConverterTest {
    private val converter = CoinbaseConverter

    private val forge =
        ModelForge().apply {
            addProvider {
                CoinbaseRaw(
                    id = this.build(),
                    timestamp = this.build(),
                    type = this.build<CoinbaseTransaction.TransactionType>().value,
                    asset = this.build(),
                    quantityTransacted = this.build<Double>().toString(),
                    priceCurrency = this.build(),
                    priceAtTransaction = this.build(),
                    subtotal = this.build(),
                    total = this.build(),
                    fees = this.build(),
                    notes = this.build(),
                )
            }
        }

    @Test
    fun `Coinbase conversion raw to transaction is successful`() {
        val transaction: CoinbaseRaw = forge.build<CoinbaseRaw>().copy(timestamp = "2024-07-15 06:22:17 UTC")
        val expected =
            CoinbaseTransaction(
                id = transaction.id,
                date = transaction.timestamp.toInstantFrom(COINBASE_DATE_TIME_FORMAT),
                type = transaction.type.toCoinbaseTransactionType(),
                asset = transaction.asset,
                quantityTransacted = transaction.quantityTransacted,
                priceCurrency = transaction.priceCurrency,
                priceAtTransaction = transaction.priceAtTransaction.removeCurrency(),
                subtotal = transaction.subtotal.removeCurrency(),
                total = transaction.total.removeCurrency(),
                fees = transaction.fees.removeCurrency(),
                notes = transaction.notes,
            )
        assertEquals(expected, converter.from(transaction))
    }

    @Test
    fun `Coinbase conversion raw to transaction is successful with null quantity`() {
        val transaction: CoinbaseRaw =
            forge.build<CoinbaseRaw>().copy(timestamp = "2024-07-15 06:22:17 UTC", quantityTransacted = null)
        val expected =
            CoinbaseTransaction(
                id = transaction.id,
                date = transaction.timestamp.toInstantFrom(COINBASE_DATE_TIME_FORMAT),
                type = transaction.type.toCoinbaseTransactionType(),
                asset = transaction.asset,
                quantityTransacted = transaction.quantityTransacted,
                priceCurrency = transaction.priceCurrency,
                priceAtTransaction = transaction.priceAtTransaction.removeCurrency(),
                subtotal = transaction.subtotal.removeCurrency(),
                total = transaction.total.removeCurrency(),
                fees = transaction.fees.removeCurrency(),
                notes = transaction.notes,
            )
        assertEquals(expected, converter.from(transaction))
    }

    @Test
    fun `Coinbase conversion back to raw transaction not implemented`() {
        val transaction: CoinbaseTransaction by forgery()
        val exception = assertFailsWith<NotImplementedError> { converter.to(transaction) }
        assertEquals("Conversion back to CoinbaseRaw is not supported.", exception.message)
    }
}
