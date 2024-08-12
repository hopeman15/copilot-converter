package com.hellocuriosity.utils

import com.hellocuriosity.data.models.coinbase.CoinbaseRaw
import com.hellocuriosity.data.models.finanzfluss.FinanzflussTransaction
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.decodeFromJsonElement
import kotlinx.serialization.json.encodeToJsonElement

fun JsonElement.toCoinbaseTransaction(): List<CoinbaseRaw> = Json.decodeFromJsonElement(this)

fun List<FinanzflussTransaction>.toJson(): JsonElement = Json.encodeToJsonElement(this)
