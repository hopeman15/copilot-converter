package com.hellocuriosity.utils

fun String?.currencyToDouble(currency: String = "€"): Double? = this?.replace(currency, "")?.toDoubleOrNull()
