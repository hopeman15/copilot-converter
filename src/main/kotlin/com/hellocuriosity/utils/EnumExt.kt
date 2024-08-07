package com.hellocuriosity.utils

inline fun <reified T : Enum<T>, V> ((T) -> V).safeFindEnumCase(value: V): T? =
    enumValues<T>().firstOrNull {
        this(it) == value
    }
