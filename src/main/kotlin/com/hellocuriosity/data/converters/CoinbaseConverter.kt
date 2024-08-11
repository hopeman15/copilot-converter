package com.hellocuriosity.data.converters

import com.hellocuriosity.data.models.coinbase.CoinbaseRaw
import com.hellocuriosity.data.models.coinbase.CoinbaseTransaction
import com.hellocuriosity.data.models.coinbase.toCoinbaseTransactionType
import com.hellocuriosity.providers.InstantProvider.COINBASE_DATE_TIME_FORMAT
import com.hellocuriosity.providers.InstantProvider.toInstantFrom
import com.hellocuriosity.utils.removeCurrency

object CoinbaseConverter : Converter<CoinbaseRaw, CoinbaseTransaction> {
    override fun from(value: CoinbaseRaw): CoinbaseTransaction =
        CoinbaseTransaction(
            id = value.id,
            date = value.timestamp.toInstantFrom(COINBASE_DATE_TIME_FORMAT),
            type = value.type.toCoinbaseTransactionType(),
            asset = value.asset,
            quantityTransacted = value.quantityTransacted,
            priceCurrency = value.priceCurrency,
            priceAtTransaction = value.priceAtTransaction.removeCurrency(),
            subtotal = value.subtotal.removeCurrency(),
            total = value.total.removeCurrency(),
            fees = value.fees.removeCurrency(),
            notes = value.notes,
        )

    override fun to(value: CoinbaseTransaction): CoinbaseRaw =
        throw NotImplementedError(
            "Conversion back to CoinbaseRaw is not supported.",
        )
}
