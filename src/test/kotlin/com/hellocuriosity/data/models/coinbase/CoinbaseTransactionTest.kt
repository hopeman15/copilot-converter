package com.hellocuriosity.data.models.coinbase

import kotlin.test.Test
import kotlin.test.assertNull

class CoinbaseTransactionTest {
    @Test
    fun `TransactionType should result in null when provided with null value`() {
        assertNull(null.toCoinbaseTransactionType())
    }
}
