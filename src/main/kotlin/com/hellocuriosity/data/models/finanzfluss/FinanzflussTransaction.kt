package com.hellocuriosity.data.models.finanzfluss

import com.hellocuriosity.utils.safeFindEnumCase
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FinanzflussTransaction(
    @SerialName("Datum")
    val date: String,
    @SerialName("ISIN")
    val isin: String,
    @SerialName("Name")
    val name: String,
    @SerialName("Typ")
    val type: Type,
    @SerialName("Transaktion")
    val transaction: TransactionType,
    @SerialName("Preis")
    val price: Double,
    @SerialName("Anzahl")
    val amount: Int,
    @SerialName("Gebühren")
    val fees: Double,
    @SerialName("Steuern")
    val taxes: Double,
    @SerialName("Währung")
    val currency: String,
    @SerialName("Wechselkurs")
    val exchangeRate: Double,
) {
    enum class Type(
        val value: String,
    ) {
        BOND("Anleihe"),
        FOREIGN_CURRENCY("Fremdwährung"),
        STOCK("Aktie"),
        ;

        companion object {
            fun from(value: String): Type? = Type::value.safeFindEnumCase(value)
        }
    }

    enum class TransactionType(
        val value: String,
    ) {
        BOOKING("Einbuchung"),
        BUY("Kauf"),
        COUPON("Kupon"),
        DERECOGNITION("Ausbuchung"),
        DIVIDEND("Dividende"),
        SELL("Verkauf"),
        ;

        companion object {
            fun from(value: String): TransactionType? = TransactionType::value.safeFindEnumCase(value)
        }
    }
}

fun String?.toFinanzflussType(): FinanzflussTransaction.Type? = this?.let(FinanzflussTransaction.Type::from)

fun String?.toFinanzflussTransaction(): FinanzflussTransaction.TransactionType? =
    this
        ?.let(FinanzflussTransaction.TransactionType::from)
