package com.hellocuriosity.utils

fun String?.removeCurrency(currency: String = "€"): String? = this?.replace(currency, "")

fun String?.toCommas(): String? = this?.replace('.', ',')
