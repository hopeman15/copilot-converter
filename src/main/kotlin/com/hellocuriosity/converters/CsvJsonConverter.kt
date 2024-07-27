package com.hellocuriosity.converters

import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject
import java.io.File

typealias Csv = String

object CsvJsonConverter : Converter<Csv, JsonElement> {
    override fun from(value: Csv): JsonElement {
        val csvFile = File(value)
        require(csvFile.exists() && csvFile.isFile) { "File $value does not exist or is not a valid file" }

        val lines = csvFile.readLines()
        require(lines.isNotEmpty()) { "File $value is empty" }

        val headers = lines.first().split(",").map { it.trim() }
        val data = lines.drop(1).map { it.split(",").map { field -> field.trim() } }

        val jsonArray =
            JsonArray(
                data.map { row ->
                    buildJsonObject {
                        headers.forEachIndexed { idx, header ->
                            put(header, JsonPrimitive(row.getOrElse(idx) { "" }))
                        }
                    }
                },
            )

        return jsonArray
    }

    override fun to(value: JsonElement): Csv {
        TODO("Not yet implemented")
    }
}
