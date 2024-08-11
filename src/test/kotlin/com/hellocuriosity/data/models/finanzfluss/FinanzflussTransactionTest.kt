package com.hellocuriosity.data.models.finanzfluss

import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

private typealias TestableTransaction = Pair<FinanzflussTransaction.TransactionType?, String?>
private typealias TestableType = Pair<FinanzflussTransaction.Type?, String?>

class FinanzflussTransactionTest {
    private val typeData: List<TestableType> =
        listOf(
            TestableType(null, null),
            TestableType(FinanzflussTransaction.Type.BOND, "Anleihe"),
            TestableType(FinanzflussTransaction.Type.FOREIGN_CURRENCY, "Fremdwährung"),
            TestableType(FinanzflussTransaction.Type.STOCK, "Aktie"),
        )

    private val transactionTypeData: List<TestableTransaction> =
        listOf(
            TestableTransaction(null, null),
            TestableTransaction(FinanzflussTransaction.TransactionType.BOOKING, "Einbuchung"),
            TestableTransaction(FinanzflussTransaction.TransactionType.BUY, "Kauf"),
            TestableTransaction(FinanzflussTransaction.TransactionType.COUPON, "Kupon"),
            TestableTransaction(FinanzflussTransaction.TransactionType.DERECOGNITION, "Ausbuchung"),
            TestableTransaction(FinanzflussTransaction.TransactionType.DIVIDEND, "Dividende"),
            TestableTransaction(FinanzflussTransaction.TransactionType.SELL, "Verkauf"),
        )

    @Test
    fun `Type should resolve values`() {
        typeData.forEach { (expected, given) ->
            assertEquals(expected, given.toFinanzflussType())
        }
    }

    @Test
    fun `TransactionType should resolve values`() {
        transactionTypeData.forEach { (expected, given) ->
            assertEquals(expected, given.toFinanzflussTransaction())
        }
    }
}
