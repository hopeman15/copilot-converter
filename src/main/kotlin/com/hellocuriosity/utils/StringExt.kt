package com.hellocuriosity.utils

fun String?.removeCurrency(currency: String = "€"): String? = this?.replace(currency, "")

fun String?.toComma(): String? = this?.replace('.', ',')
