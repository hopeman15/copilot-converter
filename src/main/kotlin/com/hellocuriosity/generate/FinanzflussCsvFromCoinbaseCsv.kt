package com.hellocuriosity.generate

import com.hellocuriosity.data.converters.CoinbaseConverter
import com.hellocuriosity.data.converters.CoinbaseToFinanzflussConverter
import com.hellocuriosity.data.converters.CsvJsonConverter
import com.hellocuriosity.providers.FileWriterProvider
import com.hellocuriosity.utils.toCoinbaseTransaction
import com.hellocuriosity.utils.toJson

class FinanzflussCsvFromCoinbaseCsv(
    private val csvConverter: CsvJsonConverter = CsvJsonConverter,
    private val coinbaseConverter: CoinbaseConverter = CoinbaseConverter,
    private val coinbaseToFinanzflussConverter: CoinbaseToFinanzflussConverter = CoinbaseToFinanzflussConverter,
    private val fileWriterProvider: FileWriterProvider = FileWriterProvider(),
) {
    fun generate(file: String) =
        csvConverter
            .from(file)
            .toCoinbaseTransaction()
            .map(coinbaseConverter::from)
            .map(coinbaseToFinanzflussConverter::from)
            .toJson()
            .let(csvConverter::to)
            .let(fileWriterProvider::write)
}
