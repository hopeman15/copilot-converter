package com.hellocuriosity.utils

import com.hellocuriosity.data.models.coinbase.CoinbaseTransaction
import com.hellocuriosity.data.models.finanzfluss.FinanzflussTransaction
import kotlin.test.Test
import kotlin.test.assertEquals

private typealias Testable = Pair<FinanzflussTransaction.TransactionType?, CoinbaseTransaction.TransactionType?>

class CoinbaseExtTest {
    private val transactionTypeData: List<Testable> =
        listOf(
            Testable(null, null),
            Testable(
                FinanzflussTransaction.TransactionType.BUY,
                CoinbaseTransaction.TransactionType.BUY,
            ),
            Testable(
                FinanzflussTransaction.TransactionType.BOOKING,
                CoinbaseTransaction.TransactionType.CONVERT,
            ),
            Testable(
                FinanzflussTransaction.TransactionType.BOOKING,
                CoinbaseTransaction.TransactionType.DEPOSIT,
            ),
            Testable(
                FinanzflussTransaction.TransactionType.DIVIDEND,
                CoinbaseTransaction.TransactionType.INFLATION_REWARD,
            ),
            Testable(
                FinanzflussTransaction.TransactionType.DIVIDEND,
                CoinbaseTransaction.TransactionType.LEARNING_REWARD,
            ),
            Testable(
                FinanzflussTransaction.TransactionType.BOOKING,
                CoinbaseTransaction.TransactionType.RECEIVE,
            ),
            Testable(
                FinanzflussTransaction.TransactionType.SELL,
                CoinbaseTransaction.TransactionType.SELL,
            ),
            Testable(
                FinanzflussTransaction.TransactionType.DERECOGNITION,
                CoinbaseTransaction.TransactionType.SEND,
            ),
            Testable(
                FinanzflussTransaction.TransactionType.DIVIDEND,
                CoinbaseTransaction.TransactionType.STAKING,
            ),
        )

    @Test
    fun `Coinbase transaction type should convert to Finanzfluss transaction type`() {
        transactionTypeData.forEach { (expected, given) ->
            assertEquals(expected, given.toFinanzflussTransactionType())
        }
    }
}
