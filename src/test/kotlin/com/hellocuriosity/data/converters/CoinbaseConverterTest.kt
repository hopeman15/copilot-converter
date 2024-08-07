package com.hellocuriosity.data.converters

import com.hellocuriosity.data.models.coinbase.CoinbaseRaw
import com.hellocuriosity.data.models.coinbase.CoinbaseTransaction
import com.hellocuriosity.data.models.coinbase.toCoinbaseTransactionType
import com.hellocuriosity.utils.currencyToDouble
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
        val transaction: CoinbaseRaw = forge.build()
        val expected =
            CoinbaseTransaction(
                id = transaction.id,
                timestamp = transaction.timestamp,
                type = transaction.type.toCoinbaseTransactionType(),
                asset = transaction.asset,
                quantityTransacted = transaction.quantityTransacted?.toDoubleOrNull(),
                priceCurrency = transaction.priceCurrency,
                priceAtTransaction = transaction.priceAtTransaction.currencyToDouble(),
                subtotal = transaction.subtotal.currencyToDouble(),
                total = transaction.total.currencyToDouble(),
                fees = transaction.fees.currencyToDouble(),
                notes = transaction.notes,
            )
        assertEquals(expected, converter.from(transaction))
    }

    @Test
    fun `Coinbase conversion raw to transaction is successful with null quantity`() {
        val transaction: CoinbaseRaw = forge.build<CoinbaseRaw>().copy(quantityTransacted = null)
        val expected =
            CoinbaseTransaction(
                id = transaction.id,
                timestamp = transaction.timestamp,
                type = transaction.type.toCoinbaseTransactionType(),
                asset = transaction.asset,
                quantityTransacted = transaction.quantityTransacted?.toDoubleOrNull(),
                priceCurrency = transaction.priceCurrency,
                priceAtTransaction = transaction.priceAtTransaction.currencyToDouble(),
                subtotal = transaction.subtotal.currencyToDouble(),
                total = transaction.total.currencyToDouble(),
                fees = transaction.fees.currencyToDouble(),
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
