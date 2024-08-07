package com.hellocuriosity.data.models.coinbase

import com.hellocuriosity.utils.safeFindEnumCase

data class CoinbaseTransaction(
    val id: String? = null,
    val timestamp: String? = null,
    val type: TransactionType? = null,
    val asset: String? = null,
    val quantityTransacted: Double? = null,
    val priceCurrency: String? = null,
    val priceAtTransaction: Double? = null,
    val subtotal: Double? = null,
    val total: Double? = null,
    val fees: Double? = null,
    val notes: String? = null,
) {
    enum class TransactionType(
        val value: String,
    ) {
        BUY("Buy"),
        CONVERT("Convert"),
        DEPOSIT("Deposit"),
        INFLATION_REWARD("Inflation Reward"),
        LEARNING_REWARD("Learning Reward"),
        RECEIVE("Receive"),
        SELL("Sell"),
        SEND("Send"),
        STAKING("Staking Income"),
        ;

        companion object {
            fun from(string: String): TransactionType? = TransactionType::value.safeFindEnumCase(string)
        }
    }
}

fun String?.toCoinbaseTransactionType(): CoinbaseTransaction.TransactionType? =
    this?.let {
        CoinbaseTransaction.TransactionType.from(it)
    }
