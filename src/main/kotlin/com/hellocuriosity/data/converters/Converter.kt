package com.hellocuriosity.data.converters

interface Converter<A, B> {
    fun from(value: A): B

    fun to(value: B): A
}
