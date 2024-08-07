package com.hellocuriosity.data.converters

import com.hellocuriosity.data.models.coinbase.CoinbaseRaw
import com.hellocuriosity.data.models.coinbase.CoinbaseTransaction
import com.hellocuriosity.data.models.coinbase.toCoinbaseTransactionType
import com.hellocuriosity.utils.currencyToDouble

object CoinbaseConverter : Converter<CoinbaseRaw, CoinbaseTransaction> {
    override fun from(value: CoinbaseRaw): CoinbaseTransaction =
        CoinbaseTransaction(
            id = value.id,
            timestamp = value.timestamp,
            type = value.type.toCoinbaseTransactionType(),
            asset = value.asset,
            quantityTransacted = value.quantityTransacted?.toDoubleOrNull(),
            priceCurrency = value.priceCurrency,
            priceAtTransaction = value.priceAtTransaction.currencyToDouble(),
            subtotal = value.subtotal.currencyToDouble(),
            total = value.total.currencyToDouble(),
            fees = value.fees.currencyToDouble(),
            notes = value.notes,
        )

    override fun to(value: CoinbaseTransaction): CoinbaseRaw =
        throw NotImplementedError(
            "Conversion back to CoinbaseRaw is not supported.",
        )
}
