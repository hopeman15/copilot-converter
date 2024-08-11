package com.hellocuriosity.data.converters

import com.hellocuriosity.data.models.coinbase.CoinbaseTransaction
import com.hellocuriosity.data.models.finanzfluss.FinanzflussTransaction
import com.hellocuriosity.providers.InstantProvider.toString
import com.hellocuriosity.utils.toComma
import com.hellocuriosity.utils.toCryptoName
import com.hellocuriosity.utils.toFinanzflussTransactionType

object CoinbaseToFinanzflussConverter : Converter<CoinbaseTransaction, FinanzflussTransaction> {
    override fun from(value: CoinbaseTransaction): FinanzflussTransaction =
        FinanzflussTransaction(
            date = value.date.toString(),
            isin = value.asset,
            name = value.asset.toCryptoName(),
            type = FinanzflussTransaction.Type.FOREIGN_CURRENCY.value,
            transaction = value.type.toFinanzflussTransactionType()?.value,
            price = value.priceAtTransaction.toComma(),
            amount = value.quantityTransacted.toComma(),
            fees = value.fees.toComma(),
            taxes = "0,0",
            currency = value.priceCurrency.toComma(),
            exchangeRate = "1,0",
        )

    override fun to(value: FinanzflussTransaction): CoinbaseTransaction =
        throw NotImplementedError(
            "Conversion back to CoinbaseTransaction is not supported.",
        )
}
