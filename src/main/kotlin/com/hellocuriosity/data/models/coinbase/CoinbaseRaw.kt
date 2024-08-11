package com.hellocuriosity.data.models.coinbase

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CoinbaseRaw(
    @SerialName("ID")
    val id: String? = null,
    @SerialName("Timestamp")
    val timestamp: String? = null,
    @SerialName("Transaction Type")
    val type: String? = null,
    @SerialName("Asset")
    val asset: String? = null,
    @SerialName("Quantity Transacted")
    val quantityTransacted: String? = null,
    @SerialName("Price Currency")
    val priceCurrency: String? = null,
    @SerialName("Price at Transaction")
    val priceAtTransaction: String? = null,
    @SerialName("Subtotal")
    val subtotal: String? = null,
    @SerialName("Total (inclusive of fees and/or spread)")
    val total: String? = null,
    @SerialName("Fees and/or Spread")
    val fees: String? = null,
    @SerialName("Notes")
    val notes: String? = null,
)
