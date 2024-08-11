package com.hellocuriosity.data.converters

import com.hellocuriosity.data.models.coinbase.CoinbaseTransaction
import com.hellocuriosity.data.models.finanzfluss.FinanzflussTransaction
import com.hellocuriosity.providers.InstantProvider.toString
import com.hellocuriosity.utils.toFinanzflussTransactionType

object CoinbaseToFinanzflussConverter : Converter<CoinbaseTransaction, FinanzflussTransaction> {
    override fun from(value: CoinbaseTransaction): FinanzflussTransaction =
        FinanzflussTransaction(
            date = value.date.toString(),
            isin = value.asset,
            name = "Coinbase",
            type = FinanzflussTransaction.Type.FOREIGN_CURRENCY,
            transaction = value.type.toFinanzflussTransactionType(),
            price = value.priceAtTransaction,
            amount = value.quantityTransacted,
            fees = value.fees,
            taxes = 0.0,
            currency = value.priceCurrency,
            exchangeRate = 1.0,
        )

    override fun to(value: FinanzflussTransaction): CoinbaseTransaction =
        throw NotImplementedError(
            "Conversion back to CoinbaseTransaction is not supported.",
        )
}
