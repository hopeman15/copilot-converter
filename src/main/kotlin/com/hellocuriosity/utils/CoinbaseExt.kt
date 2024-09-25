package com.hellocuriosity.utils

import com.hellocuriosity.data.models.coinbase.CoinbaseTransaction
import com.hellocuriosity.data.models.finanzfluss.FinanzflussTransaction

fun CoinbaseTransaction.TransactionType?.toFinanzflussTransactionType(): FinanzflussTransaction.TransactionType? =
    when (this) {
        CoinbaseTransaction.TransactionType.BUY -> FinanzflussTransaction.TransactionType.BUY
        CoinbaseTransaction.TransactionType.CONVERT -> FinanzflussTransaction.TransactionType.BOOKING
        CoinbaseTransaction.TransactionType.DEPOSIT -> FinanzflussTransaction.TransactionType.BOOKING
        CoinbaseTransaction.TransactionType.INFLATION_REWARD -> FinanzflussTransaction.TransactionType.DIVIDEND
        CoinbaseTransaction.TransactionType.LEARNING_REWARD -> FinanzflussTransaction.TransactionType.DIVIDEND
        CoinbaseTransaction.TransactionType.RECEIVE -> FinanzflussTransaction.TransactionType.BOOKING
        CoinbaseTransaction.TransactionType.SELL -> FinanzflussTransaction.TransactionType.SELL
        CoinbaseTransaction.TransactionType.SEND -> FinanzflussTransaction.TransactionType.DERECOGNITION
        CoinbaseTransaction.TransactionType.STAKING -> FinanzflussTransaction.TransactionType.DIVIDEND
        CoinbaseTransaction.TransactionType.WITHDRAWAL -> FinanzflussTransaction.TransactionType.DERECOGNITION
        else -> null
    }
