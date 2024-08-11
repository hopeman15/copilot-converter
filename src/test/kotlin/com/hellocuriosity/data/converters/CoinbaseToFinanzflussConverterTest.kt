package com.hellocuriosity.data.converters

import com.hellocuriosity.data.models.coinbase.CoinbaseTransaction
import com.hellocuriosity.data.models.finanzfluss.FinanzflussTransaction
import com.hellocuriosity.providers.InstantProvider.toString
import com.hellocuriosity.utils.toComma
import com.hellocuriosity.utils.toFinanzflussTransactionType
import io.github.hellocuriosity.forgery
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFailsWith

class CoinbaseToFinanzflussConverterTest {
    private val converter = CoinbaseToFinanzflussConverter

    @Test
    fun `Coinbase conversion to Finanzfluss is successful`() {
        val transaction: CoinbaseTransaction by forgery()
        val expected =
            FinanzflussTransaction(
                date = transaction.date.toString(),
                isin = transaction.asset,
                name = "Coinbase",
                type = FinanzflussTransaction.Type.FOREIGN_CURRENCY.value,
                transaction = transaction.type.toFinanzflussTransactionType()?.value,
                price = transaction.priceAtTransaction.toComma(),
                amount = transaction.quantityTransacted.toComma(),
                fees = transaction.fees.toComma(),
                taxes = "0,0",
                currency = transaction.priceCurrency.toComma(),
                exchangeRate = "1,0",
            )
        assertEquals(expected, converter.from(transaction))
    }

    @Test
    fun `Coinbase conversion back to Finanzfluss is not implemented`() {
        val transaction: FinanzflussTransaction by forgery()
        val exception = assertFailsWith<NotImplementedError> { converter.to(transaction) }
        assertEquals("Conversion back to CoinbaseTransaction is not supported.", exception.message)
    }
}
