package com.hellocuriosity.data.models.finanzfluss

import com.hellocuriosity.utils.safeFindEnumCase
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class FinanzflussTransaction(
    @SerialName("Datum")
    val date: String?,
    @SerialName("ISIN")
    val isin: String?,
    @SerialName("Name")
    val name: String?,
    @SerialName("Typ")
    val type: String?,
    @SerialName("Transaktion")
    val transaction: String?,
    @SerialName("Preis")
    val price: String?,
    @SerialName("Anzahl")
    val amount: String?,
    @SerialName("Gebühren")
    val fees: String?,
    @SerialName("Steuern")
    val taxes: String?,
    @SerialName("Währung")
    val currency: String?,
    @SerialName("Wechselkurs")
    val exchangeRate: String?,
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
