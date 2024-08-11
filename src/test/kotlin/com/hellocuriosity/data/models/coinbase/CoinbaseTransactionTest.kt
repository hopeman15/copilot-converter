package com.hellocuriosity.data.models.coinbase

import kotlin.test.Test
import kotlin.test.assertEquals

private typealias Testable = Pair<CoinbaseTransaction.TransactionType?, String?>

class CoinbaseTransactionTest {
    private val data: List<Testable> =
        listOf(
            // Testable(expected, given)
            Testable(null, null),
            Testable(CoinbaseTransaction.TransactionType.BUY, "Buy"),
            Testable(CoinbaseTransaction.TransactionType.CONVERT, "Convert"),
            Testable(CoinbaseTransaction.TransactionType.DEPOSIT, "Deposit"),
            Testable(CoinbaseTransaction.TransactionType.INFLATION_REWARD, "Inflation Reward"),
            Testable(CoinbaseTransaction.TransactionType.LEARNING_REWARD, "Learning Reward"),
            Testable(CoinbaseTransaction.TransactionType.RECEIVE, "Receive"),
            Testable(CoinbaseTransaction.TransactionType.SELL, "Sell"),
            Testable(CoinbaseTransaction.TransactionType.SEND, "Send"),
            Testable(CoinbaseTransaction.TransactionType.STAKING, "Staking Income"),
        )

    @Test
    fun `TransactionType should resolve values`() {
        data.forEach { (expected, given) ->
            assertEquals(expected, given.toCoinbaseTransactionType())
        }
    }
}
